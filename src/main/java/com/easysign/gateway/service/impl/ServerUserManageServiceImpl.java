package com.easysign.gateway.service.impl;

import com.easysign.gateway.entity.db.ApiServerUser;
import com.easysign.gateway.entity.response.ServerUserRegisterResponse;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.mapper.ApiServerUserMapper;
import com.easysign.gateway.service.ServerUserManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service.impl
 * @ClassName: ServerUserManageServiceImpl
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 5:45
 */
@Service
@Slf4j
public class ServerUserManageServiceImpl implements ServerUserManageService {
    @Resource
    private ApiServerUserMapper apiServerUserMapper;

    @Override
    public ServerUserRegisterResponse registerServerUser(String ips, int day, HttpServletRequest request) {
        ServerUserRegisterResponse response = new ServerUserRegisterResponse();
        response.init(request);
        ApiServerUser apiServerUser = new ApiServerUser();
        apiServerUser.init(ips, day);
        if (apiServerUserMapper.insert(apiServerUser) == 1) {
            response.setResponse(apiServerUser);
        } else {
            response.setCode(ResultCode.FAILURE.getCode());
            response.setError("An unexpected error occurred.");
        }
        return response;
    }
}
