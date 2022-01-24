package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guest")
public class GuestbookController {
	
	//필드
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/List", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController > addList");
		
		//다오에서 리스트 가져오기
		List<GuestbookVo> gbList = guestbookService.List();
		
		//모델로 보내기
		model.addAttribute("gbList", gbList);
		
		//리턴하기		
		return "guestbook/addList";
	}

	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController > add");
		
		//받아온값 넣어주는 Dao사용.
		guestbookService.insert(guestbookVo);
		
		return "redirect:/guest/List";
	}
	
	@RequestMapping(value="/deleteForm", method={RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestbookController > deleteForm");
		
		return "guestbook/deleteForm";
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("GuestbookController > delete");
		
		//받아온 no값 이용 특정 유저 정보 출력
		GuestbookVo guestinfo = guestbookService.getUser(no);
		
		//유저정보 이용, 게스트삭제
		guestbookService.delete(guestinfo);
		
		return "redirect:/guest/List";
	}

	
	
	
}
