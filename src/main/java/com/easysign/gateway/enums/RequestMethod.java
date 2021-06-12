package com.easysign.gateway.enums;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.enums
 * @ClassName: RequestMethod
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 2:57
 */
@SuppressWarnings("unused")
public enum RequestMethod {
    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE"),
    COPY("COPY"),
    HEAD("HEAD"),
    OPTION("OPTION"),
    LINK("LINK"),
    UNLINK("UNLINK"),
    PURGE("PURGE"),
    LOCK("LOCK"),
    UNLOCK("UNLOCK"),
    PROPFIND("PROPFIND"),
    VIEW("VIEW");

    RequestMethod(String val) {}
}
