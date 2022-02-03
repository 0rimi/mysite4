<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->
			<div id="content">
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->
				<div id="guestbook">
					<!-- <form action="${pageContext.request.contextPath}/guest/add" method="get"> -->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								</td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label>
								</td>
								<td><input id="input-password" type="password"
									name="password"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4" class="text-center">
									<button id="btnwrite" type="submit">등록</button>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="action" value="add">
					<!-- </form> -->

					<!-- //guestWrite -->


					<!-- 테이블 있던 자리 -->
					<div id="listArea"></div>

					<!-- //guestRead -->
				</div>
				<!-- //guestbook -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->

	<!-- ----------------------------------------------------------------------------------- -->
	<!-- 삭제 모달창 -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">글 삭제</h4>
				</div>
				<div class="modal-body">
					
					<!-- 비밀번호 입력창 -->
					비밀번호 : <input id="modalPw" type="password" name="ModalPW" value="">
					<br><input id="modalNo" type="text" name="" value="">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					<button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->




</body>
<script type="text/javascript">
	//로딩되기전에 요청
	$(document).ready(function() {
		console.log("리스트요청");

		//리스트그리기
		fetchList();
	});

	//저장버튼이 클릭되었을때(이벤트)
	$("#btnwrite").on("click", function() {
		console.log("클릭")

		//입력된 데이터를 가져오기
		var name = $("#input-uname").val();
		var password = $("#input-password").val();
		var content = $("[name='content']").val();

		//객체만들기
		var guestbookVo = {
			name : name,
			password : password,
			content : content
		}

		//출력
		console.log(guestbookVo);

		//요청
		$.ajax({
			//url로 요청할게!    
			url : "${pageContext.request.contextPath }/api/guest/write", //api의 리스트를 출력하는 메소드    
			type : "post",
			//contentType : "application/json",
			data : {
				name : name,
				password : password,
				content : content
			}, //주소뒤에 갈 데이터 전송방식 

			//dataType : "json", //jaon> javascript
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);
				render(guestbookVo, "up"); //위로 붙일지!

				//입력화면 초기화
				$("#input-uname").val("");
				$("#input-pass").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	})

	//삭제팝업 버튼을 눌렀을때
	//새로생긴애들이니까 부모한테 이벤트를 줘야함!
	$("#listArea").on("click", ".btnDelPop", function() {

		//자바스크립트  jquery변수
		//데이터수집
		var $this = $(this);
		var no = $this.data("no");
		console.log(no+"클릭");
	
		//회색바탕위에 팝업창을 열기
		//초기화
		$("[name='ModalPW']").val("");
		$("#modalNo").val(no);
		//#아이디가 myModal인애를 보여준다
		$('#delModal').modal('show');
	});
	
	//모달창의 삭제버튼 클릭할때
	$("#modalBtnDel").on("click", function(){
		console.log("모달 삭제버튼 클릭");
		
		//데이터수집
		var no = $("#modalNo").val();	//no의 밸류를 가져와
		var pw = $("#modalPw").val();
		
		var delInfo = {
			no : no,
			password : pw
		};
		console.log(delInfo);
		
		
		//ajax요청 : 넘버, 패스워드 넘겨주면서 삭제.
		$.ajax({
			//url로 요청할게!    
			url : "${pageContext.request.contextPath }/api/guest/remove",    
			type : "post",
			//contentType : "application/json",
			data : delInfo,

			dataType : "json", //jaon> javascript
			success : function(result) {
				console.log(result);
				
				/*성공시 처리해야될 코드 작성*/
				if(result === "success"){
					//리로딩. 화면에서 변경되는 부분 반영
					//	해당 테이블 html 삭제
					$("#t"+no).remove();
					//	모달창닫기
					$('#delModal').modal('hide');				
				}else{
					//	모달창닫기
					alert("비밀번호를 확인하세요");
					$('#delModal').modal('hide');	
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	
	});
	

	//리스트 출력
	function fetchList() {

		$.ajax({
			//url로 요청할게!    
			url : "${pageContext.request.contextPath }/api/guest/list", //api의 리스트를 출력하는 메소드    
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			dataType : "json", //jaon> javascript
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);
				//그리는 메소드(guestList);
				for (var i = 0; i < guestList.length; i++) {
					render(guestList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};

	function render(guestbookVo, updown) {
		var str = '';
		str += ' <table id="t'+guestbookVo.no+'" class="guestRead"> ';
		str += ' 	<colgroup> ';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 	</colgroup> ';
		str += ' 	<tr> ';
		str += ' 		<td>' + guestbookVo.no + '</td> ';
		str += ' 		<td>' + guestbookVo.name + '</td> ';
		str += ' 		<td>' + guestbookVo.regDate + '</td> ';
		str += ' 		<td><button class="btnDelPop" type="button" data-no="'+ guestbookVo.no +'">삭제</button></td> ';
		str += ' 	</tr> ';
		str += ' 	<tr> ';
		str += ' 		<td colspan=4 class="text-left">' + guestbookVo.content	+ '</td> ';
		str += ' 	</tr> ';
		str += ' </table> ';

		//$("#listArea").append(str);		//만든 문자열 listArea에 누적형식으로 끼워넣기

		if (updown == 'down') {
			$("#listArea").append(str);
		} else if (updown == 'up') {
			$("#listArea").prepend(str);
		} else {
			console.log("방향오류");
		}

	};
</script>
</html>