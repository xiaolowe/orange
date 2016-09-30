package com.orange.common.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import java.util.List;

import com.orange.common.KeyValueHelper;

public class KeyValueGetter implements TemplateMethodModelEx {
    @SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
        return KeyValueHelper.get(arguments.get(0).toString());
    }
}
