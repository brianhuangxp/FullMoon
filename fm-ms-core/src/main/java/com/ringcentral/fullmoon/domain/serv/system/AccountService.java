package com.ringcentral.fullmoon.domain.serv.system;

import com.ringcentral.fullmoon.domain.ap.AccountLogin;
import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.serv.IBaseService;
import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;

import java.util.List;

public interface AccountService extends IBaseService {
    public SysUser findById(Long id);

    public SysUser findUserForLogin(AccountLogin searchInfo);

    public List<MenuRouteVo> findMenuList(Long userId);
}
