package com.team.house.housebackapi.service.impl;

import com.team.house.housebackapi.mapper.DistrictMapper;
import com.team.house.housebackapi.pojo.District;
import com.team.house.housebackapi.pojo.DistrictExample;
import com.team.house.housebackapi.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname DistrictServiceImpl
 * @Description TODO
 * @Date: Created in 2020/3/6 15:43
 * @Create By IntelliJ IDEA
 **/

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired(required = false)
    private DistrictMapper districtMapper;

    //查询所有区域信息
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
