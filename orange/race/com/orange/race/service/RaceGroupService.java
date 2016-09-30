/*
 * 文 件 名:  RaceGroupService.java
 * 版    权:  CCDC Copyright 2016,  All rights reserved
 * 描    述:  爱跑汇
 * 修 改 人:  Lanbo
 * 创建时间:  2016年8月11日
 */
package com.orange.race.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.orange.core.base.service.Services;
import com.orange.core.base.service.ServicesManager;
import com.orange.race.dao.RaceGroupDao;
import com.orange.race.entity.RaceGroupEntity;

/**
 * <RaceGroupService>
 * 
 * @author lanbo
 * @version [V1.1, 2016年8月11日]
 * @since [爱跑汇/V1.1]
 */
@Service("raceGroupService")
public class RaceGroupService extends ServicesManager<RaceGroupEntity, RaceGroupDao> implements
    Services<RaceGroupEntity>
{
    
    /**
     * 重载方法
     * 
     * @param dao
     */
    @Resource(name = "raceGroupDao")
    @Override
    public void setDao(RaceGroupDao dao)
    {
        this.dao = dao;
    }
    
    public List<RaceGroupEntity> selectListByRaceId(String raceId)
    {
        return dao.selectListByRaceId(raceId);
    }
    
}
