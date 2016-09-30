/*
 * 文 件 名:  RaceGroupDao.java
 * 版    权:  CCDC Copyright 2016,  All rights reserved
 * 描    述:  爱跑汇
 * 修 改 人:  Lanbo
 * 创建时间:  2016年8月11日
 */
package com.orange.race.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.orange.core.base.dao.BaseDao;
import com.orange.core.base.dao.DaoManager;
import com.orange.core.base.dao.page.PagerModel;
import com.orange.race.entity.RaceGroupEntity;

/**
 * <RaceGroupDao>
 * 
 * @author lanbo
 * @version [V1.1, 2016年8月11日]
 * @since [爱跑汇/V1.1]
 */
@Repository("raceGroupDao")
public class RaceGroupDao implements DaoManager<RaceGroupEntity>
{
    @Resource
    private BaseDao dao;
    
    public void setDao(BaseDao dao)
    {
        this.dao = dao;
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    public int insert(RaceGroupEntity e)
    {
        return dao.insert("race.group.insert", e);
    }
    
    /**
     * 重载方法
     * 
     * @param id
     * @return
     */
    public int deleteById(String id)
    {
        return dao.delete("race.group.deleteById", id);
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    public int delete(RaceGroupEntity e)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    public int update(RaceGroupEntity e)
    {
        return dao.update("race.group.update", e);
    }
    
    /**
     * 重载方法
     * 
     * @param id
     * @return
     */
    public RaceGroupEntity selectById(String id)
    {
        return (RaceGroupEntity)dao.selectOne("race.group.selectById", id);
        
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    public RaceGroupEntity selectOne(RaceGroupEntity e)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RaceGroupEntity> selectList(RaceGroupEntity e)
    {
        return dao.selectList("race.info.selectList", e);
    }
    
    /**
     * 重载方法
     * 
     * @param e
     * @return
     */
    public PagerModel selectPageList(RaceGroupEntity e)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 重载方法
     * 
     * @param param
     * @return
     */
    public List<RaceGroupEntity> selectList(Map<String, String> param)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 
     * <查询报名组别列表信息>
     * 
     * @param raceId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RaceGroupEntity> selectListByRaceId(String raceId)
    {
        return dao.selectList("race.group.selectListByRaceId", raceId);
    }
    
}
