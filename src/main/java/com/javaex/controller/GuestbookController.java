package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {
	
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController > addList");
		
		//다오에서 리스트 가져오기
		List<GuestbookVo> gbList = guestbookDao.getList();
		
		//모델로 보내기
		model.addAttribute("gbList", gbList);
		
		//리턴하기		
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
