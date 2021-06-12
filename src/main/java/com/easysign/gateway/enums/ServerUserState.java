package com.easysign.gateway.enums;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.enums
 * @ClassName: ServerUserState
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 4:10
 */
@SuppressWarnings("unused")
public enum ServerUserState {
    ENABLE("启用"),
    DISABLE("禁用"),
    EXCEPT("异常"),
    EXPIRED("过期");

    ServerUserState(String val) {}

    public static String getCode(ServerUserState serverUserState) {
        return serverUserState.name();
    }
}
