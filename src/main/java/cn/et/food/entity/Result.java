package cn.et.food.entity;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Result {
	/**
	 * 0代表成功，1代表失败
	 */
	private int code;
	/**
	 * 存放错误信息
	 */
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		if(message!=null && message.length()>1000){
			return message.substring(0,300);
		}		
		return message;
	}

	public void setMessage(Exception msg) {
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		msg.printStackTrace(new PrintStream(byteArrayOutputStream));
		
		this.message = new String(byteArrayOutputStream.toByteArray());
	}

}
