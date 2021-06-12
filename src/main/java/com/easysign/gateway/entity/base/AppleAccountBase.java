package com.easysign.gateway.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity
 * @ClassName: AppleAcount
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 19:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppleAccountBase extends RequestBase {
    private String username;
    private String password;
}
