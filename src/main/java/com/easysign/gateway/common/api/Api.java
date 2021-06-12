package com.easysign.gateway.common.api;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.*;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.api
 * @ClassName: WebApi
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/11 21:12
 */
@BaseRequest(
        baseURL = "http://127.0.0.1:7418/api",
        headers = {
                "Content-Type:application/x-www-form-urlencoded"
        },
        sslProtocol = "TLS"
)
public interface Api {

    @Post("/login")
    JSONObject login(@DataParam("username") String username, @DataParam("password") String password);

    @Post("/device/list")
    JSONObject deviceList(@DataParam("username") String username, @DataParam("password") String password);

    @Post("/device/create")
    JSONObject createDevice(@DataParam("username") String username, @DataParam("password") String password, @DataParam("uuid") String uuid, @DataParam("name") String name);

    @Post("/device/statistics")
    JSONObject statisticsDevice(@DataParam("username") String username, @DataParam("password") String password);
}
