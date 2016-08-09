package com.polite.servlermvc.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放所有bean的池
 * @author polite
 * @date 2016-08-09 .
 */
public class BeanFactory {
    private static Map<String, Object> beansMap = new HashMap<>();

    public static void addBean(String beanName, Object bean){
        beansMap.put(beanName, bean);
    }

    public static Object getBean(String beanName) throws Exception {
        Object o = beansMap.get(beanName);
        if (o != null) {
            return  o;
        }else {
            throw new Exception(beanName+" not be injected");
        }
    }

    public static Boolean containsBean(String beanName){
        return beansMap.containsKey(beanName);
    }
}
