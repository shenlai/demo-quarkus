package org.acme.bean;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author rock
 * @Description TODO
 * @createTime 2022/09/02 17:43:00
 */

//创建bean实例-1：注解修饰在类上  ,ApplicationScoped表明了bean的作用域是整个应用
@ApplicationScoped
public class CustomeBean {

    public String print() {
        return "a bean from " + this.getClass().getSimpleName();
    }
}
