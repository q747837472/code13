package com.youyue.manege_cms.dao;

import com.youyue.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
//自定义一个方法
    CmsPage findByPageName(String pageName);



}
