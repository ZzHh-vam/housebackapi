package com.team.house.housebackapi.service;

import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.pojo.House;
import com.team.house.housebackapi.util.Condition;
import com.team.house.housebackapi.util.PageParameter;

/**
 * @Author ZzHh
 * @Classname HouseService
 * @Description TODO
 * @Date: Created in 2020/3/6 15:42
 * @Create By IntelliJ IDEA
 **/

public interface HouseService {
    //发布房屋信息
    int addHouse(House house);

    //通过房屋id删除房屋信息
    int delHouseByHouseId(String houseid);

    //通过房屋id修改房屋信息
    int updateHouseById(House house);

    //查询所有出租房信息并支持分页
    PageInfo<House> getHouseByCondition(Condition condition);

    //查询指定用户下所有出租房信息并支持分页
    PageInfo<House> getHouseByConditionAndUsers(Integer uid, PageParameter pageParameter);

    //通过房屋id查询房屋信息
    House getHouseByHouseId(String houseid);

    //通过房屋id查询房屋信息
    House getSingleHouseByHouseId(String houseid);
}
