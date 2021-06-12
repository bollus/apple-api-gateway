package com.easysign.gateway.controller;

import com.easysign.gateway.entity.response.ServerUserRegisterResponse;
import com.easysign.gateway.enums.RequestMethod;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.service.ServerUserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.controller
 * @ClassName: ServerUserManageController
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 5:40
 */
@RestController
@RequestMapping("/o/admin")
@Slf4j
public class ServerUserManageController {
    @Resource
    private ServerUserManageService serverUserManageService;

    @RequestMapping("/regUser")
    public ServerUserRegisterResponse registerServerUser(String ips, int day, HttpServletRequest request) {
        ServerUserRegisterResponse response = new ServerUserRegisterResponse();
        if (!request.getMethod().equals(RequestMethod.POST.name())) {
            response.init(request);
            response.setCode(ResultCode.METHOD_NOT_SUPPORTED.getCode());
            response.setError("This Method Not Allowed");
        } else {
            response = serverUserManageService.registerServerUser(ips, day, request);
        }
        return response;
    }
}
