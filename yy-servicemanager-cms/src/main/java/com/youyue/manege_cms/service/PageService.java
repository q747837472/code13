package com.youyue.manege_cms.service;

import com.youyue.framework.domain.cms.CmsConfig;
import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.request.QueryPageRequest;
import com.youyue.framework.domain.cms.response.CmsCode;
import com.youyue.framework.domain.cms.response.CmsPageResult;
import com.youyue.framework.exception.ExceptionCast;
import com.youyue.framework.model.response.CommonCode;
import com.youyue.framework.model.response.QueryResponseResult;
import com.youyue.framework.model.response.QueryResult;
import com.youyue.framework.model.response.ResponseResult;
import com.youyue.manege_cms.dao.CmsConfigRepository;
import com.youyue.manege_cms.dao.CmsPageRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;


@Service
public class PageService {

    @Autowired
     CmsPageRepository cmsPageRepository;
    @Autowired
     CmsConfigRepository cmsConfigRepository;
    @Autowired
    RestTemplate restTemplate;
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        //防止空指针异常
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        //防止空指针异常
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        if (page <= 0) {
            page = 1;
        }
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        QueryResult<CmsPage> queryResult = new QueryResult<CmsPage>();
        queryResult.setTotal(all.getTotalElements());
        queryResult.setList(all.getContent());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;

    }

    //新增
    public CmsPageResult add(CmsPage cmsPage) {
        if (cmsPage==null){
            ExceptionCast.cast(CommonCode.FAIL);
        }
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        //判断是否已经存在
        if (cmsPage1 != null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }

            cmsPage.setPageId(null);//为了能确保创建主键（我们本来传的也应该是null）
            cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);

    }

    //根据id查询
    public CmsPage getById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    //修改
    public CmsPageResult edit(String id, CmsPage cmsPage) {
        CmsPage one = this.getById(id);
        if (one != null) {

            one.setTemplateId(cmsPage.getTemplateId());
//更新所属站点
            one.setSiteId(cmsPage.getSiteId());
//更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
//更新页面名称
            one.setPageName(cmsPage.getPageName());
//更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
//更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
//提交修改
            cmsPageRepository.save(one);
            return new CmsPageResult(CommonCode.SUCCESS, one);
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }
   public ResponseResult delete(String id){
       CmsPage one = this.getById(id);
       if (one!=null){
           cmsPageRepository.delete(one);
           return new ResponseResult(CommonCode.SUCCESS);
       }
       return new ResponseResult(CommonCode.FAIL);
   }

   public CmsConfig getConfigById(String id){
       Optional<CmsConfig>optional=cmsConfigRepository.findById(id);
       if (optional.isPresent()){
           return optional.get();
       }
       return null;
   }
}
