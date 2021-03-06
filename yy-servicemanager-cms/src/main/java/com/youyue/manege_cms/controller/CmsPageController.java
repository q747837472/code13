package com.youyue.manege_cms.controller;

import com.youyue.api.cms.CmsPageControllerApi;
import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.CmsSite;
import com.youyue.framework.domain.cms.CmsTemplate;
import com.youyue.framework.domain.cms.request.QueryPageRequest;
import com.youyue.framework.domain.cms.response.CmsPageResult;
import com.youyue.framework.model.response.CommonCode;
import com.youyue.framework.model.response.QueryResponseResult;
import com.youyue.framework.model.response.QueryResult;

import com.youyue.framework.model.response.ResponseResult;
import com.youyue.manege_cms.service.PageService;
import com.youyue.manege_cms.service.SiteService;
import com.youyue.manege_cms.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    private PageService pageService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private TemplateService templateService;
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable ("page") int page, @PathVariable ("size")int size, QueryPageRequest queryPageRequest) {
        /*QueryResult<CmsPage>queryResult=new QueryResult<CmsPage>();
        List<CmsPage>list=new ArrayList<CmsPage>();
        CmsPage cmsPage=new CmsPage();
        cmsPage.setPageName("测试页面");
        list.add(cmsPage);
        queryResult.setList(list);
        queryResult.setTotal(1L);
        QueryResponseResult queryResponseResult=new QueryResponseResult(CommonCode.SUCCESS,queryResult);

        return queryResponseResult;*/
       return pageService.findList(page,size,queryPageRequest);
       //
    }

    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.getById(id);
    }

    @Override
    @PutMapping ("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id")String id,@RequestBody CmsPage cmsPage) {
        return pageService.edit(id,cmsPage);
    }

    @Override
    @DeleteMapping ("/del/{id}")
    public ResponseResult delete(@PathVariable("id")String id) {
        return pageService.delete(id);
    }

    @Override
    @GetMapping("/siteList")
    public List<CmsSite> findSite() {
        return siteService.findSite();
    }

    @Override
    @GetMapping("/templateList")
    public List<CmsTemplate> findTemplate() {
        return templateService.findTemplate();
    }


}
