package org.acme.bean;

import org.acme.service.BusinessService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author rock
 * @Description TODO
 * @createTime 2022/09/06 11:04:00
 */
//创建bean实例：注解修饰在方法上
public class CustomBeanTwo {

    /**
     * 使得quarkus调用此方法，将返回值作为bean实例注册到容器中，
     * Produces通知quarkus做实例化，
     * ApplicationScoped表明了bean的作用域是整个应用,在整个应用中只有一个实例
     *
     * @return
     */
    @Produces   //quarkus还做了个简化：如果有了ApplicationScoped这样的作用域注解，那么Produces可以省略掉
    @ApplicationScoped
    @Named("businessService")
    public BusinessService businessService() {
        return new BusinessService();
    }


    /**
     * 方式三，创建bean实例：注解修饰在成员变量上
     */
    @Produces
    @ApplicationScoped
    @Named("businessService3")
    public BusinessService businessService3 = new BusinessService();

}
