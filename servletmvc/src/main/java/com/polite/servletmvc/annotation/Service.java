package com.polite.servletmvc.annotation;

import java.lang.annotation.*;

/**
 * @author polite
 * @date 2016-08-09 .
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
    String value() ;
}
