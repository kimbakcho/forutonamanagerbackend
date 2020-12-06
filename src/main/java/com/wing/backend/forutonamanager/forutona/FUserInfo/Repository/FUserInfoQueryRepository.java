package com.wing.backend.forutonamanager.forutona.FUserInfo.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.FUserInfo;
import com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.QFUserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.wing.backend.forutonamanager.forutona.FUserInfo.Domain.QFUserInfo.fUserInfo;

@Repository
public class FUserInfoQueryRepository {

    @PersistenceContext(unitName = "forutonaApp")
    EntityManager em;

    public List<FUserInfo> findByUidToFUserInfo(List<String> uidList){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        return queryFactory.select(fUserInfo)
                .from(fUserInfo)
                .where(fUserInfo.uid.in(uidList))
                .fetch();
    }
}
