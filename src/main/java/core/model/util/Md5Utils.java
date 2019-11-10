package core.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5Utils {
    public static String md5Utils( String password) {
        try {
            //通过Md5计算摘要
            MessageDigest   md = MessageDigest.getInstance("md5");
            byte[] digest = md.digest(password.getBytes("UTF-8"));

            //a-z A-Z 0-9 /  * BASE64编码算法
            //1.8
            String enPassword = Base64.getEncoder().encodeToString(digest);
            return enPassword;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }




    }
}