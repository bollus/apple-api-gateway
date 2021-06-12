package com.easysign.gateway.entity.response;

import com.alibaba.fastjson.JSONObject;
import com.easysign.gateway.entity.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.response
 * @ClassName: AppleAccountLoginResponse
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 20:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppleAccountLoginResponse extends ResponseBase {
    private String cookie = "";
    private String teamId = "";

    public void setResponse(JSONObject object) {
        this.setCode(object.getInteger("code"));
        this.cookie = object.getString("cookie");
        this.setError(object.getString("error"));
        this.teamId = object.getString("teamid");
    }
}

