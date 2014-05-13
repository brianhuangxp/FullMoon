package com.ringcentral.fullmoon.domain.repo.temp;

import com.ringcentral.fullmoon.domain.ap.CommonSearchAp;
import com.ringcentral.fullmoon.domain.repo.BaseDaoImpl;
import com.ringcentral.fullmoon.domain.vo.temp.MemberVo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by huangking on 14-1-8.
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl implements MemberDao {
    public List<MemberVo> findMemberList(CommonSearchAp commonSearchAp){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from MEMBER where 1 =1 ");
// oracle
//        sb.append("and record_time between to_date(?1,'yyyy/MM/dd') and to_date(?2,'yyyy/MM/dd') ");
// mysql
        sb.append("and record_time between str_to_date(?1,'%Y/%m/%d') and str_to_date(?2,'%Y/%m/%d') ");
        Query query = em.createNativeQuery(sb.toString(), MemberVo.class);
        query.setParameter(1, commonSearchAp.getStartDate());
        query.setParameter(2, commonSearchAp.getEndDate());
        List<MemberVo> voList = query.getResultList();
        return voList;
    }
}
