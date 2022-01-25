package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RBoardVo;

@Repository
public class RboardService {
	
	@Autowired
	private RboardDao rboardDao;
	
	//리스트 출력
	public List<RBoardVo> list(){
		
		List<RBoardVo> rbdList = rboardDao.list();
		
		return rbdList;
	}

}
