package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {
	
	//필드
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("GuestbookController > addList");
		
		
		
		return "guestbook/addList";
	}

	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public String add() {
		System.out.println("GuestbookController > add");
		
			
		
		
		return "guestbook/add";
	}
	
	@RequestMapping(value="/delete/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no) {
		System.out.println("GuestbookController > delete");
		
			
		
		
		return "guestbook/delete";
	}

	
	
	
}
