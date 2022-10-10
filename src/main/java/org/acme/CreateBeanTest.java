package org.acme;

import org.acme.bean.CustomeBean;
import org.acme.service.BusinessService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;

@Path("/hello")
public class CreateBeanTest {

    /**
     * 创建bean方式：
     * A:  创建bean实例：注解修饰在类上
     * 用注解ApplicationScoped去修饰ClassAnnotationBean.类，如此quarkus就会实例化此类并放入容器中
     * <p>
     * B:  创建bean实例：注解修饰在方法上
     * Produces通知quarkus做实例化，ApplicationScoped表明了bean的作用域是整个应用 ,
     * quarkus还做了个简化：如果有了ApplicationScoped这样的作用域注解，那么Produces可以省略掉
     * <p>
     * C: 创建bean实例：注解修饰在成员变量上
     * Produces通知quarkus做实例化，ApplicationScoped表明了bean的作用域是整个应用
     * <p>
     * 使用bean：
     * 使用bean也很简单，如下，用注解Inject修饰ClassAnnotationBean类型的成员变量即可
     *
     * 相同类型多个Bean, 在申明bean和使用时需要指明Bean Name， 通过@Named注解
     */


    @Inject
    private CustomeBean customeBean;

    @GET
    @Path("/b1")
    @Produces(MediaType.TEXT_PLAIN)
    public String b1() {
        return "invoke b1:" + customeBean.print();
    }


    @Inject
    @Named("businessService")
    private BusinessService businessService;

    @GET
    @Path("/b2")
    @Produces(MediaType.TEXT_PLAIN)
    public String b2() {
        return "invoke b2:" + businessService.print();
    }


    @Inject
    @Named("businessService3")
    private BusinessService businessService3;

    @GET
    @Path("/b3")
    @Produces(MediaType.TEXT_PLAIN)
    public String b3() {
        return "invoke b3:" + businessService3.print();
    }


}