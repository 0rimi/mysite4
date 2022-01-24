package com.javaex.service;

import java.util.List;

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
}
