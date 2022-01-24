package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트출력 메소드
	public List<BoardVo> list(){
		
		List<BoardVo> bdList = sqlSession.selectList("board.selectList");
		
		return bdList;
	}
	
}
