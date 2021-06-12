package com.easysign.gateway.controller;

import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.request.CreateTokenRequest;
import com.easysign.gateway.entity.response.AppleAccountLoginResponse;
import com.easysign.gateway.entity.response.GatewayTokenResponse;
import com.easysign.gateway.enums.RequestMethod;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.service.GatewayService;
import com.easysign.gateway.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.controller
 * @ClassName: AppleApiController
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 20:27
 */
@RestController
@RequestMapping("/o/apple")
@Slf4j
public class AppleApiController {

    @Resource
    private GatewayService gatewayService;
    @Resource
    private WebService webService;

    @RequestMapping("token")
    public GatewayTokenResponse token(CreateTokenRequest createTokenRequest, HttpServletRequest request) {
        GatewayTokenResponse response = new GatewayTokenResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = gatewayService.token(createTokenRequest, request);
        }
        return response;
    }

    @RequestMapping("login")
    public AppleAccountLoginResponse login(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        AppleAccountLoginResponse response = new AppleAccountLoginResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = webService.login(appleAccountRequest,request);
        }
        return response;
    }
}
