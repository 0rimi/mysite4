package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.BoardService;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	//필드
	@Autowired
	private BoardService boardService;

}
