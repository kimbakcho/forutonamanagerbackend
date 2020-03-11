package com.management.forutona.FcubeUserinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FcubeUserInfoController {

    @Autowired
    FcubeUserInfoDao fcubeUserInfoDao;

    @GetMapping(value = "/v1/Fcube/Userinfo/selectlistbysearch")
    List<FcubeUserinfo> FcubeUserinfoselectlistbysearch(FcubeUserSearchdto searchdto){
        return fcubeUserInfoDao.UserInfolist(searchdto);
    }

    @GetMapping(value = "/v1/Fcube/Userinfo/selectFcubeUserinfolength")
    int FcubeUserinfolength(FcubeUserSearchdto searchdto){
        return fcubeUserInfoDao.selectFcubeUserinfolength(searchdto);
    }
}
