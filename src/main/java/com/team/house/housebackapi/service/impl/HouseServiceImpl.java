package com.team.house.housebackapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.mapper.HouseMapper;
import com.team.house.housebackapi.pojo.House;
import com.team.house.housebackapi.service.HouseService;
import com.team.house.housebackapi.util.Condition;
import com.team.house.housebackapi.util.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname HouseServiceImpl
 * @Description TODO
 * @Date: Created in 2020/3/6 15:43
 * @Create By IntelliJ IDEA
 **/

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired(required = false)
    private HouseMapper houseMapper;

    //发布房屋信息
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    //通过房屋id删除房屋信息
    public int delHouseByHouseId(String houseid) {
        return houseMapper.deleteByPrimaryKey(houseid);
    }

    //通过房屋id修改房屋信息
    public int updateHouseById(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    //查询所有出租房信息并支持分页
    public PageInfo<House> getHouseByCondition(Condition condition) {
        PageHelper.startPage(condition.getPage(), condition.getPageSize());
        List<House> list = houseMapper.selectByCondition(condition);
        return new PageInfo<>(list);
    }

    //查询指定用户下所有出租房信息并支持分页
    public PageInfo<House> getHouseByConditionAndUsers(Integer uid, PageParameter pageParameter) {
        PageHelper.startPage(pageParameter.getPage(), pageParameter.getPageSize());
        List<House> list = houseMapper.selectByConditionAndUsers(uid);
        return new PageInfo<>(list);
    }

    //通过房屋id查询房屋信息
    public House getHouseByHouseId(String houseid) {
        return houseMapper.getHouseById(houseid);
    }

    //通过房屋id查询房屋信息
    public House getSingleHouseByHouseId(String houseid) {
        return houseMapper.getSingleHouseById(houseid);
    }
}
