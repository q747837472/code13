package com.youyue.manege_cms.service;

import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.CmsSite;
import com.youyue.framework.domain.cms.request.QueryPageRequest;
import com.youyue.framework.domain.cms.response.CmsPageResult;
import com.youyue.framework.model.response.CommonCode;
import com.youyue.framework.model.response.QueryResponseResult;
import com.youyue.framework.model.response.QueryResult;
import com.youyue.manege_cms.dao.CmsPageRepository;
import com.youyue.manege_cms.dao.CmsSiteRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SiteService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    public  List<CmsSite> findSite() {
        List<CmsSite> list = cmsSiteRepository.findAll();
        return list;
    }


}
