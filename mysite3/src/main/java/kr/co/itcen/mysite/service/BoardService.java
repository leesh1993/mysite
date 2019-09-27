package kr.co.itcen.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;


@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao; 
	
	public Boolean write(BoardVo vo) {
		return boardDao.write(vo);
	}
	
	public Boolean boardReply(BoardVo vo) {
		return boardDao.boardReply(vo);
	}
	
	public List<BoardVo> getList(int cCount) {
		
		return boardDao.getList(cCount);
	}
	
	public List<BoardVo> getList(int cCount, String search) {
		
		return boardDao.getList(cCount, search);	
	}
	
	public BoardVo getBoard(Long titleNo) {
		
		return boardDao.getBoard(titleNo);
	}
	
	public boolean modify(Long no, String title, String contents) {
		
		return boardDao.modify(no, title, contents);
	}
	
	public Boolean delete(Long no) {
		
		return boardDao.delete(no);
	}
	
	public Boolean updateHit(Long hit, Long no) {
		
		return boardDao.updateHit(hit, no);
	}
	
	public BoardVo getBoardHit(Long titleNo) {
		
		return boardDao.getBoardHit(titleNo);
	}
	
	public Long getCount() {
		
		return boardDao.getCount();
	}
	
	public Long getCount(String kwd) {
		
		return boardDao.getCount(kwd);
	}
	
		
}
