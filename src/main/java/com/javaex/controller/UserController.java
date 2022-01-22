package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	@Autowired
	private UserDao userDao;
	
	
	@RequestMapping(value="/loginForm", method={RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController > loginForm");
		
		return "user/loginForm";
	}
	
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > login");
		
		//유저아이디패스워드 넣으면 정보가져오기
		UserVo authUser = userDao.getUser(userVo);
		System.out.println(authUser);
		
		return "main/index";
	}

}
