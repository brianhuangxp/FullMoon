package com.ringcentral.fullmoon.common.exception;

import java.io.Serializable;

import com.ringcentral.fullmoon.common.SysMessage;

/**
 * �ɻع���ҵ���쳣��
 * ��BizException��ֱ��ʵ���࣬�����б���߼���BizExceptionһ�£���Ϊ��ǣ���������ʶ�𡣴�������Ļع�
 * 
 */
public class RollbackableBizException extends BizException implements Serializable{
	/**
	 * �쳣�汾��
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * һ����쳣���죬���ڲ����쳣���룬������ʹ��
	 */
	public RollbackableBizException() {
		super();
	}
	/**
	 * @see cn.ccb.clpm.common.BizException
	 */
	public RollbackableBizException(String message) {
		super(message);

	}
	/**
	 * @see cn.ccb.clpm.common.BizException
	 * @param message
	 * @param cause
	 */
	public RollbackableBizException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @see cn.ccb.clpm.common.BizException
	 * @param message
	 * @param cause
	 */
	public RollbackableBizException(SysMessage msg, String message,Throwable cause) {
		super(msg,message, cause);
	}

	/**
	 * @see cn.ccb.clpm.common.BizException
	 * @param msg
	 */
	public RollbackableBizException(SysMessage msg) {
		super(msg);
	}
	/**
	 * @see cn.ccb.clpm.common.BizException
	 * @param msg
	 * @param cause
	 */
	public RollbackableBizException(SysMessage msg, Throwable cause) {
		super(msg, cause);
	}
	/**
	 * @see cn.ccb.clpm.common.BizException
	 * @param cause
	 */
	public RollbackableBizException(Throwable cause) {
		super(cause);
	}
}
