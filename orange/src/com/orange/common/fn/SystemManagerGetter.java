package com.orange.common.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

import com.orange.oscache.SystemManager;


/**
 * 获取系统管理
 */
public class SystemManagerGetter implements TemplateMethodModelEx {
    @SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
        return SystemManager.getInstance();
    }
}
