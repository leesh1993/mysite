package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.exception.UserDaoException;
import kr.co.itcen.mysite.vo.GuestbookVo;
import kr.co.itcen.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;		
	
	public Boolean insert(UserVo vo) throws UserDaoException {
		int count = sqlSession.insert("user.insert",vo);
		return count == 1;	
	}
	
	public UserVo get(UserVo vo) {
		return sqlSession.selectOne("user.getByEmailAndPassword",vo);
	}
	
	
	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo",no);	
	}
	
	public boolean update(UserVo vo) {		
		int count = sqlSession.update("user.update", vo);
		
		return count == 1;
	}
	
}
