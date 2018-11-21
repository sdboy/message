package com.kelan.message.dao;

import com.kelan.message.entity.ManRealPosition;

public interface ManRealPositionMapper {
    int deleteByPrimaryKey(Long sid);

    int insert(ManRealPosition record);

    int insertSelective(ManRealPosition record);

    ManRealPosition selectByPrimaryKey(Long sid);

    int updateByPrimaryKeySelective(ManRealPosition record);

    int updateByPrimaryKey(ManRealPosition record);
}