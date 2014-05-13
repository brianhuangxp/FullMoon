package com.ringcentral.fullmoon.domain.repo.system;

import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.repo.BaseJdbcDAO;
import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;

import java.util.List;


public interface AccountDao extends BaseJdbcDAO
{
    public SysUser findById(Long id);

    public SysUser findUserForLogin(String name, String pwd);

    public List<MenuRouteVo> findMenuList(Long userId);
}
