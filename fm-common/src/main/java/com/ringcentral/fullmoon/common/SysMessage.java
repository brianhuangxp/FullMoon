/*
* Id: SysMessage.java
* Type Name: com.ccb.cclbm.common.SysMessage
* Create Date: 2005-3-15
* Author: robert.luo
* 
*
* Project: CCLBM
*
*
*
*/
package com.ringcentral.fullmoon.common;

import java.io.Serializable;

/**
 * Message Entity contains message key and replacable values
 *  
 * @author robert.luo
 *
 */
public class SysMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * message key to identify the message in resource bundle (required)
	 */
	protected String key;
	
	/**
	 * parameter values for message placeholder (optional)
	 */
	protected Object params[];
	
	
	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return Returns the params.
	 */
	public Object[] getParams() {
		return params;
	}
	/**
	 * @param params The params to set.
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}
	/**
	 * @param key
	 * @param params
	 */
	public SysMessage(String key, Object[] params) {
		super();
		this.key = key;
		this.params = params;
	}
	/**
	 * @param key
	 */
	public SysMessage(String key) {
		super();
		this.key = key;
	}
}
