package com.youyue.manege_cms.dao;


import com.youyue.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {


}
