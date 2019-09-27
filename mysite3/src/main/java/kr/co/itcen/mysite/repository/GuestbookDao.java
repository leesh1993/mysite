package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert",vo);
		return count == 1;	
	}
	
	
	public void delete(GuestbookVo vo) {
		sqlSession.insert("guestbook.delete",vo);
	}	

	public List<GuestbookVo> getList() {
		return sqlSession.selectList("guestbook.getAll");
	}	
}
