package com.easysign.gateway.entity.request;

import com.easysign.gateway.entity.base.RequestBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.request
 * @ClassName: CreateTokenRequest
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateTokenRequest extends RequestBase {
    private String accessKey;
    private String accessSecret;
}
