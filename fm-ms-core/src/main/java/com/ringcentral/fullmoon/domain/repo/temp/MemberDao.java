package com.ringcentral.fullmoon.domain.repo.temp;

import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.vo.temp.MemberVo;

import java.util.List;

/**
 * Created by brain.huang on 14-1-22.
 */
public interface MemberDao {
    public List<MemberVo> findMemberList(CommonSearchAp commonSearchAp);
}
