package kr.co.itcen.mysite.exception;

public class BoardDaoException  extends RuntimeException {
	public BoardDaoException() {
		super("UserDaoException Occurs");
		
	}
	
	public BoardDaoException(String message) {
		super(message);
		
	}

}


