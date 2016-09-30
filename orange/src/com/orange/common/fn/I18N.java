package com.orange.common.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

import com.orange.core.i18n.MessageLoader;

/**
 * 国际化配置
 */
public class I18N implements TemplateMethodModelEx {
    @SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
        return MessageLoader.instance().getMessage(arguments.get(0).toString());
    }

}
