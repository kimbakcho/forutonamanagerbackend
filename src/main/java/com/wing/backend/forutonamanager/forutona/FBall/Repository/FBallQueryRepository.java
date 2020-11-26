package com.wing.backend.forutonamanager.forutona.FBall.Repository;


import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wing.backend.forutonamanager.forutona.FBall.Domain.QFBall;
import com.wing.backend.forutonamanager.forutona.FBall.Dto.FBallSimpleResDto;
import com.wing.backend.forutonamanager.forutona.FBall.Dto.QFBallSimpleResDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.wing.backend.forutonamanager.forutona.FBall.Domain.QFBall.fBall;



@Component
public class FBallQueryRepository {

    @PersistenceContext(unitName = "forutonaApp")
    EntityManager em;

    public List<FBallSimpleResDto> findByBallUuidsToFBallSimpleData(List<String> ballUuids){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<FBallSimpleResDto> fetch = queryFactory
                .select(new QFBallSimpleResDto(fBall.ballUuid,
                        fBall.uid, fBall.ballName,
                        fBall.makeTime, fBall.ballHits))
                .from(fBall)
                .where(fBall.ballUuid.in(ballUuids))
                .fetch();
        return fetch;

    }

}
