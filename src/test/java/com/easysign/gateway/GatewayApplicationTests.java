package com.easysign.gateway;

import com.easysign.gateway.common.utils.IpUtil;
import com.easysign.gateway.common.utils.RandomCodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Arrays.binarySearch;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        System.out.println(RandomCodeGenerator.generateShortUuid().substring(0, 12));
    }

}
