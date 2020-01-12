package com.management.forutona.PageContentDao;

import com.management.forutona.PageContentDto.NoticePageSearchDto;
import com.management.forutona.PageContentDto.Noticepage;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
public class NoticePageDao {
    @Resource(name = "WebsqlSession")
    private SqlSession WebsqlSession;

    public List<Noticepage> getNoticePageList(NoticePageSearchDto searchDto){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
       return  mapper.selectlistbysearch(searchDto);
    }

    public int selectPagelength(){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
        return  mapper.selectPagelength();
    }

    public int writedoc(Noticepage item){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
        return mapper.insert(item);
    }

    public Noticepage getPageitem(Noticepage item){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
        return mapper.selectByPrimaryKey(item.getIdx());
    }

    public int updatedoc(Noticepage item){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
        return mapper.updateByPrimaryKey(item);
    }

    public int deletedoc(Noticepage item){
        NoticepageMapper mapper =  WebsqlSession.getMapper(NoticepageMapper.class);
        return mapper.deleteByPrimaryKey(item.getIdx());
    }

}
