package com.easysign.gateway.entity.response;

import com.alibaba.fastjson.JSONObject;
import com.easysign.gateway.entity.base.AppleDeviceBase;
import com.easysign.gateway.entity.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.response
 * @ClassName: AppleDeviceResponse
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 17:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppleCreateDeviceResponse extends ResponseBase {
    private AppleDeviceBase device;

    public void setResponse(JSONObject object) {
        this.setCode(object.getInteger("code"));
        this.setError(object.getString("error"));
        if (object.get("device") != null) {
            this.device = object.getObject("device", AppleDeviceBase.class);
        }
    }
}
