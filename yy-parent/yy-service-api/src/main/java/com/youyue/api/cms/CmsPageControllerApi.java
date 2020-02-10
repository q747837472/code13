package com.youyue.api.cms;

import com.youyue.framework.domain.cms.CmsPage;
import com.youyue.framework.domain.cms.CmsSite;
import com.youyue.framework.domain.cms.CmsTemplate;
import com.youyue.framework.domain.cms.request.QueryPageRequest;
import com.youyue.framework.domain.cms.response.CmsPageResult;
import com.youyue.framework.model.response.QueryResponseResult;
import com.youyue.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(value="cms页面管理接口",description="cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
   @ApiOperation("分页查询页面列表")
@ApiImplicitParams({
        @ApiImplicitParam(name="page",value="页码",required=true,paramType="path",dataType="int"),
        @ApiImplicitParam(name="size",value="每页记录 数",required=true,paramType="path",dataType="int")})

    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);
    @ApiOperation("新增信息")
    CmsPageResult add(CmsPage cmsPage);
    @ApiOperation("根据id查询")
    CmsPage findById(String id);
    @ApiOperation("修改信息")
    CmsPageResult edit(String id,CmsPage cmsPage);
    @ApiOperation("删除信息")
    ResponseResult delete(String id);
    @ApiOperation("查询站点")
    List<CmsSite> findSite();
    @ApiOperation("查询模板")
    List<CmsTemplate> findTemplate();

}
