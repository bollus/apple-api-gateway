package com.easysign.gateway.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.easysign.gateway.entity.base.ResponseBase;
import com.easysign.gateway.entity.db.ApiServerUser;
import com.easysign.gateway.enums.ResultCode;
import com.easysign.gateway.mapper.ApiServerUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.utils
 * @ClassName: TokenAuthenUtils
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 16:36
 */
@Component
public class TokenAuthenUtils {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ApiServerUserMapper apiServerUserMapper;

    public ResponseBase tokenAuth(HttpServletRequest request, String tTag) {
        ResponseBase response = new ResponseBase();
        response.init(request);
        if (StringUtils.isEmpty(request.getHeader("token"))) {
            response.setCode(ResultCode.UN_AUTHORIZED.getCode());
            response.setError("Api request token empty.");
        }
        if (!redisUtil.hasKey(tTag) ||
                !redisUtil.get(tTag).equals(request.getHeader("token"))) {
            response.setCode(ResultCode.UN_AUTHORIZED.getCode());
            response.setError("Api request token authentication error.");
        } else {
            String tokenStr = TokenUtilsmm.checkToken(request.getHeader("token"));
            switch (tokenStr) {
                case "F":
                    response.setCode(ResultCode.UN_AUTHORIZED.getCode());
                    response.setError("Api request token authentication illegal.");
                    break;
                case "G":
                    response.setCode(ResultCode.UN_AUTHORIZED.getCode());
                    response.setError("Api request token has been expired.");
                    break;
                case "S":
                    response.setCode(ResultCode.UN_AUTHORIZED.getCode());
                    response.setError("Api request token authentication failure.");
                    break;
                default:
                    JSONObject tokenJson = JSONObject.parseObject(tokenStr);
                    long uId = tokenJson.getLongValue("id");
                    String sIp = tokenJson.getString("serverIp");
                    if (!sIp.equals(IpUtil.getIpAddr(request))) {
                        response.setCode(ResultCode.CUSTOM_FAILED.getCode());
                        response.setError("request ip <" + IpUtil.getIpAddr(request) + "> not in server ip list,please register it.");
                    }
                    ApiServerUser apiServerUser = apiServerUserMapper.selectById(uId);
                    switch (apiServerUser.getState()) {
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
                    break;
            }
        }
        return response;
    }
}
