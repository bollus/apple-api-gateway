package com.easysign.gateway.entity.response;

import com.easysign.gateway.common.utils.IpUtil;
import com.easysign.gateway.entity.base.ResponseBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.entity.response
 * @ClassName: GatewayTokenResponse
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GatewayTokenResponse extends ResponseBase {
    private String token;
    private long tkTs;

    public void init(HttpServletRequest request) {
        this.token = "";
        this.tkTs = 0L;
        this.tkTs = System.currentTimeMillis();
        this.setClientIp(IpUtil.getIpAddr(request));
        this.setRequestAction(request.getRequestURL() + ":" + request.getMethod());
    }
}
