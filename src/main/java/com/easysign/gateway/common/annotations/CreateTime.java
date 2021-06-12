package com.easysign.gateway.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.common.annotations
 * @ClassName: CreateTime
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 3:40
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CreateTime {
}
