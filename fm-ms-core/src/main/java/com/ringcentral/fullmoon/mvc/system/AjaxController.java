package com.ringcentral.fullmoon.mvc.system;

import com.ringcentral.fullmoon.common.web.utils.ActionParameterUtils;
import com.ringcentral.fullmoon.domain.ap.BaseAp;
import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.ap.UserBaseAp;
import com.ringcentral.fullmoon.domain.serv.IBaseService;
import com.ringcentral.fullmoon.domain.vo.AjaxResponseVo;
import com.ringcentral.fullmoon.domain.vo.Status;
import com.ringcentral.fullmoon.domain.vo.system.UserSession;
import com.ringcentral.fullmoon.mvc.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by brain.huang on 14-1-8.
 */
public class AjaxController extends BaseController {
    public AjaxResponseVo putResult(Status status, Object result, long spendTime){
        AjaxResponseVo vo = new AjaxResponseVo(status, result, spendTime);
        return vo;
    }

    public AjaxResponseVo commonFind(HttpServletRequest request, IBaseService service, String loginDataServiceMethodName) {
        return this.commonFind(request, service, UserBaseAp.class, loginDataServiceMethodName);
    }

    public AjaxResponseVo commonFind(HttpServletRequest request, IBaseService service, Class<? extends BaseAp> apClass, String loginDataServiceMethodName) {
        long currentTime = System.currentTimeMillis();
        try {
            BaseAp baseAp = ActionParameterUtils.request2Ap(request, apClass);
            if(baseAp instanceof UserBaseAp){
                UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
                ((UserBaseAp) baseAp).setUserId(userSession.getUser().getUserId());
            }
            Object result = service.getClass().getMethod(loginDataServiceMethodName, apClass).invoke(service, baseAp);
            return putResult(new Status("1", "success"), result,
                    System.currentTimeMillis() - currentTime);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return putResult(new Status("0", "fail", e), null,
                    System.currentTimeMillis() - currentTime);
        }
    }
}
