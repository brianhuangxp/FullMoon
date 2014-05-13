/*
* Id: BizException.java
* Type Name: com.ccb.cclbm.common.BizException
* Create Date: 2005-3-15
* Author: robert.luo
* 
*
* Project: CCLBM
*
*
*
*/
package com.ringcentral.fullmoon.common.exception;

import com.ringcentral.fullmoon.common.SysMessage;

import java.io.Serializable;

/**
 * Common Exception threw from biz tier
 * 
 * @author robert.luo
 * @version 1.1 zhangfeng add new constrct method [public BizException(SysMessage msg, String message,Throwable cause)]
 */
public class BadDataException extends BizException implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * SysMessage
	 */
	protected SysMessage msg;

	/**
	 *
	 */
	public BadDataException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 */
	public BadDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 * @param cause
	 */
	public BadDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param cause
	 */
	public BadDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param message
	 */
	public BadDataException(SysMessage msg) {
		super();
		this.msg = msg;
		// TODO Auto-generated constructor stub
	}
	/**
	 * zhangfeng V1.2
	 * @param msg
	 * @param message
	 * @param cause
	 */
	public BadDataException(SysMessage msg, String message, Throwable cause){
		super(message,cause);
		this.msg = msg;
	}
	/**
	 * @param message
	 * @param cause
	 */
	public BadDataException(SysMessage msg, Throwable cause) {
		super(cause);
		this.msg = msg;
		// TODO Auto-generated constructor stub
	}
	
	public SysMessage getSysMsg() {
	    return this.msg;
	}
}
