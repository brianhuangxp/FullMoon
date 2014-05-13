package com.ringcentral.fullmoon.common.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private String checkAttribute;
    private String failPage;
	private List<String> mappingURL = new ArrayList<String>();

	public String getCheckAttribute() {
		return checkAttribute;
	}

	public void setCheckAttribute(String checkAttribute) {
		this.checkAttribute = checkAttribute;
	}

	public List<String> getMappingURL() {
		return mappingURL;
	}

	public void setMappingURL(List<String> mappingURL) {
		this.mappingURL = mappingURL;
	}

    public String getFailPage() {
        return failPage;
    }

    public void setFailPage(String failPage) {
        this.failPage = failPage;
    }

    @Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
        if((request.getContextPath() + "/" + failPage).equals(request.getRequestURI())){
            return true;
        }
        if(allowExternalSystem(request, url)){
            return true;
        }
        System.out.println(url);
//        System.out.println(request.getCookies().toString());

        boolean visitFlag = true;
		for (String matchUrl : mappingURL) {
			if (url.matches(matchUrl)) {
                visitFlag = authorize(request, url, matchUrl);
			}
		}
		if(mappingURL.size() == 0){
            visitFlag = authorize(request, url, ".");
		}
        if(!visitFlag){
            //todo 404 status
            response.sendRedirect(request.getContextPath() + "/" + failPage);
        }
		return visitFlag;
	}

    /**
     *  Check user if login or not, need to overwrite
     * @param request
     * @return
     */
	protected boolean authorize(HttpServletRequest request, String url, String matchUrl) {
		return true;
	}

    /**
     * Check external system visit no need login in, need to overwrite
     * @param request
     * @param url
     * @return
     */
    protected boolean allowExternalSystem(HttpServletRequest request, String url) {
        return false;
    }
}
