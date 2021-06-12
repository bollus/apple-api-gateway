package com.easysign.gateway.entity.base;

import lombok.Data;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.base
 * @ClassName: RequestBase
 * @Author: Strawberry
 * @Description: \
 * @Date: 2021/06/11 20:06
 */
@Data
public class RequestBase {
    private String tTag;

    public void initTagKey() {
        this.setTTag("T_TAG:"+getTTag());
    }
}
