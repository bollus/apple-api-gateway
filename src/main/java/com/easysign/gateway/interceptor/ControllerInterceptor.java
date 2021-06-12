package com.easysign.gateway.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @ProjectName: gateway
 * @Package: com.easysign.gateway.interceptor
 * @ClassName: ControllerInterceptor
 * @Author: Strawberry
 * @Description:
 * @Date: 2021/06/12 16:59
 */
@Slf4j
@SuppressWarnings("unused")
public class ControllerInterceptor {

    private static final String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};

    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * 定义拦截规则：拦截com.dxyl.controller..*(..))包下面的所有类中，有@PostMapping注解的方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }

    @Before("controllerMethodPointcut()")
    public void controller(JoinPoint point) {
        startTime.set(System.currentTimeMillis());
        MethodSignature signature = (MethodSignature) point.getSignature();
        long count = Stream.of(signature.getMethod().getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == PostMapping.class)
                .count();
        String requestPath = count >= 1 ? signature.getMethod().getAnnotation(PostMapping.class).value()[0] : "";

        String info = String.format("\n =======> 请求路径: %s  %s", requestPath, getMethodInfo(point));
        log.info(info);
    }

    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                // 对参数解析(参数有可能为基础数据类型，也可能为一个对象，若为对象则需要解析对象中变量名以及值)
                String value = "";
                if (point.getArgs()[i] == null) {
                    value = "null";
                } else {
                    // 获取对象类型
                    String typeName = point.getArgs()[i].getClass().getTypeName();
                    boolean flag = false;
                    for (String t : types) {
                        //1 判断是否是基础类型
                        if (t.equals(typeName)) {
                            value = point.getArgs()[i].toString();
                            flag = true;
                        }
                        if (flag) {
                            break;
                        }
                    }
                    if (!flag) {
                        //2 通过反射获取实体类属性
                        value = getFieldsValue(point.getArgs()[i]);
                    }
                }
                sb.append(parameterNames[i]).append(":").append(value).append("; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        return String.format("\n =======> 请求类名: %s \n =======> 请求方法: %s \n =======> 请求参数: %s", className, methodName, sb.toString());
    }

    /**
     *  解析实体类，获取实体类中的属性
     */
    public static String getFieldsValue(Object obj) {
        //通过反射获取所有的字段，getFileds()获取public的修饰的字段
        //getDeclaredFields获取private protected public修饰的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getTypeName();
        for (String t : types) {
            if (t.equals(typeName)) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Field f : fields) {
            //在反射时能访问私有变量
            f.setAccessible(true);
            try {
                for (String str : types) {
                    //这边会有问题，如果实体类里面继续包含实体类，这边就没法获取。
                    //其实，我们可以通递归的方式去处理实体类包含实体类的问题。
                    if (f.getType().getName().equals(str)) {
                        sb.append(f.getName()).append(" : ").append(f.get(obj)).append(", ");
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     *  计算接口执行时间
     */
    @AfterReturning(pointcut = "controllerMethodPointcut()")
    public void doAfterReturing() {
        long costTime = System.currentTimeMillis() - startTime.get();
        log.info("\n =======> 耗费时间: " + costTime + "ms");
    }
}
