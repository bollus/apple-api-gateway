package com.easysign.gateway.common.utils;

import java.util.UUID;

/**
 * @ProjectName: SuperSign
 * @Package: com.cjml.supersign.sys.user.utils
 * @ClassName: RandomCodeGenerator
 * @Author: Strawberry
 * @Description: 唯一随机码生成器
 * @Date: 2020/09/23 17:53
 * @Version: 1.0
 */
public class RandomCodeGenerator {

    private static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String generateShortUuid() {
        StringBuilder stringBuilder = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "")+UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str;
            str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            stringBuilder.append(chars[x % 0x3E]);
        }
        return stringBuilder.toString();

    }
}
