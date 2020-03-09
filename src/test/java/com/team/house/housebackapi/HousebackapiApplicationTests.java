package com.team.house.housebackapi;

import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HousebackapiApplicationTests {
    @Autowired
    private UsersService usersService;

    @Test
    void contextLoads() {
        /*Users users = new Users();
        String username = "zzh";
        String password = "123456";
        Users users1 = usersService.usersLogin(username,password);
        System.out.println(users1.getName());*/
    }

}
