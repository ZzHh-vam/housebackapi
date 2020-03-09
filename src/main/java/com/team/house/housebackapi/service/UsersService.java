package com.team.house.housebackapi.service;

import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.util.PageParameter;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname UsersService
 * @Description TODO
 * @Date: Created in 2020/3/6 15:42
 * @Create By IntelliJ IDEA
 **/

public interface UsersService {
    //用户注册
    int usersReg(Users users);

    //通过账号密码登录
    Users usersLogin(String username, String password);

    //通过手机号登录
    Users login(String tel);

   //获取同类型用户最大id
    int usersId();

    //获取同类型管理员最大id
    int adminId();

    //管理员注册
    int adminReg(Users users);

    //管理员通过账号密码登录
    Users adminLogin(String username, String password);

    //管理员通过手机号登录
    Users adminLogin(String tel);

    //查询用户信息
    PageInfo<Users> admin(PageParameter pageParameter);
}
