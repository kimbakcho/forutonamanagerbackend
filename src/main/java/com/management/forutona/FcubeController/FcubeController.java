package com.management.forutona.FcubeController;

import com.management.forutona.FcubeDao.FcubeDao;
import com.management.forutona.FcubeDto.FcubeUserSearchdto;
import com.management.forutona.FcubeDto.FcubeUserinfo;
import com.management.forutona.FcubeDto.Fcubequestpageview;
import com.management.forutona.FcubeDto.FcubequestpageviewSearchdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FcubeController {

    @Autowired
    FcubeDao fcubeDao;

    @GetMapping(value = "/v1/Fcube/Userinfo/selectlistbysearch")
    List<FcubeUserinfo> FcubeUserinfoselectlistbysearch(FcubeUserSearchdto searchdto){
        return fcubeDao.UserInfolist(searchdto);
    }

    @GetMapping(value = "/v1/Fcube/Userinfo/selectFcubeUserinfolength")
    int FcubeUserinfolength(FcubeUserSearchdto searchdto){
        return fcubeDao.selectFcubeUserinfolength(searchdto);
    }

    @GetMapping(value = "/v1/Fcube/QuestCubePage/selectFcubeQuestPageViewlength")
    int selectFcubeQuestPageViewlength(FcubequestpageviewSearchdto searchdto){
        return fcubeDao.selectFcubeQuestPageViewlength(searchdto);
    }

    @GetMapping(value = "/v1/Fcue/QuestCubePage/selectFcubeQuestPageView")
    List<Fcubequestpageview> selectFcubeQuestPageView(FcubequestpageviewSearchdto searchdto){
        return fcubeDao.selectFcubeQuestPageView(searchdto);
    }

}
