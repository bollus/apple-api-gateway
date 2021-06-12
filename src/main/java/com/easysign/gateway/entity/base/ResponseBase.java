package com.easysign.gateway.entity.base;

import com.easysign.gateway.common.utils.IpUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity
 * @ClassName: ResultBase
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 20:00
 */
@Data
public class ResponseBase {
    private Integer code;
    private String error;
    private String clientIp;
    private String requestAction;

    public void init(HttpServletRequest request) {
        this.error = "";
        this.setClientIp(IpUtil.getIpAddr(request));
        this.setRequestAction(request.getRequestURL() + ":" + request.getMethod());
    }
}
