package com.orange.core.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.cas.model.Task;
import com.orange.component.task.CancelAddonTask;

/**
 * 任务管理，负责管理定时启动的任务。
 * 
 */
@Component
public class TaskManager {
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(TaskManager.class);
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	public static final Map<String, Task> taskPool = new HashMap<String, Task>();// 任务池
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	private CancelAddonTask cancelAddonTask;
	
	

	public CancelAddonTask getCancelAddonTask() {
		return cancelAddonTask;
	}

	public void setCancelAddonTask(CancelAddonTask cancelAddonTask) {
		this.cancelAddonTask = cancelAddonTask;
	}

	public static Map<String, Task> getTaskpool() {
		return taskPool;
	}

	public void start() {
		logger.error("TaskManager.start()");
		pool.execute(cancelAddonTask);
	}

	// 处理某个任务
	private void doOneTask(Task task) {
		logger.error("doOneTask0...");

		try {
			Runnable runnable = (Runnable) Class.forName(task.getClz())
					.newInstance();
			if (runnable != null) {
				Thread thread = new Thread(runnable);
				task.setThread(thread);

				// 计算出此任务下一次的执行时间点
				String nextWorkTime = null;
				if (task.getUnit().equalsIgnoreCase("DAYS")) {
					nextWorkTime = sdf.format(DateUtils.addDays(new Date(),
							Integer.valueOf(task.getSleep())));
				} else if (task.getUnit().equalsIgnoreCase("HOURS")) {
					nextWorkTime = sdf.format(DateUtils.addHours(new Date(),
							Integer.valueOf(task.getSleep())));
				} else if (task.getUnit().equalsIgnoreCase("MINUTES")) {
					nextWorkTime = sdf.format(DateUtils.addMinutes(new Date(),
							Integer.valueOf(task.getSleep())));
				} else if (task.getUnit().equalsIgnoreCase("SECONDS")) {
					nextWorkTime = sdf.format(DateUtils.addSeconds(new Date(),
							Integer.valueOf(task.getSleep())));
				} else if (task.getUnit().equalsIgnoreCase("MILLISECONDS")) {
					nextWorkTime = sdf.format(DateUtils.addMilliseconds(
							new Date(), Integer.valueOf(task.getSleep())));
				}
				task.setNextWorkTime(nextWorkTime);
				task.setCurrentStatus(Task.task_currentStatus_wait);

				thread.setDaemon(true);
				thread.setName(task.getCode());
				thread.start();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过任务代号得到任务对象
	 * 
	 * @param taskCode
	 * @return
	 */
	public static Task getTask(String taskCode) {
		return taskPool.get(taskCode);
	}

	public void startTask(String taskCode) {
		Task task = taskPool.get(taskCode);
		if (task.getThread() == null) {
			doOneTask(task);
		} else {
			if (task.getThread().isAlive()) {

			}
		}
	}

	public void stopTask(String taskCode) {

	}
}
