package com.util.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil
{
    public static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    public static final String issuer = "user-web";
    public static final String token = "abc1234567890";

    public static String createToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(token);
            JWTCreator.Builder builder = JWT.create().withIssuer(issuer).withExpiresAt(DateUtils.addMonths(new Date(), 2));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (Exception e) {
            logger.error("生成token失败,失败原因:{}", e);
        }
        return null;
    }

//    public static String createLoginToken(LoginInfoEntity loginInfoEntity) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(PropertiesUtil.getValue(ConfigConstants.TOKEN_SECURITY));
//            JWTCreator.Builder builder = JWT.create()
//                    .withIssuer(ISSUER)
//                    .withExpiresAt(DateUtils.addMonths(new Date(), 2));
//            if(loginInfoEntity != null) {
//                builder.withClaim(LoginInfoConstants.USER_ID, loginInfoEntity.getUserId());
//                builder.withClaim(LoginInfoConstants.PLAT_FORM, loginInfoEntity.getPlatForm());
//                builder.withClaim(LoginInfoConstants.CURRENT_TIME, System.currentTimeMillis());
//            }
//            return builder.sign(algorithm);
//        } catch (Exception e) {
//            logger.error("登陆生成token失败,失败原因:{}", e);
//        }
//        return null;
//    }


    /**
     * 验证jwt，并返回数据
     */
    public static Map<String, String> verifyToken(String token) 
    {
        Algorithm algorithm;
        Map<String, Claim> map = new HashMap<String, Claim>();
        try {
            algorithm = Algorithm.HMAC256(token);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

//    /**
//     * 验证jwt，并返回数据
//     */
//    public static LoginInfoEntity verifyLoginToken(String token)
//    {
//        Algorithm algorithm;
//        Map<String, Claim> map = new HashMap<String, Claim>();
//        LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
//        try
//        {
//            algorithm = Algorithm.HMAC256(PropertiesUtil.getValue(ConfigConstants.TOKEN_SECURITY));
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
//            DecodedJWT jwt = verifier.verify(token);
//            map = jwt.getClaims();
//            loginInfoEntity.setUserId(map.get(LoginInfoConstants.USER_ID).asInt());
//            loginInfoEntity.setPlatForm(map.get(LoginInfoConstants.PLAT_FORM).asString());
//            loginInfoEntity.setCurrentTime(map.get(LoginInfoConstants.CURRENT_TIME).asLong());
//        }
//        catch (Exception e)
//        {
//            loginInfoEntity = null;
//            logger.error("解密token失败:{}", e);
//        }
//        return loginInfoEntity;
//    }

//    /**
//     * 从请求头中获取token进行校验
//     * @param request
//     * @return
//     */
//    public static LoginInfoEntity validByRequestHead(HttpServletRequest request)
//    {
//        String token = request.getHeader(LoginInfoConstants.TOKEN_NAME);
//        //默认设置为未登录
//        if(request == null || StringUtil.isEmpty(token))
//        {
//            return null;
//        }
//        return JwtTokenUtil.verifyLoginToken(token);
//    }
    
    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> info = new HashMap<>();
		Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            info.put(key, value);
        }
        return info;
    }
    
    /**
     * 从headers获取token值
     */
    public static String getTokenValueFromHeaders(HttpServletRequest request) {
      	Map<String, String> reqParam = JwtTokenUtil.getHeadersInfo(request);
    	String loginToken = reqParam.get(LoginInfoConstants.TOKEN_NAME);
    	return loginToken;
    }
    
//    public static void main(String[] args)
//    {
//        LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
//        loginInfoEntity.setUserId(1111111111);
//        loginInfoEntity.setUserName("222222222");
//        loginInfoEntity.setLogin(true);
//        loginInfoEntity.setPlatForm("T");
//        String token = createLoginToken(loginInfoEntity);
//        System.out.println("生成的token:{}" + token);
//        LoginInfoEntity abc = verifyLoginToken(token);
//        System.out.println(abc.getPlatForm());
//    }

}
