package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Repository
public class UserService {
	
	//필드
	@Autowired
	private UserDao userDao;
	
	public UserVo login(UserVo userVo) {
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;	
	}
	
	public void insert(UserVo userVo) {
		userDao.insert(userVo);
	}
	
	//회원정보 받아주는 메소드
	public UserVo getinfo(int no) {
		
		UserVo userinfo = userDao.getinfo(no);
		
		return userinfo;
	}
	
	//회원정보 업데이트해주는 메소드
	public void update(UserVo userVo) {
		userDao.update(userVo);
	}
}
