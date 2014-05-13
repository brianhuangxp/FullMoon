package com.ringcentral.fullmoon.mvc.system;

import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.vo.system.UserSession;
import com.ringcentral.fullmoon.mvc.BaseController;
import com.ringcentral.fullmoon.tools.DateUtils;
import com.ringcentral.fullmoon.tools.ObjectUtils;
import com.ringcentral.fullmoon.tools.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by huangking on 14-1-3.
 */
@Controller
@RequestMapping(value = "/system/")
public class SystemConfigController extends BaseController {
    @RequestMapping(value = "configJs", method = RequestMethod.GET, produces = "text/javascript")
    public
    @ResponseBody
    String bulidingJsConfig(HttpServletRequest request, HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer();
        String projectPath = request.getContextPath() + "/";
        sb.append("RC.Config={");
        sb.append("		projectPath: '" + projectPath + "',");
        sb.append("		resourcePath: '" + projectPath + "resources/',");
        sb.append("		componentsPath: '" + projectPath + "components/',");
        sb.append("		dateFormat: '" + DateUtils.DEFAULT_DATE_FORMAT + "'");
        sb.append("};");

        return sb.toString();
    }

    @RequestMapping(value = "userConfigJs", method = RequestMethod.GET, produces = "text/javascript")
    public
    @ResponseBody
    String bulidingJsUserConfig(HttpServletRequest request, HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer();
        HttpSession session = request.getSession();
        Object sk = session.getAttribute("securityKey");
        String securityKey = "";

        Object o = session.getAttribute(UserSession.USER_SESSION_KEY);
        SysUser user = (o != null) ? (SysUser) o : null;
        if (ObjectUtils.isEmpty(sk)) {
            securityKey = SecurityUtils.getInstance().generateKey();
            session.setAttribute("securityKey", securityKey);
        } else {
            securityKey = sk.toString();
        }
        sb.append("(function(config){");
        sb.append("		config.skid = '" + securityKey + "';");
        if(isEmpty(user)){
            sb.append("		config.sessionTimeout = 'true';");
        }else{
            sb.append("		config.sessionTimeout = 'false';");
            sb.append("		config.userNickName = '" + user.getNickName() + "';");
        }

        // todo make config for isCookieMenu
        sb.append("		config.isCookieMenu= 'true';");
        sb.append("})(RC.Config);");
        return sb.toString();
    }
}
