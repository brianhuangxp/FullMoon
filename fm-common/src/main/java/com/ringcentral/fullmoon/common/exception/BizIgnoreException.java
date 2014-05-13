package com.ringcentral.fullmoon.common.exception;

import com.ringcentral.fullmoon.common.SysMessage;

public class BizIgnoreException extends BizException {
	public BizIgnoreException(){
		super();
	}

	public BizIgnoreException(String message) {

		super(message);

		// TODO Auto-generated constructor stub

	}

	/**

	 * @param message

	 * @param cause

	 */

	public BizIgnoreException(String message, Throwable cause) {

		super(message, cause);

		// TODO Auto-generated constructor stub

	}

	/**

	 * @param cause

	 */

	public BizIgnoreException(Throwable cause) {

		super(cause);

		// TODO Auto-generated constructor stub

	}

	/**

	 * @param message

	 */

	public BizIgnoreException(SysMessage msg) {

		super();

		this.msg = msg;

		// TODO Auto-generated constructor stub

	}

	/**

	 * @param message

	 * @param cause

	 */

	public BizIgnoreException(SysMessage msg, Throwable cause) {

		super(cause);

		this.msg = msg;

		// TODO Auto-generated constructor stub

	}

	

	public SysMessage getSysMsg() {

	    return this.msg;

	}
	
	public BizIgnoreException(SysMessage msg, String message,Throwable cause){
		super(msg,message,cause);
	}


}
