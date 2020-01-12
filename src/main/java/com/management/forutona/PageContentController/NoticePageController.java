package com.management.forutona.PageContentController;

import com.management.forutona.AuthDao.AuthDao;
import com.management.forutona.PageContentDao.NoticePageDao;
import com.management.forutona.PageContentDto.NoticePageSearchDto;
import com.management.forutona.PageContentDto.Noticepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticePageController {
    @Autowired
    NoticePageDao noticePageDao;

    @GetMapping(value = "/v1/NoticePage/list")
    List<Noticepage> getNoticePageList(NoticePageSearchDto searchDto){
        return noticePageDao.getNoticePageList(searchDto);
    }

    @GetMapping(value = "/v1/NoticePage/Pagelength")
    int selectPagelength(NoticePageSearchDto searchDto){
        return noticePageDao.selectPagelength();
    }

    @GetMapping(value = "/v1/NoticePage/Pageitem")
    Noticepage getPageitem(Noticepage item){
        return noticePageDao.getPageitem(item);
    }

    @PostMapping(value="/v1/NoticePage/Writedoc")
    int Writedoc(@RequestBody Noticepage page){
        return noticePageDao.writedoc(page);
    }
}
