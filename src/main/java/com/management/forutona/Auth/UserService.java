package com.management.forutona.Auth;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserService implements UserDetailsService {

    @Resource(name = "WebsqlSession")
    private SqlSession WebsqlSession;


    @Override
    public UserDetails loadUserByUsername(String UserID) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Userinfo userinfo = new Userinfo();
        userinfo.setUid(UserID);
        UserinfoMapper mapper = WebsqlSession.getMapper(UserinfoMapper.class);
        Userinfo tempuserinfo = mapper.selectByPrimaryKey(UserID);
        if(tempuserinfo == null){
            throw new UsernameNotFoundException(UserID);
        }
        return new UserinfoAdapter(tempuserinfo);
    }

}
