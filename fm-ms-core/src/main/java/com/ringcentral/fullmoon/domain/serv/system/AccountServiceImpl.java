package com.ringcentral.fullmoon.domain.serv.system;

import com.ringcentral.fullmoon.domain.ap.AccountLogin;
import com.ringcentral.fullmoon.domain.bo.system.SysUser;
import com.ringcentral.fullmoon.domain.repo.system.AccountDao;
import com.ringcentral.fullmoon.domain.serv.BaseService;
import com.ringcentral.fullmoon.domain.vo.MenuRouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountServiceImpl extends BaseService implements AccountService {
    @Autowired
    private AccountDao accountDao;

    public SysUser findById(Long id) {

        //TODO some issue.

//		CriteriaBuilder cb = em.getCriteriaBuilder();
////		CriteriaQuery<MemberVo> cq = cb.createQuery(MemberVo.class);
//		CriteriaQuery<MemberVo> cq = cb.createQuery(MemberVo.class);
//		Root<MemberVo> member = cq.from(MemberVo.class);
////		cq.select(member).where(cb.notEqual(member.get("email"), ""));
//
//		PageInfo p = queryWithPage(cq, new PageInfo(2));
//		List<MemberVo> rs = (List<MemberVo>) p.getDataList();
//		for (MemberVo m : rs) {
//			System.out.println(m.getEmail());
//		}
        SysUser account2 = accountDao.findById(id);
//        accountDao.
//        List al = accountDao.sqlQueryForList("select * from sys_gm_role");
//        System.out.println(al.size());
//        System.out.println(account2.getSysGmRoles().size());
        return account2;
    }

    public SysUser findUserForLogin(AccountLogin searchInfo) {
        logger.debug("login user:" + searchInfo.getUserName());
        return accountDao.findUserForLogin(searchInfo.getUserName(), searchInfo.getUserPwd());
    }

    public List<MenuRouteVo> findMenuList(Long userId){
        return accountDao.findMenuList(userId);
    }
}
