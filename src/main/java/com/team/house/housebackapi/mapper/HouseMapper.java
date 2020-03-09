package com.team.house.housebackapi.mapper;

import com.team.house.housebackapi.pojo.House;
import com.team.house.housebackapi.pojo.HouseExample;
import com.team.house.housebackapi.util.Condition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询当前用户下所有出租房信息并支持分页
    List<House> selectByCondition(Condition condition);

    //查询指定用户下所有出租房信息并支持分页
    List<House> selectByConditionAndUsers(Integer userid);

    //通过房屋id查询房屋信息
    House getHouseById(String houseid);

    //通过房屋id查询房屋信息
    House getSingleHouseById(String houseid);
}