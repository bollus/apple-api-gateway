package com.easysign.gateway.controller;

import com.easysign.gateway.entity.request.AppleAccountRequest;
import com.easysign.gateway.entity.request.AppleCreateDeviceRequest;
import com.easysign.gateway.entity.response.AppleCreateDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceResponse;
import com.easysign.gateway.entity.response.AppleDeviceStatisticsResponse;
import com.easysign.gateway.enums.RequestMethod;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.controller
 * @ClassName: AppleApiDeviceController
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 17:26
 */
@RestController
@RequestMapping("/o/apple/device")
@Slf4j
public class AppleApiDeviceController {

    @Resource
    private DeviceService deviceService;

    @RequestMapping("create")
    public AppleCreateDeviceResponse create(AppleCreateDeviceRequest appleCreateDeviceRequest, HttpServletRequest request) {
        AppleCreateDeviceResponse response = new AppleCreateDeviceResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = deviceService.create(appleCreateDeviceRequest, request);
        }
        return response;
    }

    @RequestMapping("list")
    public AppleDeviceResponse list(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        AppleDeviceResponse response = new AppleDeviceResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = deviceService.list(appleAccountRequest, request);
        }
        return response;
    }

    @RequestMapping("statistics")
    public AppleDeviceStatisticsResponse statistics(AppleAccountRequest appleAccountRequest, HttpServletRequest request) {
        AppleDeviceStatisticsResponse response = new AppleDeviceStatisticsResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = deviceService.statistics(appleAccountRequest, request);
        }
        return response;
    }
}
