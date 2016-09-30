package com.orange.component.task;

import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orange.oscache.SystemManager;
import com.orange.race.service.RaceAddonService;


/**
 * 一周内部支付的订单，系统自动帮其取消
 */
@Component
public class CancelAddonTask implements Runnable{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CancelAddonTask.class);
	@Autowired
	private RaceAddonService raceAddonService;

	public void setRaceAddonService(RaceAddonService raceAddonService) {
		this.raceAddonService = raceAddonService;
	}


	public void run() {
		while(true){
			try {
				TimeUnit.SECONDS.sleep(Long.valueOf(SystemManager.getInstance().getProperty("task_SystemAutoNotifyTask_time")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			logger.error("RaceAddonCancelTask.run...");
			//raceAddonService.cancelAddon(new RaceAddonEntity());
		}
	}
	
}
