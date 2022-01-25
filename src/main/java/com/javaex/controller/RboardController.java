package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.RBoardVo;

@Controller
@RequestMapping(value="/rboard")
public class RboardController {
	
	//필드
	@Autowired
	private RboardService rboardService;
	
	
	@RequestMapping(value="/List", method={RequestMethod.GET, RequestMethod.POST})
	public String List(Model model) {
		System.out.println("RboardController > rList");
				
		//리스트 출력해주는 메소드이용
		List<RBoardVo> rbdList = rboardService.list();
		
		//테스트출력
		System.out.println(rbdList.toString());
		
		//모델로 보내주기
		model.addAttribute("rbdList",rbdList);
		
		
		//뷰
		return "rboard/list";
	}
	
	
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("RBoardController > writeForm");
				
		return "rboard/writeForm";
	}
	
	//그냥 글쓰기폼
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("RBoardController > write");
				
		//파라미터값 넣어준 BoardVo이용 insert메소드 사용
		
		
		//리다이렉트
		return "redirect:/rboard/List";
	}
	
	//댓글쓰기폼
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String rwrite(@ModelAttribute BoardVo boardVo) {
		System.out.println("RBoardController > write");
				
		//파라미터값 넣어준 BoardVo이용 insert메소드 사용
		
		
		//리다이렉트
		return "redirect:/rboard/List";
	}
	
	@RequestMapping(value="/read", method={RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("RBoardController > read");
		
		//특정 넘버의 게시글 조회수 1 올리면서 정보 가져오는 메소드 이용
		
		
		//모델로 보내기
		
		
		//뷰		
		return "rboard/read";
	}
	
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("RBoardControllerRBoardController > modifyForm");
		
		//특정 넘버의 게시글 정보 가져오는 메소드 이용
		
		
		//모델을 사용해서 출력
		
		
		//뷰
		return "board/modifyForm";
	}
	
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("RBoardController > modify");
		
		//파라미터값을 넣은 BoardVo를 이용하여 수정하는 메소드 이용
		
		
		//리다이렉트
		return "redirect:/rboard/List";
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("RBoardController > delete");
		
		//특정 넘버의 게시글 삭제하는 메소드
		
		
		//리다이렉트
		return "redirect:/rboard/List";
	}

}
