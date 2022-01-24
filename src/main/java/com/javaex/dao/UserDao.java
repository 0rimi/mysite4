package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//유저정보가져오기(로그인시 사용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectUser",userVo);
		
		return authUser;
	}
	
	//유저 저장(회원가입시사용)
	public int insert(UserVo userVo) {
		System.out.println("UserDao.insert()");
		System.out.println(userVo);
		
		int count = sqlSession.insert("user.insert", userVo); 
		System.out.println(count+"건이 저장되었습니다.");
		
		return count;
	}
	
	//유저 정보 받아주는 메소드
	public UserVo getinfo(int no) {
		System.out.println("UserDao.getinfo()");
		
		UserVo userinfo = sqlSession.selectOne("user.selectOne", no);
		System.out.println(userinfo);
		
		return userinfo;		
	}
	
	//유저 정보 업데이트 해주는 메소드(수정용)
	public int update(UserVo userVo) {
		System.out.println("UserDao.update");
		
		int count = sqlSession.update("user.update", userVo);
		System.out.println(count+"건이 수정되었습니다.");
		
		return count;
	}
}
