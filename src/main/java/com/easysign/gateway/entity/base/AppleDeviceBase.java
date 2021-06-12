package com.easysign.gateway.entity.base;

import lombok.Data;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.base
 * @ClassName: DeviceBase
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 17:31
 */
@Data
public class AppleDeviceBase {
    private String deviceId;
    private String name;
    private String type;
    private String uuid;
}

