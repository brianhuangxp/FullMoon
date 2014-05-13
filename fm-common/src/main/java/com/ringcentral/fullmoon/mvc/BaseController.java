package com.ringcentral.fullmoon.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by huangking on 14-1-2.
 */
public class BaseController {
    /** Logger available to subclasses */
    	protected final Log logger = LogFactory.getLog(getClass());

    public boolean isEmpty(Object obj){
        return (obj instanceof  String) ? null == obj || "".equals(obj) : null == obj;
    }
}
