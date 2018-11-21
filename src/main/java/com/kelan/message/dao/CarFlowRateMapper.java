package com.kelan.message.dao;

import com.kelan.message.entity.CarFlowRate;

public interface CarFlowRateMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(CarFlowRate record);

    int insertSelective(CarFlowRate record);

    CarFlowRate selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(CarFlowRate record);

    int updateByPrimaryKey(CarFlowRate record);
}