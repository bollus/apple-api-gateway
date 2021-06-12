package com.easysign.gateway.service.impl;

import com.easysign.gateway.common.api.Api;
import com.easysign.gateway.common.utils.*;
import com.easysign.gateway.entity.base.ResponseBase;
import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.response.AppleAccountLoginResponse;
import com.easysign.gateway.mapper.ApiServerUserMapper;
import com.easysign.gateway.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service
 * @ClassName: WebServiceImpl
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 20:18
 */
@Slf4j
@Service
public class WebServiceImpl implements WebService {

    @Resource
    private Api api;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TokenAuthenUtils tokenAuthenUtils;
    @Resource
    private ApiServerUserMapper apiServerUserMapper;

    @Override
    public AppleAccountLoginResponse login(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        appleAccountRequest.initTagKey();
        AppleAccountLoginResponse response = new AppleAccountLoginResponse();
        ResponseBase rsBase = tokenAuthenUtils.tokenAuth(request, appleAccountRequest.getTTag());
        BeanCopierUtil.copyNotNullProperties(rsBase, response);
        if (StringUtils.isEmpty(rsBase.getError())) {
            response.setResponse(api.login(appleAccountRequest.getUsername(), appleAccountRequest.getPassword()));
        }
        return response;
    }
}
