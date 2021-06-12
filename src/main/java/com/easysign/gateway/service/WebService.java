package com.easysign.gateway.service;

import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.response.AppleAccountLoginResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service
 * @ClassName: WebService
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 19:59
 */
public interface WebService {
    AppleAccountLoginResponse login(AppleAccountRequest appleAccountRequest, HttpServletRequest request);
}
