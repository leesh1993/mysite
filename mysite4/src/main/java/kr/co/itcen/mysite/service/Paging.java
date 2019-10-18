package kr.co.itcen.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.itcen.mysite.repository.BoardDao;

@Service
public class Paging {
	
	@Autowired
	private BoardDao boardDao; 
	
    private final static int pageCount = 5;
    private int blockStartNum = 0;
    private int blockLastNum = 0;
    private int lastPageNum = 0;
    private Long totalCount = 0L;
     
    public int getBlockStartNum() {
        return blockStartNum;
    }
    public void setBlockStartNum(int blockStartNum) {
        this.blockStartNum = blockStartNum;
    }
    public int getBlockLastNum() {
        return blockLastNum;
    }
    public void setBlockLastNum(int blockLastNum) {
        this.blockLastNum = blockLastNum;
    }
    public int getLastPageNum() {
        return lastPageNum;
    }
    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }
     
    public Long getTotalCount() {
		return totalCount;
	}
    
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public static int getPagecount() {
		return pageCount;
	}
	
	// block을 생성
    // 현재 페이지가 속한 block의 시작 번호, 끝 번호를 계산
    public void makeBlock(int curPage){
        int blockNum = 0;

        blockNum = (int)Math.floor((curPage-1)/ pageCount);
        blockStartNum = (pageCount * blockNum) + 1;
        blockLastNum = blockStartNum + (pageCount-1);
    }

    // 총 페이지의 마지막 번호
    public int makeLastPageNum() {
    	
        //BoardDao dao = new BoardDao();
    	totalCount = boardDao.getCount();
    	
        if( totalCount % pageCount == 0 ) {
            return lastPageNum = (int)Math.floor(totalCount/pageCount);
        }
        else {
        	return lastPageNum = (int)Math.floor(totalCount/pageCount) + 1;
        }
    }

    // 검색을 했을 때 총 페이지의 마지막 번호
    public int makeLastPageNum(String kwd) {

    	//BoardDao dao = new BoardDao();
    	totalCount = boardDao.getCount(kwd);

        if( totalCount % pageCount == 0 ) {
            return lastPageNum = (int)Math.floor(totalCount/pageCount);
        }
        else {
        	return lastPageNum = (int)Math.floor(totalCount/pageCount) + 1;
        }
    }

}
