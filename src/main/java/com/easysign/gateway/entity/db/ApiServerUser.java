package com.easysign.gateway.entity.db;

import com.easysign.gateway.common.annotations.CreateTime;
import com.easysign.gateway.common.annotations.SnowflakeId;
import com.easysign.gateway.common.annotations.UpdateTime;
import com.easysign.gateway.common.utils.RandomCodeGenerator;
import com.easysign.gateway.enums.ServerUserState;
import lombok.Data;
import org.apache.tomcat.util.security.MD5Encoder;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.db
 * @ClassName: ApiServerUser
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:40
 */
@Data
public class ApiServerUser {
    @SnowflakeId
    private Long id;
    @CreateTime
    private Date cTime;
    @UpdateTime
    private Date uTime;
    private String state;
    private long validPeriod;
    private String accessKey;
    private String accessSecret;
    private String serverIps;
    private String tTag;

    public void init(String ips, int day) {
        this.state = ServerUserState.ENABLE.name();
        this.validPeriod = System.currentTimeMillis()/1000 + 60L * 60 * 24 * day;
        this.accessKey = RandomCodeGenerator.generateShortUuid();
        this.accessSecret = MD5Encoder.encode(this.accessKey.getBytes(StandardCharsets.UTF_8)).toUpperCase(new Locale("vi"));
        this.serverIps = ips;
        this.tTag = RandomCodeGenerator.generateShortUuid().substring(0, 12);
    }
}
