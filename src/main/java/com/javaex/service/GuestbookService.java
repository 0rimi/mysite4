package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	//리스트출력
	public List<GuestbookVo> List(){
		
		List<GuestbookVo> gbList = guestbookDao.getList();
		
		return gbList;
	}
	
	
	//저장
	public void insert(GuestbookVo guestbookVo) {
		
		guestbookDao.insert(guestbookVo);
		System.out.println(guestbookVo);
		
	}
	
	//특정유저 인포 가져오기
	public GuestbookVo getUser(int no) {
		
		GuestbookVo guestinfo = guestbookDao.getUser(no);
		
		return guestinfo;
	}
	
	//삭제
	public void delete(GuestbookVo guestbookVo) {
		
		guestbookDao.delete(guestbookVo);
	}
	
	//방명록 글 저장--> 저장글 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
	System.out.println("guestbookService/addGuestResultVo");
	
	//저장하기
	guestbookDao.insertSelectKey(guestbookVo);
	
	//저장한 내용 가져오기
	int no = guestbookVo.getNo();
	return guestbookDao.getUser(no);
	}
	
	//방명록 글 삭제
	public int removeGues3t(GuestbookVo guestbookVo){
		System.out.println("guestbookService/remove");
		
		return guestbookDao.delete(guestbookVo);
	}
	
}
