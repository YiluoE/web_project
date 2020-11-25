import cn.yiluoe.spring.aop.service.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: spring-family2
 * @description \
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:37
 **/

/*@Autowired
    private AopService proxyAopService;*/
/*自动装配如果没有找到返回的不是null? 为什么没有出现空指异常。*/
/*或者不应该是fount俩么？*/
/*---------------------------------------------------*/
/*首先按名称去ioc寻找对应名称的bean但是该类并没有设置value（重点根本没有注解为控件）*/
/*如果注解为控件但没有给value的话，ioc会自动将该类名首字符小写引入到context,所以还是能找到的*/
/*那么问题来没注解为控件它就不可能找到，找到不应该抛出没找到bean的异常吗？*/
/*原因是这个接口有两个子类吖~ 而且最后自动装配按类型查找到的第二个实现类的方法没有任何输出*/
/*所以就造成了一个错觉，我草我没理解透。*/
/*那么问题又来了调试模式为什么装配第一个就能显示值按类型装配第二个的时候就没有值呢？*/
/*--------------------------------------------------*/
/*首先按照被自动装配注解的实例名去寻找bean如果没找到则按类型去寻找如果还没找到实现类或该类型 则报错*/
/*如果有两个实现类都没配置id或name则会根据 被注解的标识符 去匹配 实现类的类名首字符小写的实现类 优先装配被 @Primary注解的实现类*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-aop-annotation.xml")
public class AopJunitAnnotation {

    /*静态代理*/
    /*@Autowired
    private AopService proxyAopService;*/

    /*动态代理*/
    @Autowired
    private AopService aopServiceImpl;

    @Test
    public void run(){
        aopServiceImpl.insertAno("yiluoe",99);
        //aopService.update();
    }

}