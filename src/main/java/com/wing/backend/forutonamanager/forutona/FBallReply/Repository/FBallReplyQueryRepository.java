package com.wing.backend.forutonamanager.forutona.FBallReply.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wing.backend.forutonamanager.forutona.FBallReply.Domain.FBallReply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.wing.backend.forutonamanager.forutona.FBallReply.Domain.QFBallReply.fBallReply;

@Repository
public class FBallReplyQueryRepository {

    @PersistenceContext(unitName = "forutonaApp")
    EntityManager em;

    public List<FBallReply> findByReplyUuidToFBallReply(List<String> replyUuid){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        return queryFactory.select(fBallReply)
                .from(fBallReply)
                .where(fBallReply.replyUuid.in(replyUuid))
                .fetch();
    }
}
