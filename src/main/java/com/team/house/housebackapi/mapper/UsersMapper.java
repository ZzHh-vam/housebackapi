package com.team.house.housebackapi.mapper;

import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.pojo.UsersExample;
import com.team.house.housebackapi.util.PageParameter;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    //获取同类型用户最大id
    int selectUsersId();

    //获取同类型用户最大id
    int selectId();

    //查询用户信息
    List<Users> selectNum();
}