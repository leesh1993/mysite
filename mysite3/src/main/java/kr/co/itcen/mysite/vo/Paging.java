package kr.co.itcen.mysite.vo;

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

}
