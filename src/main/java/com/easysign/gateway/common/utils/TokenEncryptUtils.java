package com.easysign.gateway.common.utils;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.utils
 * @ClassName: TokenEncryptUtils
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 5:11
 */
public class TokenEncryptUtils {
    // 编码密码,可自定义
    private static final String ENCODED_PASSWORD = "easysigntokenpass";

    /**
     * 编码
     */
    public static String encoded(String str) {
        return strToHex(encodedString(str));
    }

    /**
     * 转换
     */
    private static String encodedString(String str) {
        char[] pwd = TokenEncryptUtils.ENCODED_PASSWORD.toCharArray();
        int pwdLen = pwd.length;

        char[] strArray = str.toCharArray();
        for (int i=0; i<strArray.length; i++) {
            strArray[i] = (char)(strArray[i] ^ pwd[i%pwdLen] ^ pwdLen);
        }
        return new String(strArray);
    }

    private static String strToHex(String s) {
        return bytesToHexStr(s.getBytes());
    }

    private static String bytesToHexStr(byte[] bytesArray) {
        StringBuilder builder = new StringBuilder();
        String hexStr;
        for (byte bt : bytesArray) {
            hexStr = Integer.toHexString(bt & 0xFF);
            if (hexStr.length() == 1) {
                builder.append("0");
            }
            builder.append(hexStr);
        }
        return builder.toString();
    }

    /**
     * 解码
     */
    public static String decoded(String str) {
        String hexStr = null;
        try {
            hexStr = hexStrToStr(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hexStr != null) {
            hexStr = encodedString(hexStr);
        }
        return hexStr;
    }

    private static String hexStrToStr(String hexStr) {
        return new String(hexStrToBytes(hexStr));
    }

    private static byte[] hexStrToBytes(String hexStr) {
        String hex;
        int val;
        byte[] btHexStr = new byte[hexStr.length()/2];
        for (int i=0; i<btHexStr.length; i++) {
            hex = hexStr.substring(2*i, 2*i+2);
            val = Integer.valueOf(hex, 16);
            btHexStr[i] = (byte) val;
        }
        return btHexStr;
    }


}
