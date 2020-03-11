package com.management.forutona.FcubeQuestPage;

import com.management.forutona.Fcube.FcubeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FcubeQuestPageDao {

    @Resource(name = "AppsqlSession")
    private SqlSession AppsqlSession;

    public List<Fcubequestpageview> selectFcubeQuestPageView(FcubequestpageviewSearchdto searchdto){
        FcubequestpageviewMapper mapper = AppsqlSession.getMapper(FcubequestpageviewMapper.class);
        return mapper.selectFcubeQuestPageView(searchdto);
    }

    public int selectFcubeQuestPageViewlength(FcubequestpageviewSearchdto searchdto){
        FcubequestpageviewMapper mapper = AppsqlSession.getMapper(FcubequestpageviewMapper.class);
        return mapper.selectFcubeQuestPageViewlength(searchdto);
    }

    public int deleteFcube(String cubeuuid){
        FcubeMapper fcubeMapper = AppsqlSession.getMapper(FcubeMapper.class);

        return fcubeMapper.deleteByPrimaryKey(cubeuuid);
    }
}
