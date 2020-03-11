package com.management.forutona.FcubeUserinfo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FcubeUserInfoDao {

    @Resource(name = "AppsqlSession")
    private SqlSession AppsqlSession;

    public List<FcubeUserinfo> UserInfolist(FcubeUserSearchdto searchdto){
        FcubeUserinfoMapper mapper = AppsqlSession.getMapper(FcubeUserinfoMapper.class);
        return mapper.selectlistbysearch(searchdto);
    }

    public int selectFcubeUserinfolength(FcubeUserSearchdto searchdto){
        FcubeUserinfoMapper mapper = AppsqlSession.getMapper(FcubeUserinfoMapper.class);
        return mapper.selectFcubeUserinfolength(searchdto);
    }
}
