package com.ringcentral.fullmoon.domain.serv;

import com.ringcentral.fullmoon.common.exception.RollbackableBizException;
import com.ringcentral.fullmoon.common.system.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = RollbackableBizException.class)
public class BTService extends BaseService {

}
