package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//게스트북리스트 출력
	public List<GuestbookVo> getList() {
		System.out.println("getList()");
		
		List<GuestbookVo> gbList = sqlSession.selectList("guestbook.selectList");
		System.out.println(gbList);
		
		return gbList;
	}

}
