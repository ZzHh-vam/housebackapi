package com.team.house.housebackapi.service;

import com.team.house.housebackapi.pojo.Street;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname StreetService
 * @Description TODO
 * @Date: Created in 2020/3/6 15:42
 * @Create By IntelliJ IDEA
 **/

public interface StreetService {
    //根据街道编号查询街道信息
    List<Street> getAllStreet(Integer districtId);
}
