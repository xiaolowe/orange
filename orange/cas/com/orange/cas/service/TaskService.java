package com.orange.cas.service;import com.orange.cas.dao.TaskDao;import com.orange.cas.model.Task;import com.orange.cas.service.TaskService;import com.orange.core.base.service.Services;import com.orange.core.base.service.ServicesManager;import org.springframework.stereotype.Service;import javax.annotation.Resource;@Service("casTaskService")public class TaskService extends ServicesManager<Task, TaskDao> implements		Services<Task> {    @Override    @Resource(name = "casTaskDao")	public void setDao(TaskDao taskDao) {        this.dao = taskDao;	}}