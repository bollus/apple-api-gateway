package com.easysign.gateway.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.utils
 * @ClassName: TokenUtilsmm
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 5:12
 */
@SuppressWarnings("unused")
public class TokenUtilsmm {
    private static final Map<String,String> MAP_TOKENS = new HashMap<>();
    private static final int VALID_TIME = 60*30; // token有效期(秒)
    public static final String TOKEN_ERROR = "F"; // 非法
    public static final String TOKEN_OVERDUE = "G"; // 过期
    public static final String TOKEN_FAILURE = "S"; // 失效

    /**
     * 生成token,该token长度不一致,如需一致,可自行MD5或者其它方式加密一下
     * 该方式的token只存在磁盘上,如果项目是分布式,最好用redis存储
     * @param str: 该字符串可自定义,在校验token时要保持一致
     */
    public static String getToken(String str) {
        String token = TokenEncryptUtils.encoded(getCurrentTime()+"&"+str);
        MAP_TOKENS.put(str, token);
        return token;
    }

    /**
     * 校验token的有效性
     */
    public static String checkToken(String token) {
        if (token == null) {
            return TOKEN_ERROR;
        }
        try{
            String[] tArr = TokenEncryptUtils.decoded(token).split("&");
            if (tArr.length != 2) {
                return TOKEN_ERROR;
            }
            // token生成时间戳
            int tokenTime = Integer.parseInt(tArr[0]);
            // 当前时间戳
            int currentTime = getCurrentTime();
            if (currentTime-tokenTime < VALID_TIME) {
                String tokenStr = tArr[1];
                String mToken = MAP_TOKENS.get(tokenStr);
                if (mToken == null) {
                    return TOKEN_OVERDUE;
                } else if(!mToken.equals(token)) {
                    return TOKEN_FAILURE;
                }
                return tokenStr;
            } else {
                return TOKEN_OVERDUE;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return TOKEN_ERROR;
    }

    /**获取当前时间戳（10位整数）*/
    public static int getCurrentTime() {
        return (int)(System.currentTimeMillis()/1000);
    }

    /**
     * 移除过期的token
     */
    public static void removeInvalidToken() {
        int currentTime = getCurrentTime();
        for (Map.Entry<String,String> entry : MAP_TOKENS.entrySet()) {
            String[] tArr = TokenEncryptUtils.decoded(entry.getValue()).split(",");
            int tokenTime = Integer.parseInt(tArr[0]);
            if(currentTime-tokenTime > VALID_TIME){
                MAP_TOKENS.remove(entry.getKey());
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        String str = "{sdada:asdada,dasdad:123132,aaa:{vvv:123}}";

        // 获取token
//        String token = TokenUtilsmm.getToken(str);
        String token = "4546505b564c40465d495c0f0c051106034e11110c031c1753011f09101e054a53504741515a4e19171e5f050c02095b415051090d";
        System.out.println("token Result: " + token);

        // 校验token
        String checkToken = TokenUtilsmm.checkToken(token);
        System.out.println("checkToken Result: " + checkToken);
        if(str.equals(checkToken)) {
            System.out.println("==>token verification succeeded!");
        }

    }

}
