package kr.co.itcen.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.itcen.mysite.repository.UserDao;
import kr.co.itcen.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired //주입
	private UserDao userDao;

	public void join(UserVo vo) {
		userDao.insert(vo);		
	}

	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
		
	}
	
	public UserVo getUpdateUser(Long userNo) {
		return userDao.get(userNo);
		
	}
	
	public void update(UserVo vo) {
		userDao.update(vo);
	
	}

	public Boolean existUser(String email) {

		return userDao.get(email) != null;
	}
	
}
