package com.orange.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented 
public  @interface ServiceLogAnnotation {    
    String description()  default "";    
}
