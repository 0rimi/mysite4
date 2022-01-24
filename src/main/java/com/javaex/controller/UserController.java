package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String login(@ModelAttribute UserVo userVo,HttpSession session,Model model) {//세션쓸게!?
		System.out.println("UserController > login");
		
		//유저아이디패스워드 넣으면 넘버, 이름정보주는 메소드 사용
		UserVo authUser = userDao.selectUser(userVo);
		System.out.println(authUser);
		
		if(authUser != null) {//로그인 성공
		System.out.println("로그인성공");
		
		//세션에 저장
		session.setAttribute("authUser", authUser);
			
		//리다이렉트
		return "redirect:/";	
		}else {
		System.out.println("로그인실패");
		
		//fail이라는 result 보내주기	
		String result = "fail";
		model.addAttribute("result", result);
		
		return "redirect:/user/loginForm";	
		}	
	}
	
	@RequestMapping(value="/logout", method={RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController > logout");
		
		//세션값지우기
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}

}
