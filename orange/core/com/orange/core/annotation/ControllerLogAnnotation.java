package com.orange.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)
@Documented 
public  @interface ControllerLogAnnotation {    
    String description()  default "";
    String client() default "";
    String opt() default "";
}
