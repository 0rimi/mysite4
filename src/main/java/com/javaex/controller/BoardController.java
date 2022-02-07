package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	//필드
	@Autowired
	private BoardService boardService;
	
	//리스트만 출력
	@RequestMapping(value="/List2", method={RequestMethod.GET, RequestMethod.POST})
	public String List2(Model model) {
		System.out.println("BoardController > List");
		
		//리스트 출력해주는 메소드이용
		List<BoardVo> bdList = boardService.list();
		
		//테스트출력
		System.out.println(bdList.toString());
		
		//모델로 보내주기
		model.addAttribute("bdList",bdList);
		
		//뷰
		return "board/list";
	}
	
	//리스트+페이징
	@RequestMapping(value="/List", method={RequestMethod.GET, RequestMethod.POST})
	public String List(Model model,
					   @RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("BoardController > List");
		
		System.out.println(crtPage+"page");
		
		//10개짜리 글리스트 출력!
		Map<String, Object> pageMap = boardService.listPaging(crtPage);
		
		/*
		List<BoardVo> boardList = boardService.listPaging(crtPage);
		System.out.println(boardList);
		*/
		
		System.out.println(pageMap);
		
		//model.addAttribute("bdList", boardList);
		
		//뷰
		return "board/list";
	}
	
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController > writeForm");
				
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > write");
				
		//파라미터값 넣어준 BoardVo이용 insert메소드 사용
		boardService.insert(boardVo);
		
		//리다이렉트
		return "redirect:/board/List";
	}
	
	@RequestMapping(value="/read", method={RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController > read");
		
		//특정 넘버의 게시글 조회수 1 올리면서 정보 가져오는 메소드 이용
		BoardVo boardVo = boardService.read(no);
		
		//모델로 보내기
		model.addAttribute("boardVo", boardVo);
		
		//뷰		
		return "board/read";
	}
	
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("BoardController > modifyForm");
		
		//특정 넘버의 게시글 정보 가져오는 메소드 이용
		BoardVo binfo = boardService.getboard(no);
		
		//모델을 사용해서 출력
		model.addAttribute("binfo", binfo);
		
		//뷰
		return "board/modifyForm";
	}
	
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController > modify");
		
		//파라미터값을 넣은 BoardVo를 이용하여 수정하는 메소드 이용
		boardService.update(boardVo);
		
		//리다이렉트
		return "redirect:/board/List";
	}
	
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("BoardController > delete");
		
		//특정 넘버의 게시글 삭제하는 메소드
		boardService.delete(no);
		
		//리다이렉트
		return "redirect:/board/List";
	}
	

}
