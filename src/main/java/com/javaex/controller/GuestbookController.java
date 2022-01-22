package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {
	
	//필드
	
	public String addList() {
		System.out.println("GuestbookController > addList");
		
		return "guest/addList";
	}

}
