package com.easysign.gateway.entity.response;

import com.alibaba.fastjson.JSONObject;
import com.easysign.gateway.entity.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.response
 * @ClassName: AppleDeviceStatisticsResponse
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 18:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppleDeviceStatisticsResponse extends ResponseBase {
    private Integer ipad = 0;
    private Integer iphone = 0;
    private Integer ipod = 0;
    private Integer tv = 0;
    private Integer watch = 0;

    public void setResponse(JSONObject object) {
        this.setCode(object.getInteger("code"));
        this.setError(object.getString("error"));
        if (object.get("ipad") != null) {
            this.ipad = object.getInteger("ipad");
            this.iphone = object.getInteger("iphone");
            this.ipod = object.getInteger("ipod");
            this.tv = object.getInteger("tv");
            this.watch = object.getInteger("watch");
        }
    }
}
