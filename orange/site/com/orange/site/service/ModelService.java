package com.orange.site.service;import com.orange.site.dao.ModelDao;import com.orange.site.entity.ModelEntity;import com.orange.statics.dao.LinkDao;import com.orange.statics.entity.LinkEntity;import com.orange.core.base.service.Services;import com.orange.core.base.service.ServicesManager;import org.springframework.stereotype.Service;import javax.annotation.Resource;@Service("siteModelService")public class ModelService extends ServicesManager<ModelEntity, ModelDao> implements		Services<ModelEntity> {    @Resource(name="siteModelDao")    @Override    public void setDao(ModelDao mDao) {        this.dao = mDao;    }    }