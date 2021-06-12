package com.easysign.gateway.service;

import com.easysign.gateway.entity.response.ServerUserRegisterResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service
 * @ClassName: ServerUserManageService
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 5:44
 */
public interface ServerUserManageService {
    ServerUserRegisterResponse registerServerUser(String ips, int day, HttpServletRequest request);
}
