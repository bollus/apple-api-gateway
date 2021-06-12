package com.easysign.gateway.service;

import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.request.AppleCreateDeviceRequest;
import com.easysign.gateway.entity.response.AppleCreateDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceStatisticsResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service
 * @ClassName: DeviceService
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 17:27
 */
public interface DeviceService {
    AppleDeviceResponse list(AppleAccountRequest appleAccountRequest, HttpServletRequest request);

    AppleCreateDeviceResponse create(AppleCreateDeviceRequest appleCreateDeviceRequest, HttpServletRequest request);

    AppleDeviceStatisticsResponse statistics(AppleAccountRequest appleAccountRequest, HttpServletRequest request);

}
