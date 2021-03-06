package kr.co.itcen.mysite.dto;

public class JSONResult {
	private String result;   /* sucess or fail */
	private Object data;     /* if sucess, set*/
	private String message; /* if fail, set*/
	
	public String getResult() {
		return result;
	}
	
	public Object getData() {
		return data;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static JSONResult success(Object data) {
		return new JSONResult(data);
	}

	public static JSONResult fail(String message) {
		return new JSONResult(message);
	}

	private JSONResult() {
	}

	private JSONResult(Object data) {
		this.result = "success";
		this.data = data;
	}

	private JSONResult(String message) {
		this.result = "fail";
		this.message = message;
	}
	


	
	
}
