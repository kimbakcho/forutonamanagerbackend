package com.management.forutona.FcubeQuestPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FcubeQuestPageController {

    @Autowired
    FcubeQuestPageDao fcubeQuestPageDao;

    @GetMapping(value = "/v1/Fcube/QuestCubePage/selectFcubeQuestPageViewlength")
    int selectFcubeQuestPageViewlength(FcubequestpageviewSearchdto searchdto){
        return fcubeQuestPageDao.selectFcubeQuestPageViewlength(searchdto);
    }

    @GetMapping(value = "/v1/Fcube/QuestCubePage/selectFcubeQuestPageView")
    List<Fcubequestpageview> selectFcubeQuestPageView(FcubequestpageviewSearchdto searchdto){
        return fcubeQuestPageDao.selectFcubeQuestPageView(searchdto);
    }

    @PostMapping(value = "/v1/Fcube/deletefcube")
    int deletefcube(@RequestParam String cubeuuid){
        return fcubeQuestPageDao.deleteFcube(cubeuuid);
    }
}
