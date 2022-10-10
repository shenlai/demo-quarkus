package org.acme.beanscope;

import io.quarkus.logging.Log;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author rock
 * @Description Dependent是个伪作用域，它的特点是：每个依赖注入点的对象实例都不同,类似spring原型bean
 * @createTime 2022/10/09 18:12:00
 */
@Dependent
public class DependentBean {

    public DependentBean(InjectionPoint injectionPoint) {
        Log.info("injecting from bean " + injectionPoint.getMember().getDeclaringClass());
    }

    public String hello() {
        return this.getClass().getSimpleName();
    }
}
