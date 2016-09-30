package com.orange.oscache;

import com.orange.cas.model.Task;
import com.orange.cas.service.TaskService;
import com.orange.core.task.TaskManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 缓存管理器。可以通过接口程序通知该类重新加载部分或全部的缓存
 * 
 */
public class ManageCache {
	private static final Logger logger = LoggerFactory.getLogger(ManageCache.class);
	
	/**
	 * manage后台
	 */
    @Autowired
	private TaskService casTaskService;
    
    public void loadManageCache(){
    	loadTask();
    }
    
    
	/**
	 * 加载定时任务列表
	 */
	public void loadTask(){
		List<Task> list = casTaskService.selectList(new Task());
		if(list!=null){
			TaskManager.taskPool.clear();
			for(int i=0;i<list.size();i++){
				Task item = list.get(i);
				TaskManager.taskPool.put(item.getCode(),item);
			}
		}
	}
	
	
}