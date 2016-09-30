package com.orange.common.fn;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orange.common.PrivilegeUtil;
import com.orange.web.action.holder.RequestHolder;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PrivilegeChecker implements TemplateMethodModelEx {
    private static Logger logger = LoggerFactory.getLogger(PrivilegeChecker.class);
    @SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
    	Boolean result = false;
        if(arguments == null || arguments.size() == 0){
        	result = false;
        }
        if(!(arguments.get(0) instanceof String)){
        	result = false;
        }
        ;
        if(StringUtils.isBlank(arguments.get(0).toString())){
        	result =  false;
        }else{
        	 HttpSession session = RequestHolder.getSession();
             logger.error("check privilege ,res : {}, session id :{}", arguments.get(0).toString(), session == null ? null : session.getId());
             result =  PrivilegeUtil.check(session, arguments.get(0).toString());
        }
        return result;
    }
}
