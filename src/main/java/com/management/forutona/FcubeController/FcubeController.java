package com.management.forutona.FcubeController;

import com.management.forutona.FcubeDao.FcubeDao;
import com.management.forutona.FcubeDto.FcubeUserSearchdto;
import com.management.forutona.FcubeDto.FcubeUserinfo;
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
}
