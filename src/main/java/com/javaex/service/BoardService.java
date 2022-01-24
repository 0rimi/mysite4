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

}
