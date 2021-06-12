package com.easysign.gateway.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.easysign.gateway.common.utils.IpUtil;
import com.easysign.gateway.common.utils.RedisUtil;
import com.easysign.gateway.common.utils.TokenUtilsmm;
import com.easysign.gateway.entity.db.ApiServerUser;
import com.easysign.gateway.entity.request.CreateTokenRequest;
import com.easysign.gateway.entity.response.GatewayTokenResponse;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.mapper.ApiServerUserMapper;
import com.easysign.gateway.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import static java.util.Arrays.binarySearch;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.service.impl
 * @ClassName: GatewayServiceImpl
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:56
 */
@Service
@Slf4j
public class GatewayServiceImpl implements GatewayService{
    @Resource
    private ApiServerUserMapper apiServerUserMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public GatewayTokenResponse token(CreateTokenRequest createTokenRequest, HttpServletRequest request) {
        GatewayTokenResponse response = new GatewayTokenResponse();
        response.init(request);
        ApiServerUser apiServerUser = apiServerUserMapper.selectOne(new LambdaQueryWrapper<ApiServerUser>()
                .eq(ApiServerUser::getAccessKey, createTokenRequest.getAccessKey())
                .eq(ApiServerUser::getAccessSecret, createTokenRequest.getAccessSecret()));
        if (apiServerUser == null) {
            response.setCode(ResultCode.CUSTOM_FAILED.getCode());
            response.setError("server user not be registered.");
        } else {
            switch (apiServerUser.getState()) {
                case "ENABLE":
                    String[] ips = apiServerUser.getServerIps().split(",");
                    if (binarySearch(ips, IpUtil.getIpAddr(request)) < 0) {
                        response.setCode(ResultCode.CUSTOM_FAILED.getCode());
                        response.setError("request ip <" + IpUtil.getIpAddr(request) + "> not in server ip list,please register it.");
                    } else {
                        JSONObject tokenJson = new JSONObject();
                        tokenJson.put("id", apiServerUser.getId());
                        tokenJson.put("accessKey", apiServerUser.getAccessKey());
                        tokenJson.put("accessSecret", apiServerUser.getAccessSecret());
                        tokenJson.put("serverIp", IpUtil.getIpAddr(request));
                        String token = TokenUtilsmm.getToken(tokenJson.toJSONString());
                        redisUtil.setOverride("T_TAG:"+apiServerUser.getTTag(), token, (60 * 30) + 5);
                        response.setToken(token);
                        response.setCode(ResultCode.SUCCESS.getCode());
                    }
                    break;
                case "DISABLE":
                    response.setCode(ResultCode.CUSTOM_FAILED.getCode());
                    response.setError("server user has bean disabled.");
                    break;
                case "EXCEPT":
                    response.setCode(ResultCode.CUSTOM_FAILED.getCode());
                    response.setError("server user excepted.");
                    break;
                case "EXPIRED":
                    response.setCode(ResultCode.CUSTOM_FAILED.getCode());
                    response.setError("server user api auth expired.");
                    break;
            }
        }
        return response;
    }

}
