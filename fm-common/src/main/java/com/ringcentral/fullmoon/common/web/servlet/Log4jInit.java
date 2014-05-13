package com.ringcentral.fullmoon.common.web.servlet;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by brain.huang on 14-1-3.
 * TODO jboss 7 log4j has some issue, waiting ...
 */
public class Log4jInit extends HttpServlet {

    /**
     * init
     */
    public void init() {
        // String prefix = getServletContext().getRealPath("/"); //only succeed
        // in unpacked web app

        String file = getInitParameter("log4j-init-file");

        InputStream is = getServletContext().getResourceAsStream(file);
        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PropertyConfigurator.configure(props);

		/*
		 * // if the log4j-init-file is not set, then no point in trying if(file !=
		 * null) { PropertyConfigurator.configure(prefix+file); }
		 */
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
    }

    private String getWebRoot() {
        String strClassName = getClass().getName();
        String strPackageName = "";
        if (getClass().getPackage() != null) {
            strPackageName = getClass().getPackage().getName();
        }

        String strClassFileName = "";
        if (!"".equals(strPackageName)) {
            strClassFileName = strClassName.substring(strPackageName.length() + 1, strClassName.length());
        } else {
            strClassFileName = strClassName;
        }

        URL url = getClass().getResource(strClassFileName + ".class");
        String strURL = url.toString();
        strURL = strURL.substring(strURL.indexOf(':') + 1, strURL.indexOf("/WEB-INF/"));
        strURL = strURL.replaceAll("%20", " ");
        return strURL;
    }

}
