package com.easysign.gateway.entity.response;

import com.easysign.gateway.entity.base.ResponseBase;
import com.easysign.gateway.entity.db.ApiServerUser;
import com.easysign.gateway.enums.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.response
 * @ClassName: ServerUserRegisterResponse
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 23:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerUserRegisterResponse extends ResponseBase {
    private String accessKey = "";
    private String accessSecret = "";
    private String tTag = "";

    public void setResponse(ApiServerUser apiServerUser) {
        this.setCode(ResultCode.SUCCESS.getCode());
        this.setError("");
        this.accessKey = apiServerUser.getAccessKey();
        this.accessSecret = apiServerUser.getAccessSecret();
        this.tTag = apiServerUser.getTTag();
    }
}
