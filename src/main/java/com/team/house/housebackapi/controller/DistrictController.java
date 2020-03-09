package com.team.house.housebackapi.controller;

import com.team.house.housebackapi.pojo.District;
import com.team.house.housebackapi.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname DistrictController
 * @Description TODO
 * @Date: Created in 2020/3/6 15:41
 * @Create By IntelliJ IDEA
 **/

//返回JSON
@RestController
//跨域
@CrossOrigin(value = "*", allowCredentials = "true")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getAllDist")
    public List<District> getAllDist(){
        return districtService.getAllDistrict();
    }
}
