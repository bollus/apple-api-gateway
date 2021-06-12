package com.easysign.gateway.service;

import com.easysign.gateway.entity.request.CreateTokenRequest;
import com.easysign.gateway.entity.response.GatewayTokenResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service
 * @ClassName: GatewayService
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:56
 */
public interface GatewayService {
    GatewayTokenResponse token(CreateTokenRequest createTokenRequest, HttpServletRequest request);
}
