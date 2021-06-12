package com.easysign.gateway.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanCopierUtil {

    @SuppressWarnings("unused")
    public static <T> T copy(Object src, Class<T> c) {
        try {
            T t = c.newInstance();
            BeanCopier.create(src.getClass(), c, false).copy(src, t, null);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @SuppressWarnings("unused")
    public static <T> List<T> copy(List<T> srcList, Class<T> c) {
        try {
            List<T> newList = new ArrayList<>();
            for (Object src : srcList) {
                T t = c.newInstance();
                BeanCopier.create(src.getClass(), c, false).copy(src, t, null);
                newList.add(t);
            }
            return newList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取值为Null的字段名称
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 属性复制，跳过为null的值
     * @param source    提供值的obj
     * @param target    接收值的obj
     */
    public static <T> void copyNotNullProperties(T source, T target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }



    /**
     * 该方法是用于相同对象不同属性值的合并，如果两个相同对象中同一属性都有值，
     *               那么sourceBean中的值会覆盖tagetBean重点的值
     * @param sourceBean
     *            被提取的对象bean
     * @param targetBean
     *            用于合并的对象bean
     * @return targetBean 合并后的对象
     */
    @SuppressWarnings("all")
    public static Object combineBean(Object sourceBean, Object targetBean) {
        Class sourceBeanClass = sourceBean.getClass();
        Class targetBeanClass = targetBean.getClass();
        Field[] sourceFields = sourceBeanClass.getDeclaredFields();
        Field[] targetFields = sourceBeanClass.getDeclaredFields();
        for (int i = 0; i < sourceFields.length; i++) {
            Field sourceField = sourceFields[i];
            Field targetField = targetFields[i];
            sourceField.setAccessible(true);
            targetField.setAccessible(true);
            try {
                if (!(sourceField.get(sourceBean) == null)) {
                    if (targetField.getType() == String.class){
                        if (!StringUtils.isEmpty(sourceField.get(sourceBean))){
                            targetField.set(targetBean, sourceField.get(sourceBean));
                        }
                    }else {
                        targetField.set(targetBean, sourceField.get(sourceBean));
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return targetBean;
    }


}
