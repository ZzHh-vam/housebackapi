package com.team.house.housebackapi.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.mapper.UsersMapper;
import com.team.house.housebackapi.pojo.House;
import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.service.HouseService;
import com.team.house.housebackapi.util.Condition;
import com.team.house.housebackapi.util.FileUploadUtil;
import com.team.house.housebackapi.util.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ZzHh
 * @Classname HouseController
 * @Description TODO
 * @Date: Created in 2020/3/6 15:41
 * @Create By IntelliJ IDEA
 **/

//返回JSON
@RestController
//跨域
@CrossOrigin(value = "*", allowCredentials = "true")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @Autowired(required = false)
    private UsersMapper usersMapper;

    @RequestMapping("/fabuHouse")
    public String fabuHouse(House house, HttpSession session, @RequestParam(value = "filepath",required = false)MultipartFile filepath){
        try {
            //文件服务器地址
            String path = "E:\\JavaSpace\\FileServer\\RentSystem\\images";
            String fileName = FileUploadUtil.upload(filepath,path);

            //房屋编号
            house.setId(System.currentTimeMillis() + "");
            //所属用户
            Users users = (Users) session.getAttribute("userslogininfo");
            house.setUserId(users.getId());

            users.setIsadmin(2);
            usersMapper.updateByPrimaryKey(users);

            //文件保存路径
            house.setPath(fileName);

            houseService.addHouse(house);
            return "{\"result\":1}";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":0}";
    }

    @RequestMapping("/delHouseByHouseId")
    public String delHouseByHouseId(String houseId, String filename){
        int delLine = houseService.delHouseByHouseId(houseId);
        if (delLine > 0){
            //删除图片
            File file = new File("E:\\JavaSpace\\FileServer\\RentSystem\\images" + filename);
            if (file.exists()){
                file.delete();
            }
            return "{\"result\":1}";
        }else{
            return "{\"result\":0}";
        }
    }

    @RequestMapping("/updateHouse")
    public String updateHouse(House house, HttpSession session, @RequestParam(value = "filepath",required = false)MultipartFile filepath){
        try {
            //判断是否上传文件
            String fileName = filepath.getOriginalFilename();
            if (!fileName.equals("")){
                //文件服务器地址
                String path = "E:\\JavaSpace\\FileServer\\RentSystem\\images";
                fileName = FileUploadUtil.upload(filepath,path);

                //文件保存路径
                house.setPath(fileName);
            }

            //修改基本信息
            houseService.updateHouseById(house);
            return "{\"result\":1}";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{\"result\":0}";
    }

    @RequestMapping("/getHouseByCondition")
    public Map<String,Object> getHouseByCondition(Condition condition, HttpSession session){
        Users users = (Users) session.getAttribute("userslogininfo");
        PageInfo<House> pageInfo = houseService.getHouseByCondition(condition);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",pageInfo.getPages());
        map.put("rows",pageInfo.getList());
        map.put("curPage",pageInfo.getPageNum());
        map.put("isadmin",users.getIsadmin());
        return map;
    }

    @RequestMapping("/getHouseByUsers")
    public Map<String,Object> getHouseByUsers(PageParameter pageParameter, HttpSession session){
        Users users = (Users) session.getAttribute("userslogininfo");
        PageInfo<House> pageInfo = houseService.getHouseByConditionAndUsers(users.getId(),pageParameter);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",pageInfo.getPages());
        map.put("rows",pageInfo.getList());
        map.put("curPage",pageInfo.getPageNum());
        map.put("isadmin",users.getIsadmin());
        return map;
    }

    @RequestMapping("getHouseByHouseId")
    public House getHouseByHouseId(String houseid){
        return houseService.getHouseByHouseId(houseid);
    }

    @RequestMapping("getSingleHouseByHouseId")
    public House getSingleHouseByHouseId(String houseid){
        House house = houseService.getSingleHouseByHouseId(houseid);
        return house;
    }

    @RequestMapping("/getHouseByAdmin")
    public Map<String,Object> getHouseByAdmin(Condition condition, HttpSession session){
        //Users users = (Users) session.getAttribute("adminlogininfo");
        PageInfo<House> pageInfo = houseService.getHouseByCondition(condition);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",pageInfo.getPages());
        map.put("rows",pageInfo.getList());
        map.put("curPage",pageInfo.getPageNum());
        return map;
    }

    @RequestMapping("/getHouseByAdminAndUsers")
    public Map<String,Object> getHouseByAdminAndUsers(Integer uid, PageParameter pageParameter, HttpSession session){
        PageInfo<House> pageInfo = houseService.getHouseByConditionAndUsers(uid,pageParameter);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",pageInfo.getPages());
        map.put("rows",pageInfo.getList());
        map.put("curPage",pageInfo.getPageNum());
        return map;
    }
}
