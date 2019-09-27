package kr.co.itcen.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		
			String url = "jdbc:mariadb://192.168.1.63:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		
		return connection;
	}
	
	public Boolean write(BoardVo vo) {
		int count = sqlSession.insert("board.insert",vo);
		return count == 1;	
	}
	
	public Boolean boardReply(BoardVo vo) {
		int count1 = sqlSession.update("board.writeUpdate",vo);
		int count2 = sqlSession.insert("board.writeInsert",vo);
		return count2 == 1;
	}	
	
	public List<BoardVo> getList(int cCount) {
		cCount = cCount * 5 - 5;
		return sqlSession.selectList("board.getAll",cCount);
	}
	
	public List<BoardVo> getList(int cCount, String search) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		cCount = cCount * 5 - 5;
		

		map.put("cCount", cCount);
		map.put("search", search);
		
	     //for(String key : map.keySet()){
	    	  
	     //     String value =  map.get(key);
	 
	     //       System.out.println(key+" : "+value);
	 
	     //   }
		
		return sqlSession.selectList("board.getKwd",map);
		//return null;
	}	
	
	public BoardVo getBoard(Long titleNo) {
		return sqlSession.selectOne("board.getBoard",titleNo);
	}		
	
	public boolean modify(Long no, String title, String contents) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("no", no);
		map.put("title", title);
		map.put("contents", contents);
		
		int count = sqlSession.update("board.modify",map);
			
		return count == 1;		
	}
		
	public Boolean delete(Long no) {
		int count = sqlSession.update("board.delete",no);
		
		return count == 1;	
	}	

	public Boolean updateHit(Long hit, Long no) {
		HashMap<String, Long> map = new HashMap<String, Long>();

		map.put("hit", hit);
		map.put("no", no);
		
		int count = sqlSession.update("board.updateHit",map);
			
		return count == 1;		
	}
	
	public BoardVo getBoardHit(Long titleNo) {
		return sqlSession.selectOne("board.getBoardHit",titleNo);
	}

	public Long getCount() {
		Long result = -1L;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql = "select count(b.no) as count" + 
						 " from board b, user u" + 
						 " where b.user_no = u.no" +
						 " order by g_no desc";
			
			pstmt = connection.prepareStatement(sql);

			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Long count         = rs.getLong(1);				
				result = count;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public Long getCount(String kwd) {
		Long result = -1L;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql = "select count(b.no) as count" + 
					 " from board b, user u" + 
					 " where b.user_no = u.no" +
					 " and b.title like ?" +
					 " and (b.state = 'y' or b.state = 'u')" +
					 " order by g_no desc";
			
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + kwd + "%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Long count         = rs.getLong(1);				
				result = count;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}		
}
