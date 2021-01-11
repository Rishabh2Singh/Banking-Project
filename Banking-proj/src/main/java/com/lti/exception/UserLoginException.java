package com.lti.exception;

public class UserLoginException extends Exception {

	public UserLoginException() {
		super();
	}

	public UserLoginException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UserLoginException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserLoginException(String arg0) {
		super(arg0);
	}

	public UserLoginException(Throwable arg0) {
		super(arg0);
	}

	

}
