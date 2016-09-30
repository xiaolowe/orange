package com.orange.common.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

import com.orange.oscache.SystemManager;
import com.orange.web.action.holder.LoginUserHolder;


/**
 * 获取当前登录的用户(前端用户)
 */
public class CurrentNavsGetter implements TemplateMethodModelEx {
    @SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
        return SystemManager.getInstance().getNavs();
    }
}
