package com.management.forutona.AuthDao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.firebase.auth.UserInfo;
import com.management.forutona.AuthDto.Userinfo;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Component
public class AuthDao {
    @Resource(name = "WebsqlSession")
    private SqlSession WebsqlSession;

    public Userinfo Login(Userinfo info){
        UserinfoMapper mapper = WebsqlSession.getMapper(UserinfoMapper.class);
        Userinfo item = mapper.selectByPrimaryKey(info.getUid());
        if(item.getPassword().equals(info.getPassword())){
            Algorithm algorithm = Algorithm.HMAC256("neoforutona1020");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            //토큰 인증 2시간
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            String token = JWT.create()
                    .withIssuer("forutona")
                    .withExpiresAt(calendar.getTime())
                    .withClaim("uid",item.getUid())
                    .withClaim("nickname",item.getNickname())
                    .sign(algorithm);
            System.out.println(token);
            item.setAccesstoken(token);
        }
        item.setPassword("");
        return item;
    }

    public Userinfo RefreshToken(Userinfo info){
        if(Tokenverifier(info.getAccesstoken())){
            Algorithm algorithm = Algorithm.HMAC256("neoforutona1020");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            //토큰 인증 2시간
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            String token = JWT.create()
                    .withIssuer("forutona")
                    .withExpiresAt(calendar.getTime())
                    .withClaim("uid",info.getUid())
                    .withClaim("nickname",info.getNickname())
                    .sign(algorithm);
            info.setAccesstoken(token);
            return info;
        }else {
            return info;
        }
    }

    boolean Tokenverifier(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("forutona");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("forutona")
                    .acceptLeeway(1)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch ( JWTVerificationException exception){
            return false;
        }

    }

}
