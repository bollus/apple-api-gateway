package com.easysign.gateway.service.impl;

import com.easysign.gateway.common.api.Api;
import com.easysign.gateway.common.utils.BeanCopierUtil;
import com.easysign.gateway.common.utils.ShortUuidUtils;
import com.easysign.gateway.common.utils.TokenAuthenUtils;
import com.easysign.gateway.entity.base.ResponseBase;
import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.request.AppleCreateDeviceRequest;
import com.easysign.gateway.entity.response.AppleCreateDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceStatisticsResponse;
import com.easysign.gateway.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service.impl
 * @ClassName: DeviceServiceImpl
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 17:28
 */
@Service
@Slf4j
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private TokenAuthenUtils tokenAuthenUtils;
    @Resource
    private Api api;

    @Override
    public AppleCreateDeviceResponse create(AppleCreateDeviceRequest appleCreateDeviceRequest, HttpServletRequest request) {
        appleCreateDeviceRequest.initTagKey();
        AppleCreateDeviceResponse response = new AppleCreateDeviceResponse();
        ResponseBase rsBase = tokenAuthenUtils.tokenAuth(request, appleCreateDeviceRequest.getTTag());
        BeanCopierUtil.copyNotNullProperties(rsBase, response);
        if (StringUtils.isEmpty(rsBase.getError())) {
            String name = ShortUuidUtils.generateShortUuid(8);
            response.setResponse(api.createDevice(appleCreateDeviceRequest.getUsername(),
                    appleCreateDeviceRequest.getPassword(),
                    appleCreateDeviceRequest.getUuid(),
                    name));
        }
        return response;
    }

    @Override
    public AppleDeviceResponse list(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        appleAccountRequest.initTagKey();
        AppleDeviceResponse response = new AppleDeviceResponse();
        ResponseBase rsBase = tokenAuthenUtils.tokenAuth(request, appleAccountRequest.getTTag());
        BeanCopierUtil.copyNotNullProperties(rsBase, response);
        if (StringUtils.isEmpty(rsBase.getError())) {
            response.setResponse(api.deviceList(appleAccountRequest.getUsername(), appleAccountRequest.getPassword()));
        }
        return response;
    }

    @Override
    public AppleDeviceStatisticsResponse statistics(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        appleAccountRequest.initTagKey();
        AppleDeviceStatisticsResponse response = new AppleDeviceStatisticsResponse();
        ResponseBase rsBase = tokenAuthenUtils.tokenAuth(request, appleAccountRequest.getTTag());
        BeanCopierUtil.copyNotNullProperties(rsBase, response);
        if (StringUtils.isEmpty(rsBase.getError())) {
            response.setResponse(api.statisticsDevice(appleAccountRequest.getUsername(), appleAccountRequest.getPassword()));
        }
        return response;
    }
}
