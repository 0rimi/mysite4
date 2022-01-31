package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guest")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookservice;
	
	@RequestMapping("/List")
	public String addList() {
		System.out.println("ApiGuestbookContoller.addList()");
		
		return "aGuestbook/addList";
	}
	
	@ResponseBody  //리스폰스바디에 붙여 보낼게!
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookContoller.list()");
		
		//리스트 출력 메소드
		List<GuestbookVo> guestList = guestbookservice.List();
		
		return guestList;
	}
	
	//@ResponseBody
	@RequestMapping("/write")
	public String write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookContoller.write()");
		
		//write메소드 
		guestbookservice.addGuestReturn(guestbookVo);
		
		return "redirect:/api/guest/list";
	}
	
	//글쓰고, 다시 리스트 로딩필요
	
}
