package com.orange.core.exception;

/**
 * 权限异常
 * 
 */
public class PrivilegeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param string
	 */
	public PrivilegeException(String arg0) {
		super(arg0);
	}

}
