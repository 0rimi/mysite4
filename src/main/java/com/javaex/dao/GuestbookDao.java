package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//게스트북리스트 출력
	public List<GuestbookVo> getList() {
		System.out.println("getList()");
		
		List<GuestbookVo> gbList = sqlSession.selectList("guestbook.selectList");
		System.out.println(gbList);
		
		return gbList;
	}
	
	//게스트북 값 삽입
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("insert()");
		
		int count  = sqlSession.insert("guestbook.insert", guestbookVo);
		System.out.println(count+"건이 추가되었습니다.");
		
		return count;
	}
	
	//특정 유저 하나 불러오기
	public GuestbookVo getUser(int no) {
		
		GuestbookVo guestinfo = sqlSession.selectOne("guestbook.selectone", no);
		System.out.println(guestinfo.toString());
		
		return guestinfo; 
	}
	
	//게스트북 값 삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("delete()");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		System.out.println(count+"건이 삭제되었습니다.");
		
		return count;
	}
	
	//저장한 후 파라미터값 받아내는 메소드
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("insertSelectKey()");
		
		int count = sqlSession.insert("guestbook.inserSelect", guestbookVo);
		System.out.println(count+"건이 저장되었습니다.");
		
		return count;
	}

}
