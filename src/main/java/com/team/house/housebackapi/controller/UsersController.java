package com.team.house.housebackapi.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.housebackapi.pojo.Users;
import com.team.house.housebackapi.service.UsersService;
import com.team.house.housebackapi.sms.SendMsgUtil;
import com.team.house.housebackapi.util.PageParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ZzHh
 * @Classname UsersController
 * @Description TODO
 * @Date: Created in 2020/3/6 15:41
 * @Create By IntelliJ IDEA
 **/

//返回JSON
@RestController
//跨域
@CrossOrigin(value = "*", allowCredentials = "true")
public class UsersController {
    @Autowired
    private UsersService usersService;

    //接受手机号,发送验证码
    @RequestMapping("/sendCode")
    public String sendCode(String tel, HttpSession session){
        //1.生成验证码(四位随机数)
        int code = (int)((Math.random()*9+1)*100000);
        //2.定义消息
        String msg = "验证码:" + code + ",仅限本次使用!";
        //保存验证码
        session.setAttribute("sendCode",code);
        session.setMaxInactiveInterval(60);
        //3.发送短信
        int result = SendMsgUtil.sendMsg(msg,tel);
        return "{\"result\":" + result + "}";
    }

    //用户注册
    @RequestMapping("/usersReg")
    public String usersReg(Users users){
        //调用业务
        int id = usersService.usersId();
        users.setId(id + 1);
        int regLine = usersService.usersReg(users);  //1:成功     0:失败
        return "{\"result\":"+regLine+"}";
    }

    //账号密码登录控制器
    @RequestMapping("/usersLoginByUP")
    public String usersLoginByUP(String username, String password, HttpSession session){
        //调用业务
        Users users = usersService.usersLogin(username,password);  //成功1  失败0
        if(users == null){
            return "{\"result\":0}";  //登入失败
        }else{
            //只要登入请使用session保存登入人的信息
            session.setAttribute("userslogininfo",users);
            session.setMaxInactiveInterval(600); //10分钟
            return "{\"result\":1,\"usersid\":"+users.getId()+",\"isadmin\":"+users.getIsadmin()+"}";  //登陆成功
        }
    }

    //短信验证登录控制器
    @RequestMapping("/usersLoginByCode")
    public String usersLoginByCode(String tel, String inputCode, HttpSession session){
        //1.获取session中的验证码
        String code = session.getAttribute("sendCode").toString();
        Users users = usersService.login(tel);
        //2.比较验证码判断登陆是否成功
        if (inputCode.equals(code) && users != null){
            //只要登入请使用session保存登入人的信息
            session.setAttribute("userslogininfo",users);
            session.setMaxInactiveInterval(600); //10分钟
            //通过手机号查询当前用户的信息,并将用户信息保存session
            return "{\"result\":1,\"usersid\":"+users.getId()+",\"isadmin\":"+users.getIsadmin()+"}";   //登陆成功
        }else{
            return "{\"result\":0}";  //登陆失败
        }
    }

    //退出
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("userslogininfo");
        return "{\"result\":1}";
    }

    //管理员注册
    @RequestMapping("/adminReg")
    public String adminReg(Users users){
        //调用业务
        int id = usersService.adminId();
        users.setId(id + 1);
        int regLine = usersService.adminReg(users);  //1:成功     0:失败
        return "{\"result\":"+regLine+"}";
    }

    //管理员账号密码登录控制器
    @RequestMapping("/adminLoginByUP")
    public String adminLoginByUP(String username, String password, HttpSession session){
        //调用业务
        Users users = usersService.adminLogin(username,password);  //成功1  失败0
        if(users == null){
            return "{\"result\":0}";  //登入失败
        }else{
            //只要登入请使用session保存登入人的信息
            session.setAttribute("adminlogininfo",users);
            session.setMaxInactiveInterval(600); //10分钟
            return "{\"result\":1}";  //登陆成功
        }
    }

    //管理员短信验证登录控制器
    @RequestMapping("/adminLoginByCode")
    public String adminLoginByCode(String tel, String inputCode, HttpSession session){
        //1.获取session中的验证码
        String code = session.getAttribute("sendCode").toString();
        Users users = usersService.adminLogin(tel);
        //2.比较验证码判断登陆是否成功
        if (inputCode.equals(code) && users != null){
            //只要登入请使用session保存登入人的信息
            session.setAttribute("adminlogininfo",users);
            session.setMaxInactiveInterval(600); //10分钟
            //通过手机号查询当前用户的信息,并将用户信息保存session
            return "{\"result\":1}";   //登陆成功
        }else{
            return "{\"result\":0}";  //登陆失败
        }
    }

    //管理员退出
    @RequestMapping("/adminLoginOut")
    public String adminLoginOut(HttpSession session){
        session.removeAttribute("userslogininfo");
        return "{\"result\":1}";
    }

    //查询所有用户信息
    @RequestMapping("/admin")
    public Map<String,Object> admin(PageParameter pageParameter){
        PageInfo<Users> pageInfo = usersService.admin(pageParameter);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("totalPage",pageInfo.getPages());
        map.put("curPage",pageInfo.getPageNum());
        return map;
    }
}
