package com.ringcentral.fullmoon.mvc.system;

import com.ringcentral.fullmoon.common.web.utils.ActionParameterUtils;
import com.ringcentral.fullmoon.domain.ap.AccountLogin;
import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.serv.system.AccountService;
import com.ringcentral.fullmoon.domain.vo.AjaxResponseVo;
import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;
import com.ringcentral.fullmoon.domain.vo.Status;
import com.ringcentral.fullmoon.domain.vo.system.UserSession;
import com.ringcentral.fullmoon.mvc.BaseController;
import com.ringcentral.fullmoon.tools.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "index.view/"})
public class IndexController extends AjaxController {
    private static Logger log = Logger.getLogger(IndexController.class);
    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public String displayIndex() {
        return "index";
    }

    @RequestMapping(value = "system/system.login", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    AjaxResponseVo loginAccount(HttpServletRequest request, HttpServletResponse resp, HttpSession session) {
        AjaxResponseVo responseVo = commonFind(request, accountService, AccountLogin.class, "findUserForLogin");
        if (ObjectUtils.isEmpty(responseVo.getResult())) {
            responseVo.setStatus(new Status("0", "fail"));
        } else {
            session.setAttribute(UserSession.USER_SESSION_KEY, responseVo.getResult());
        }
        return responseVo;
    }

    @RequestMapping(value = "system/system.logout", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    AjaxResponseVo logoutAccount(HttpServletRequest req) {
        long currentTime = System.currentTimeMillis();
        Status status;
        try {
            req.getSession(false).invalidate();
            status = new Status("1", "success");
        } catch (Exception e) {
            log.error(e, e.fillInStackTrace());
            status = new Status("0", "fail");
        }
        return putResult(status, null, System.currentTimeMillis() - currentTime);
    }

    @RequestMapping(value = "system/system.findMenuList", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    AjaxResponseVo findMenuList(HttpSession session) {
        long currentTime = System.currentTimeMillis();
        Object o = session.getAttribute(UserSession.USER_SESSION_KEY);
        List<MenuRouteVo> result = new ArrayList<MenuRouteVo>();
        Status status;
        try {
            if (o != null) {
                SysUser u = (SysUser) o;
                result = accountService.findMenuList(u.getUserId());
                status = new Status("1", "success");
            } else {
                status = new Status("0", "fail");
            }
        } catch (Exception e) {
            status = new Status("0", "fail");
        }
        return putResult(status, result, System.currentTimeMillis() - currentTime);
    }
}
