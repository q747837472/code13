package com.youyue.manege_cms.service;

import com.youyue.framework.domain.cms.CmsTemplate;
import com.youyue.manege_cms.dao.CmsTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TemplateService {

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    public List<CmsTemplate> findTemplate() {
        List<CmsTemplate> list = cmsTemplateRepository.findAll();
        return list;
    }

}
