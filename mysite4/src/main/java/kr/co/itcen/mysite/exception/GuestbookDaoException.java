package kr.co.itcen.mysite.exception;

public class GuestbookDaoException extends RuntimeException {
	public GuestbookDaoException() {
		super("UserDaoException Occurs");
		
	}
	
	public GuestbookDaoException(String message) {
		super(message);
		
	}

}
