package com.ringcentral.fullmoon.mvc.rest.temp;

import com.ringcentral.fullmoon.common.web.utils.ActionParameterUtils;
import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.serv.temp.MemberService;
import com.ringcentral.fullmoon.domain.vo.AjaxResponseVo;
import com.ringcentral.fullmoon.domain.vo.Status;
import com.ringcentral.fullmoon.domain.vo.temp.MemberVo;
import com.ringcentral.fullmoon.mvc.system.AjaxController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangking on 14-1-8.
 */
@Controller
@RequestMapping("/json/temp/member")
public class MemberController extends AjaxController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/member.findMemberList", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    AjaxResponseVo findUserTranslate(HttpServletRequest request) {
        long currentTime = System.currentTimeMillis();
        List<MemberVo> result = new ArrayList<MemberVo>();
        Status status;
        CommonSearchAp commonSearchAp = (CommonSearchAp) ActionParameterUtils.request2Ap(request, CommonSearchAp.class);
        try {
            result = memberService.findMemberList(commonSearchAp);
            status = new Status("1", "success");
        } catch (Exception e) {
            e.printStackTrace();
            status = new Status("0", "fail");
        }
        return putResult(status, result, System.currentTimeMillis() - currentTime);
    }

}
