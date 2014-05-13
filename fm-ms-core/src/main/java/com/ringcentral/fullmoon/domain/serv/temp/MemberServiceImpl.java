package com.ringcentral.fullmoon.domain.serv.temp;

import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.repo.temp.MemberDao;
import com.ringcentral.fullmoon.domain.serv.BaseService;
import com.ringcentral.fullmoon.domain.vo.temp.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberServiceImpl extends BaseService implements MemberService {
    @Autowired
    private MemberDao memberDao;

    public List<MemberVo> findMemberList(CommonSearchAp commonSearchAp){
        return memberDao.findMemberList(commonSearchAp);
    }
}
