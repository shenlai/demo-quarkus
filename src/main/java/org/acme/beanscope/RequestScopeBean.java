package org.acme.beanscope;

import io.quarkus.logging.Log;

import javax.enterprise.context.RequestScoped;

/**
 * @author rock
 * @Description TODO
 * @createTime 2022/10/09 17:52:00
 */
@RequestScoped
public class RequestScopeBean {


    /**
     * 构造函数，观察执行时机
     */
    public RequestScopeBean() {
        Log.info("execute create instance" + this.getClass().getSimpleName());
    }

    public String hello() {
        return "from " + this.getClass().getSimpleName();
    }
}
