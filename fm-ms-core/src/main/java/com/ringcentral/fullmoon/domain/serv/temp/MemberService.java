package com.ringcentral.fullmoon.domain.serv.temp;

import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.vo.temp.MemberVo;

import java.util.List;

public interface MemberService {
    public List<MemberVo> findMemberList(CommonSearchAp commonSearchAp);
}
