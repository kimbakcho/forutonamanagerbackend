package com.wing.backend.forutonamanager.manager.Notice.Repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NoticeQueryRepository {

    @PersistenceContext
    EntityManager em;


}
