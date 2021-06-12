package com.easysign.gateway.entity.response;

import com.alibaba.fastjson.JSONObject;
import com.easysign.gateway.entity.base.AppleDeviceBase;
import com.easysign.gateway.entity.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

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
public class AppleDeviceResponse extends ResponseBase {
    private List<AppleDeviceBase> devices = new ArrayList<>();

    public void setResponse(JSONObject object) {
        this.setCode(object.getInteger("code"));
        if (object.getJSONArray("devices") != null) {
            this.devices = object.getJSONArray("devices").toJavaList(AppleDeviceBase.class);
        }
    }
}
