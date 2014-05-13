/*
* Id: SysLogger.java
* Type Name: com.ccb.cclbm.common.SysLogger
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

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

/**
 * @author robert.luo
 *
 * TODO 
 */
public class SysLogger {
	
	public static Log getLog(Object obj){
		return LogFactory.getLog(obj.getClass());
	}
	
}
