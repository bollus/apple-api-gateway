package com.easysign.gateway.common.sys;

/**
 * @ProjectName: SuperSign
 * @Package: com.cjml.supersign.common.utils
 * @Class: SysLogInfo
 * @Author: Strawberry
 * @Description: system log info
 * @CreatTime: 2020/09/22 11:04
 * @Version: 1.0
 **/
public class SysLogInfo {

    /**
     * 获取栈信息
     */
    private StackTraceElement[] getStack() {
        return new Throwable().getStackTrace();
    }

    /**
     * 获取类名
     */
    private String getCurrentClassName(int level) {
        return getStack()[level+1].getClassName();
    }

    /**
     * 获取方法名
     */
    private String getCurrentMethodName(int level) {
        return getStack()[level+1].getMethodName();
    }

    /**
     * 获取代码行
     */
    private int getLineNumber(int level) {
        return getStack()[level+1].getLineNumber();
    }

    @SuppressWarnings("unused")
    public String getStackInfo(int level) {
        return this.getCurrentClassName(level+1) + "-" + this.getCurrentMethodName(level+1) + "-" + this.getLineNumber(level+1);
    }

    public String getStackInfo() {
        return this.getCurrentClassName(2) + "-" + this.getCurrentMethodName(2) + "-Line:" + this.getLineNumber(2);
    }

}
