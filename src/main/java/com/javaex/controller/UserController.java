package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	//필드
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/loginForm", method={RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController > loginForm");
		
		return "user/loginForm";
	}
	
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo,HttpSession session,Model model) {//세션쓸게!?
		System.out.println("UserController > login");
		
		//유저아이디패스워드 넣으면 넘버, 이름정보주는 메소드 사용
		UserVo authUser = userService.login(userVo);  //selectUser(userVo);
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
	
	@RequestMapping(value="/joinForm", method={RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController > joinForm");
			
		return "user/joinForm";
	}
	
	@RequestMapping(value="/join", method={RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > join");
		
		//파라미터값 넣은 userVo 넣어주는 insert 메소드 이용
		userService.insert(userVo);
			
		return "user/joinOk";
	}
	
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController > modifyForm");
		
		//세션에서 회원정보 받아오기
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		//받아온 정보로 이 회원의 정보(아이디,패스워드,이름, 성별) 받아주는 메소드이용
		UserVo userinfo = userService.getinfo(no);
		
		//모델로 보내기
		model.addAttribute("userinfo",userinfo);
		
		//뷰
		return "user/modifyForm";
	}
	
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo) {
		System.out.println("UserController > modify");
		
		//받아온파라미터값을 넣은 userVo넣어 업데이트해주는 메소드이용
		userService.update(userVo);
				
		//리다이렉트
		return "redirect:/user/modifyForm";
	}

}
