package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트출력 메소드
	public List<BoardVo> list(){
		
		List<BoardVo> bdList = sqlSession.selectList("board.selectList");
		
		return bdList;
	}
	
	//조회수 올리는 메소드
	public void upread(int no) {
		sqlSession.update("board.updatehit", no); 
		System.out.println("조회수 1증가");
	}
	
	//특정 넘버 게시글 셀렉트 메소드
	public BoardVo getboard(int no) {
		
		BoardVo boardVo = sqlSession.selectOne("board.selectone", no);
		System.out.println(boardVo);
		
		return boardVo;
	}
	
	//insert(게시글등록)
	public int insert(BoardVo boardVo) {
		int count = sqlSession.insert("board.insert", boardVo);
		System.out.println(count + "건이 등록되었습니다.");
		
		return count;
	}
	
	//수정하는 메소드
	public int update(BoardVo boardVo) {
		int count = sqlSession.update("board.update", boardVo);
		System.out.println(count + "건이 수정되었습니다.");
		
		return count;
	}

	//삭제하는 메소드
	public int delete(int no) {
		int count = sqlSession.delete("board.delete", no);
		System.out.println(count + "건이 삭제되었습니다.");
		
		return count;
	}
	
	//리스트페이징
	public List<BoardVo> pageList(int sn, int en) {
		System.out.println("boardDao.selectList");
		System.out.println(sn +"," + en);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("sn", sn);
		map.put("en", en);
		
		List<BoardVo> boardList = sqlSession.selectList("listpaging", map);
		
		return boardList;
	}
	
	//전체 글 갯수
	public int selectTotal() {
		System.out.println("boardDao.selectTotal");
		
		int Cnt = sqlSession.selectOne("board.totalCnt");
		
		return Cnt;
	}
	
}
