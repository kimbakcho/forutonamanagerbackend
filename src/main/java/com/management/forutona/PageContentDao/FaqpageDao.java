package com.management.forutona.PageContentDao;

import com.management.forutona.PageContentDto.Faqpage;
import com.management.forutona.PageContentDto.FaqpageSearchdto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FaqpageDao {
    @Resource(name = "WebsqlSession")
    private SqlSession WebsqlSession;

    public List<Faqpage> getFaqPageList(FaqpageSearchdto searchdto){
        FaqpageMapper mapper = WebsqlSession.getMapper(FaqpageMapper.class);
        return mapper.selectListofsearch(searchdto);
    }

    public int getFaqPageLength() {
        FaqpageMapper mapper = WebsqlSession.getMapper(FaqpageMapper.class);
        return mapper.selectListofsearchLength();
    }

    public Faqpage getFaqPage(int idx){
        FaqpageMapper mapper = WebsqlSession.getMapper(FaqpageMapper.class);
        return mapper.selectByPrimaryKey(idx);
    }

    public int insertPage(Faqpage item){
        FaqpageMapper mapper = WebsqlSession.getMapper(FaqpageMapper.class);
        return mapper.insert(item);
    }

}
