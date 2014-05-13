/*
* Id: SysEnv.java
* Type Name: com.ccb.cclbm.common.SysEnv
* Create Date: 2005-3-16
* Author: robert.luo
* 
*
* Project: CCLBM
*
*
*
*/
package com.ringcentral.fullmoon.common;

import java.util.Locale;
import java.io.Serializable;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Environment info retrieved from resource bundle, J2EE context, etc.
 *  
 * @author robert.luo
 *
 */
public class SysEnv implements Serializable{
	/**
	 * default locale
	 */
	private static final Locale DEFAULT_LOCALE = Locale.CHINA;
	
	private static final String ENV_QNAME = "Env";
	/**
	 * resource bundle
	 */
	private static ResourceBundleMessageSource bundle = null;
	
	/**
	 * read resource bundle
	 * @return resource bundle
	 */
	public static ResourceBundleMessageSource getBundle(){
		if(bundle == null){
			bundle = new ResourceBundleMessageSource();
			bundle.setBasename(ENV_QNAME);
		}
		return bundle;
	}
	/**
	 * debug flag
	 */
	private static Boolean isDebug;
	/**
	 * @return Returns the isDebug.
	 */
	public static Boolean isDebug() {
		if(isDebug == null){
			isDebug = Boolean.valueOf(getBundle().getMessage("debug", null, DEFAULT_LOCALE)) ;
		}
		return isDebug;
	}
	/**
	 * @param isDebug The isDebug to set.
	 */
	public static void setDebug(Boolean isDebug) {
		SysEnv.isDebug = isDebug;
	}

	/**
	 * getter of reprotRootURL 
	 * @return
	 */
	private static String reprotRootURL;
	
	
	public static String getReprotRootURL() {
		if(reprotRootURL == null){
			reprotRootURL = getBundle().getMessage("reprotRootURL", null, DEFAULT_LOCALE);
		}
		return reprotRootURL;
	}
	
	public static void setReprotRootURL(String reprotRootURL) {
		SysEnv.reprotRootURL = reprotRootURL;
	}


    private static String resourcesRootPath;

    public static String getResourcesRootPath()
    {
        if( resourcesRootPath==null )
        {
            resourcesRootPath = getBundle().getMessage( "resourcesRootPath", null, DEFAULT_LOCALE );
        }
        return resourcesRootPath;
    }

    public static void setResourcesRootPath(String resourcesRootPath)
    {
        SysEnv.resourcesRootPath = resourcesRootPath;
    }
    

	
	private static String initialContextFactory;
	
	public static String getInitialContextFactory() {
		if(initialContextFactory == null){
			initialContextFactory = getBundle().getMessage("initialContextFactory",null, DEFAULT_LOCALE);
		}
		return initialContextFactory;
	}
	

}
