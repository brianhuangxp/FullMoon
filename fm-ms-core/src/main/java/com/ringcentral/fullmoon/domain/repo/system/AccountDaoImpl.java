package com.ringcentral.fullmoon.domain.repo.system;

import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.repo.BaseJdbcDaoImpl;
import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AccountDaoImpl extends BaseJdbcDaoImpl implements AccountDao {

    public SysUser findById(Long id) {
        return em.find(SysUser.class, id);
    }


    public SysUser findUserForLogin(String name, String pwd) {
        String hql = "select u From SysUser u where u.userName = ?1 and u.userPwd = ?2 and u.disable <> '1'";
        Query query = em.createQuery(hql, SysUser.class);
        query.setParameter(1, name);
        query.setParameter(2, pwd);
        List resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            SysUser sysUser = (SysUser) resultList.get(0);
            return sysUser;
        }
        return null;
    }

    public List<MenuRouteVo> findMenuList(Long userId){
        Query query = em.createNamedQuery("SysUser.findMenuList");
        query.setParameter(1, userId);
        return query.getResultList();
    }
}
