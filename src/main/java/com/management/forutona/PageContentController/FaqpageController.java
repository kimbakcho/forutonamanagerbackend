package com.management.forutona.PageContentController;


import com.management.forutona.PageContentDao.FaqpageDao;
import com.management.forutona.PageContentDto.Faqpage;
import com.management.forutona.PageContentDto.FaqpageSearchdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FaqpageController {

    @Autowired
    FaqpageDao faqpageDao;

    @GetMapping(value = "/v1/Faqpage/list")
    List<Faqpage> getFaqPageList(FaqpageSearchdto searchDto){
        return faqpageDao.getFaqPageList(searchDto);
    }

    @GetMapping(value = "/v1/Faqpage/Pagelength")
    int getFaqPageList(){
        return faqpageDao.getFaqPageLength();
    }

    @GetMapping(value = "/v1/Faqpage/Pageitem")
    Faqpage getPageitem(@RequestParam int idx){
        return faqpageDao.getFaqPage(idx);
    }

    @PostMapping(value = "/v1/Fqapage/InsertPage")
    int InsertPage(@RequestBody Faqpage item){
        return faqpageDao.insertPage(item);
    }

}
