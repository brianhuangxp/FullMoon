package com.ringcentral.fullmoon.domain.repo;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl implements BaseDao{
	@Autowired
	protected EntityManager em;

}
