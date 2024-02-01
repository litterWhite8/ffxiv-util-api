package org.litterwhite.ffxivUtil.Common.Utils;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class PasswordUtil {

    // 盐
    public static final String SECRET="litterwhite&AkieGZH";


    /**
     * @description 生成加密passoword
     * @param password 密码
     * @return String
     * @author AA
     * @date 2024/1/30 13:30:24
     */
    public static String getMd5Encryption(String password){
        return DigestUtils.md5DigestAsHex((password+SECRET).getBytes(StandardCharsets.UTF_8));
    }

    public static String getMd5Encryption(String password,String salt){
        return DigestUtils.md5DigestAsHex((password+salt).getBytes(StandardCharsets.UTF_8));
    }


    public static void main(String[] args) {
        System.out.println(Keys.secretKeyFor(SignatureAlgorithm.HS512));
    }
}
	
