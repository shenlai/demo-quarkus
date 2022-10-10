package org.acme;

import io.quarkus.logging.Log;
import org.acme.beanscope.DependentBean;
import org.acme.beanscope.RequestScopeBean;
import org.acme.service.BusinessService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//单步调试方式：  参考： https://www.zhangshengrong.com/p/q0XpV2VkaK/
//1 :   运行 mvn quarkus:dev，启动应用
//2 :  idea   run-》Attach to process

@Path("/scope")
public class BeanScopeTest {


    /**
     * 作用域：
     * 常规作用域， 包括：ApplicationScoped、RequestScoped、SessionScoped三种,quarkus官方推荐使用的是ApplicationScoped
     * 伪作用域，包括：Singleton、Dependent两种
     * ApplicationScoped修饰的bean有代理类包裹，Singleton修饰的bean没有代理类, debug可看出
     * 见 Step1 和 Step2
     * <p>
     * <p>
     * RequestScoped :与当前请求绑定的作用域，,没次请求生产一个全新的实例，
     * <p>
     * Dependent是个伪作用域，它的特点是：每个依赖注入点的对象实例都不同,类似spring原型bean
     */

    @Inject
    @Named("businessServiceNormal")
    private BusinessService businessService;  //Step1: 如果是伪作用域，这里触发实例化
    //Singleton被quarkus划分为伪作用域，此时再回头品味下图，您是否恍然大悟：成员变量classAnnotationBean如果是Singleton，是没有代理类的，那就必须在@Inject位置实例化，否则，在get方法中classAnnotationBean就是null，会空指针异常的


    @GET
    @Path("/b3")
    @Produces(MediaType.TEXT_PLAIN)
    public String b3() {
        return "invoke b3:" + businessService.print();  //Step2: 如果是常规作用域，首次执行这里触发实例化
    }


    //RequestScoped 作用域测试
    @Inject
    RequestScopeBean requestScopeBean;


    @GET
    @Path("/b4")
    @Produces(MediaType.TEXT_PLAIN)
    public String b4() {
        return "invoke b4:" + requestScopeBean.hello();
    }


    @Inject
    DependentBean dependentBean;

    @Inject
    DependentBean dependentBean2;


    @GET
    @Path("/b5")
    @Produces(MediaType.TEXT_PLAIN)
    public String b5() {

        Class<DependentBean> clazz = DependentBean.class;

        Log.info(clazz.getSimpleName() + "/n" + dependentBean.hello());
        Log.info(clazz.getSimpleName() + "/n" + dependentBean2.hello());

        return "";
    }


}