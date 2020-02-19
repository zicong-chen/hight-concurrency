package top.chenzicong.highconcurrency.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    //加盐
    private static final String salt = "czcLove";

    //从用户输入得到的密码进行一次MD5
    public  static String inputPassToFormPass(String inputPass) {
        //加得分散一点。
        String str = String.valueOf(salt.charAt(0)) +
                salt.charAt(6) +
                salt.charAt(2) +
                inputPass +
                salt.charAt(5) +
                salt.charAt(4);

        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    //结合一个随机的salt再一次MD5后，把salt和MD5同时存入数据库。
    public static String formPassToDBPass(String formPass, String salt) {
        //加得分散一点。
        String str = String.valueOf(salt.charAt(0)) +
                salt.charAt(0) +
                salt.charAt(2) +
                formPass +
                salt.charAt(4) +
                salt.charAt(3);

        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    //用户输入的密码，然后经过两次MD5进行加密后返回。
    public static String inputPassToDBPass(String inputPass, String salt) {
        String formPass = inputPassToFormPass(inputPass);
        return formPassToDBPass(formPass, salt);
    }

    public static void main(String[] args) {
        String pass = inputPassToDBPass("czcwr1314","czcwr");
        System.out.println(pass);
    }
}
