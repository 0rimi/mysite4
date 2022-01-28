<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.12.4.js"></script>
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
									<td><input id="input-password" type="password" name="password"></td>
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
					<div id = "listArea">
					
					</div>
					
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
</body>
<script type="text/javascript">
	//로딩되기전에 요청
	$(document).ready(function(){
		console.log("리스트요청");

		//리스트그리기
		fetchList();		
	});
	
	//저장버튼이 클릭되었을때(이벤트)
	$("#btnwrite").on("click", function(){
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
		      url : "${pageContext.request.contextPath }/api/guest/write",  //api의 리스트를 출력하는 메소드    
		      type : "post",
		      //contentType : "application/json",
		      data : {name : name,
					  password : password,
					  content : content	}, 		//주소뒤에 갈 데이터 전송방식 

		      dataType : "json", //jaon> javascript
		      success : function(result){
		         	     
		      },
		      error : function(XHR, status, error) {
		         console.error(status + " : " + error);
		      }
		   });
	})
	
	//리스트 출력
	function fetchList(){
		$.ajax({
			  //url로 요청할게!    
		      url : "${pageContext.request.contextPath }/api/guest/list",  //api의 리스트를 출력하는 메소드    
		      type : "post",
		      //contentType : "application/json",
		      //data : {name: ”홍길동"},

		      dataType : "json", //jaon> javascript
		      success : function(guestList){
		         /*성공시 처리해야될 코드 작성*/
		         console.log(guestList);		 
		         //그리는 메소드(guestList);
		         for(var i=0; i<guestList.length; i++){
		        	 render(guestList[i]); 
		         }		     
		      },
		      error : function(XHR, status, error) {
		         console.error(status + " : " + error);
		      }
		   });
	}
	
	function render(guestbookVo){
			var str = '';
			str += ' <table class="guestRead"> ';
			str += ' 	<colgroup> ';
			str += ' 		<col style="width: 10%;"> ';
			str += ' 		<col style="width: 40%;"> ';
			str += ' 		<col style="width: 40%;"> ';
			str += ' 		<col style="width: 10%;"> ';
			str += ' 	</colgroup> ';
			str += ' 	<tr> ';
			str += ' 		<td>'+guestbookVo.no+'</td> ';
			str += ' 		<td>'+guestbookVo.name+'</td> ';
			str += ' 		<td>'+guestbookVo.regDate+'</td> ';
			str += ' 		<td><a href="${pageContext.request.contextPath}/guest/deleteForm?no='+guestbookVo.no+'">[삭제]</a></td> ';
			str += ' 	</tr> ';
			str += ' 	<tr> ';
			str += ' 		<td colspan=4 class="text-left">'+guestbookVo.content+'</td> ';
			str += ' 	</tr> ';
			str += ' </table> ';	
			
			$("#listArea").append(str);		//만든 문자열 listArea에 누적형식으로 끼워넣기		
		}
	
	
</script>
</html>