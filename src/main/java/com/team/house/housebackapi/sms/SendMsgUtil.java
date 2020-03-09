package com.team.house.housebackapi.sms;

/**
 * @Author ZzHh
 * @Classname SendMsgUtil
 * @Description TODO
 * @Date: Created in 2020/3/6 10:42
 * @Create By IntelliJ IDEA
 **/

public class SendMsgUtil {
    //用户名
    private static String Uid = "zzh1181132030";

    //用户密钥
    private static String Key = "d41d8cd98f00b204e980";

    /**
     *@Function: sendMsg
     *@Description: 发送短信的方法
     *@Param: [smsText, smsMobel]
     *@Return: int
     * -1   没有该用户账户
     * -2   接口密钥不正确[查看密钥]
     * 不是账户登陆密码
     * -21  MD5接口密钥加密不正确
     * -3   短信数量不足
     * -11  该用户被禁用
     * -14  短信内容出现非法字符
     * -4   手机号格式不正确
     * -41  手机号码为空
     * -42  短信内容为空
     * -51  短信签名格式不正确
     * 接口签名格式为:[签名内容]
     * -52  短信签名太长
     * 建议签名10个字符以内
     * -6   IP限制
     * 大于0  短信发送数量
     **/
    public static int sendMsg(String smsText, String smsMobel){
        HttpClientUtil client = HttpClientUtil.getInstance();
        int result = client.sendMsgUtf8(Uid,Key,smsText,smsMobel);
        return result;
    }
}
