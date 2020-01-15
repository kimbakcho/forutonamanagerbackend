package com.management.forutona.FcubeDao;

import com.management.forutona.FcubeDto.FcubeUserSearchdto;
import com.management.forutona.FcubeDto.FcubeUserinfo;
import com.management.forutona.FcubeDto.Fcubequestpageview;
import com.management.forutona.FcubeDto.FcubequestpageviewSearchdto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FcubeDao {
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
