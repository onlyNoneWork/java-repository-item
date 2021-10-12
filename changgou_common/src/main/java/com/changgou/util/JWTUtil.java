package com.changgou.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * @author: hui.jin
 * @date: 2021/10/12 16:03
 */
public class JWTUtil {

    //设置过期时间 --一个小时
    public static final long JWT_EXPIRETIME = 3600000L;
    //设置密匙明文
    public static final String JWT_KEY = "itcorce";

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMills
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMills){

        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        if (ttlMills == null) {
            ttlMills = JWT_EXPIRETIME;
        }
        long expireMills = millis + ttlMills;
        Date expireDate = new Date(expireMills);

        /**
         * 唯一的id
         * 主题,可以是json数据
         * 签发者
         * 签发时间
         * 使用HS256对称加密算法签名, 私钥密匙
         * 设置过期时间
         */
        SecretKey secretKey = createSecretKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)//唯一的id
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(date)
                .signWith(algorithm, secretKey)
                .setExpiration(expireDate);
        return builder.compact();
    }

    /**
     * 解析
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){

        SecretKey secretKey = createSecretKey();

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 创建密匙
     * @return
     */
    public static SecretKey createSecretKey(){
        byte[] decode = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(decode, 0, decode.length, "AES");
    }

}
