/*
 * 文 件 名:  RaceGroupEntity.java
 * 版    权:  CCDC Copyright 2016,  All rights reserved
 * 描    述:  爱跑汇
 * 修 改 人:  Lanbo
 * 创建时间:  2016年8月11日
 */
package com.orange.race.entity;

import com.orange.core.base.dao.page.QueryModel;

/**
 * <报名组别实体类>
 * 
 * @author lanbo
 * @version [V1.1, 2016年8月11日]
 * @since [爱跑汇/V1.1]
 */
public class RaceGroupEntity extends QueryModel
{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private String raceId;
    
    private String name;
    
    private String maxNum;
    
    private int price;
    
    private int signUpNum;
    
    private String type;
    
    private String createTime;
    
    private String updateTime;
    
    /**
     * 获取 raceId
     * 
     * @return 返回 raceId
     */
    public String getRaceId()
    {
        return raceId;
    }
    
    /**
     * 设置 raceId
     * 
     * @param 对raceId进行赋值
     */
    public void setRaceId(String raceId)
    {
        this.raceId = raceId;
    }
    
    /**
     * 获取 name
     * 
     * @return 返回 name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 设置 name
     * 
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 获取 maxNum
     * 
     * @return 返回 maxNum
     */
    public String getMaxNum()
    {
        return maxNum;
    }
    
    /**
     * 设置 maxNum
     * 
     * @param 对maxNum进行赋值
     */
    public void setMaxNum(String maxNum)
    {
        this.maxNum = maxNum;
    }
    
    /**
     * 获取 price
     * 
     * @return 返回 price
     */
    public int getPrice()
    {
        return price;
    }
    
    /**
     * 设置 price
     * 
     * @param 对price进行赋值
     */
    public void setPrice(int price)
    {
        this.price = price;
    }
    
    /**
     * 获取 signUpNum
     * 
     * @return 返回 signUpNum
     */
    public int getSignUpNum()
    {
        return signUpNum;
    }
    
    /**
     * 设置 signUpNum
     * 
     * @param 对signUpNum进行赋值
     */
    public void setSignUpNum(int signUpNum)
    {
        this.signUpNum = signUpNum;
    }
    
    /**
     * 获取 type
     * 
     * @return 返回 type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * 设置 type
     * 
     * @param 对type进行赋值
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * 获取 createTime
     * 
     * @return 返回 createTime
     */
    public String getCreateTime()
    {
        return createTime;
    }
    
    /**
     * 设置 createTime
     * 
     * @param 对createTime进行赋值
     */
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    
    /**
     * 获取 updateTime
     * 
     * @return 返回 updateTime
     */
    public String getUpdateTime()
    {
        return updateTime;
    }
    
    /**
     * 设置 updateTime
     * 
     * @param 对updateTime进行赋值
     */
    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }
    
}
