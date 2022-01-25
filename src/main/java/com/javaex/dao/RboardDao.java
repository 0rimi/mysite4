package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트출력 메소드
	public List<RBoardVo> list(){
		
		List<RBoardVo> rbdList = sqlSession.selectList("rboard.selectList");
		
		return rbdList;
	}

}
