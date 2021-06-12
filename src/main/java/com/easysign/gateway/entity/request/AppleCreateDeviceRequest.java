package com.easysign.gateway.entity.request;

import com.easysign.gateway.entity.base.AppleAccountBase;
import com.easysign.gateway.entity.base.RequestBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.request
 * @ClassName: AppleCreateDeviceRequest
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 18:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppleCreateDeviceRequest extends AppleAccountBase {
    private String uuid;
}
