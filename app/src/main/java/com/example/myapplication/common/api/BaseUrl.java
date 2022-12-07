package com.example.myapplication.common.api;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2022.12.07
 */
@Documented
@Target(TYPE)
@Retention(RUNTIME)
public @interface BaseUrl {
    String value() default "";
}
