package com.jafe.comm.util;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CourierCase {
    //方法递增id
    public   int id();
    public String descripetion() default "no dscription";
}
