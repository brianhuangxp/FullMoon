package com.ringcentral.fullmoon.domain.serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ringcentral.fullmoon.common.exception.RollbackableBizException;
import com.ringcentral.fullmoon.common.system.PageInfo;
import com.ringcentral.fullmoon.domain.bo.BaseBo;

public class BaseService implements IBaseService {
	@Autowired
	protected EntityManager em;

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
//	 public List<BaseBo> findPostBySitePaged2(Set sites, Page page) {
//		    List<BaseBo> result = new ArrayList<BaseBo>();
//		    if (sites == null || sites.size() == 0) return result;
//
//		    try {
//		        CriteriaBuilder builder = em.getCriteriaBuilder();
//
//		        CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
//		        Root<BaseBo> root = cQuery.from(BaseBo.class);
//
//		        CriteriaQuery<Long> select = cQuery.select(builder.count(root));
//		        select.where(root.get("site").in(sites));
//
//		        
//		        TypedQuery<Long> typedQuery = em.createQuery(select);
//		        Long t = typedQuery.getSingleResult();
//
//		        int tot = t.intValue() / page.getPageSize() + ((t.intValue() % page.getPageSize()) == 0 ? 0 : 1);
//		        page.setTotal(tot);
//
//		        CriteriaQuery<BaseBo> criteriaQuery = builder.createQuery(BaseBo.class);
//		        root = criteriaQuery.from(BaseBo.class);
//		        criteriaQuery.select(root).where(root.get("site").in(sites)).orderBy(builder.desc(root.get("createDate")));
//
//		        TypedQuery<BaseBo> postQuery = em.createQuery(criteriaQuery);
//		        postQuery.setFirstResult((page.getCurrent() - 1) * (page.getPageSize())).setMaxResults(page.getPageSize());
//
//		        result = postQuery.getResultList();
//
//		    } catch (Exception e) {
//		    	//todo  add log4j
////		        log.error(e.getMessage());
//		    }
//
//		    return result;
//		}
	 
	 
	public PageInfo queryWithPage(CriteriaQuery<?> criteriaQuery,
			PageInfo page) {
		List<?> result = new ArrayList();
		try {
			Long t = count(criteriaQuery);
			page.setTotalCount(t.intValue());
			TypedQuery<?> postQuery = em.createQuery(criteriaQuery);
			postQuery.setFirstResult(
					(page.getPageIndex() - 1) * (page.getPageSize()))
					.setMaxResults(page.getPageSize());
			result = postQuery.getResultList();
		} catch (Exception e) {
			// todo add log4j
			// log.error(e.getMessage());
			e.printStackTrace();
		}

		page.setDataList(result);
		return page;
	}
	 
	public Long count(final CriteriaQuery<?> criteria) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		countCriteria.select(builder.count(criteria.getRoots().iterator().next()));
		Predicate groupRestriction = criteria.getGroupRestriction(), fromRestriction = criteria.getRestriction();
		if (groupRestriction != null) {
			countCriteria.having(groupRestriction);
		}
		if (fromRestriction != null) {
			countCriteria.where(fromRestriction);
		}
		countCriteria.groupBy(criteria.getGroupList());
		countCriteria.distinct(criteria.isDistinct());
		return em.createQuery(countCriteria).getSingleResult();
	}

}
