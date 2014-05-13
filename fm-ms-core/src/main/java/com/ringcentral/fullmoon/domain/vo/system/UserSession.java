package com.ringcentral.fullmoon.domain.vo.system;

import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.tools.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserSession {
    public final static String USER_SESSION_KEY = "sessionUser";

    private SysUser user;

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public ClientUserVo buildClientUserVo() {
        if (!ObjectUtils.isEmpty(user))
            return new ClientUserVo(user.getUserName(), user.getNickName());
        return null;
    }
}
