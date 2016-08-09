package com.polite.servlermvc.annotation;

import java.lang.annotation.*;

/**
 * @author polite
 * @date 2016-08-09 .
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    String value();
}
