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
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookContoller.write()");
		
		//저장하고 저장된값 리턴
		GuestbookVo gVo = guestbookservice.addGuestResultVo(guestbookVo);
		System.out.println(gVo);
		
		return gVo;
	}
	
	@ResponseBody //바디에 json으로 담아 보내기.
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo delInfo) {
		System.out.println("ApiGuestbookController.remove()");
		
		//값 불러오고 삭제
		System.out.println(delInfo);
		String result = guestbookservice.removeGuest(delInfo);
		System.out.println(result);
		
		return result;
	}
	
	
	
}
