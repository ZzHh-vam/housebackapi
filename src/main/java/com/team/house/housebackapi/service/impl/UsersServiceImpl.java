package com.team.house.housebackapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.mapper.UsersMapper;
import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.pojo.UsersExample;
import com.team.house.housebackapi.service.UsersService;
import com.team.house.housebackapi.util.MD5Utils;
import com.team.house.housebackapi.util.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZzHh
 * @Classname UsersServiceImpl
 * @Description TODO
 * @Date: Created in 2020/3/6 15:43
 * @Create By IntelliJ IDEA
 **/

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired(required = false)
    private UsersMapper usersMapper;

    //用户注册
    public int usersReg(Users users) {
        //密码不能以明文方式保存到DB中,不安全
        //使用md5对密码进行加密后存储到DB中
        //调用MD5工具类的方法进行加密
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    //通过账号密码登录
    public Users usersLogin(String username, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //设置条件
        criteria.andNameEqualTo(username);
        //加密后与加密的内容相比
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() == 1 && (list.get(0).getIsadmin() == null || list.get(0).getIsadmin().equals("") || list.get(0).getIsadmin() == 2)){
            return list.get(0);
        }else{
            return null;
        }
    }

    //通过手机号登录
    public Users login(String tel) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //设置条件
        criteria.andTelephoneEqualTo(tel);
        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() == 1){
            return list.get(0);
        }else{
            return null;
        }
    }

    //获取同类型用户最大id
    public int usersId() {
        return usersMapper.selectUsersId();
    }

    //获取同类型管理员最大id
    public int adminId() {
        return usersMapper.selectId();
    }

    //管理员注册
    public int adminReg(Users users) {
        users.setIsadmin(users.getIsadmin());
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users);
    }

    //管理员通过账号密码登录
    public Users adminLogin(String username, String password) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //设置条件
        criteria.andNameEqualTo(username);
        criteria.andIsadminEqualTo(1);
        //加密后与加密的内容相比
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() == 1){
            return list.get(0);
        }else{
            return null;
        }
    }

    //管理员通过手机号登录
    public Users adminLogin(String tel) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //设置条件
        criteria.andTelephoneEqualTo(tel);
        criteria.andIsadminEqualTo(1);
        List<Users> list = usersMapper.selectByExample(usersExample);
        if (list != null && list.size() == 1){
            return list.get(0);
        }else{
            return null;
        }
    }

    //查询用户信息
    public PageInfo<Users> admin(PageParameter pageParameter) {
        PageHelper.startPage(pageParameter.getPage(), pageParameter.getPageSize());
        List<Users> list = usersMapper.selectNum();
        return new PageInfo<>(list);
    }
}
