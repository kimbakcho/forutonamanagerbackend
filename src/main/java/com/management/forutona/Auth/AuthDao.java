package com.management.forutona.Auth;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthDao {
    @Resource(name = "WebsqlSession")
    private SqlSession WebsqlSession;




}
