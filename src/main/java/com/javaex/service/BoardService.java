package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Repository
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//리스트 출력
	public List<BoardVo> list(){
		
		List<BoardVo> bdList = boardDao.list();
		
		return bdList;
	}
	
	//특정넘버 조회수 올리면서 불러오는 메소드
	public BoardVo read(int no) {
		boardDao.upread(no);
		BoardVo boardVo = boardDao.getboard(no);
		
		return boardVo;
	}
	
	//insert(게시글등록)
	public void insert(BoardVo boardVo) {
		
		/*
		//페이징 데이타 추가 123개
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i+"번째 글입니다");
			boardVo.setContent(i+"번째 글 내용입니다.");
			boardVo.setUserNo(1);
			boardDao.insert(boardVo);
		}
		*/
		
		boardDao.insert(boardVo);
	}
	
	//특정 게시글 정보 불러오는 메소드
	public BoardVo getboard(int no) {
		BoardVo boardVo = boardDao.getboard(no);
		
		return boardVo;
	}
	
	//수정하는 메소드
	public void update(BoardVo boardVo) {
		boardDao.update(boardVo);
	}
	
	//삭제하는 메소드
	public void delete(int no) {
		boardDao.delete(no);
	}
	
	
	
	//리스트(리스트+페이징)
	public Map<String, Object> listPaging(int crtPage){
		System.out.println("boardService/list+paging");
		
		
		/////////리스트출력/////////
		//현재페이지 처리
		crtPage = (crtPage>0) ? crtPage : (crtPage=1); 
		
		//페이지당 글 개수
		int listCnt = 10;
				
		//시작글 번호		1>1 	6>51
		int startRnum = (crtPage-1)*listCnt + 1;
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		List<BoardVo> boardList = boardDao.pageList(startRnum, endRnum);
		
		System.out.println(boardList);
		
		/////////페이징버튼/////////
		int totalCnt = boardDao.selectTotal();
		System.out.println("totalCnt = "+totalCnt);
		
		//페이지당 버튼갯수
		int pageCnt = 5;
		
		//마지막 버튼번호
		//1  1~5	0.2
		//5  1~5	1.0
		//6  6~10	1.2
		//10 6~10	2        >>올림해서 같은 범위는 같은 정수로 나오게끔
		int endPageNo = (int)(Math.ceil(crtPage/(double)5))*pageCnt; 
		
		//시작버튼 번호
		int startPageNo = endPageNo - (pageCnt-1);
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageNo*listCnt < totalCnt) {
			next = true;
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageNo != 1) {
			prev = true;
		}
		
		//////////포장//////////////
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("prev", prev);
		pageMap.put("starNo", startPageNo);
		pageMap.put("endNo", endPageNo);
		pageMap.put("next", next);		
		
		
		return pageMap;
	}
	
	

}
