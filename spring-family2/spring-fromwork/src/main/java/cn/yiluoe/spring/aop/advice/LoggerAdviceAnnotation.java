package cn.yiluoe.spring.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @program: spring-family2
 * @description
 * @user: 本以罗伊斯
 * @create: 2020/11/25 11:51
 **/

@Component
@Aspect /*1.声明切面*/
public class LoggerAdviceAnnotation {

    /*2.声明切入点,方法名即是切入点ID*/
    @Pointcut(value = "execution(* cn.yiluoe.spring.*.service.impl.AopServiceImpl.*(..))")
    public void methodPointcut(){/*我不是方法,我是切入点。*/}

    /*3.切入,或称绑定通知*//*直接获取到连接点参数*//*如果只想获得参数则使用这种方法*//*参数名可以与连接点方法参数名不同*/
    @After(value = "methodPointcut() && args(name,age)")
    public void after(String name,int age){ /*这里的参数名必须与上面的对应。*/
        System.out.println(name + ":" + age);
    }

    /*3.切入,或称绑定通知*/
    //@Around(value = "methodPointcut()")
    /*4.加入连接点参数ProceedingJoinPoint pjp链接点，可以获取连接点的信息*//*链接点既 被代理的类*/
    /*public Object logger(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("调用前添加：" + LocalTime.now());
        long value = (Long)pjp.proceed(); *//*放行*//*
        System.out.println("---------------------------------------");
        System.out.println("委托类的类名：" + pjp.getTarget().getClass().getName());
        System.out.println("连接点方法：" + pjp.getSignature().getName());

        System.out.println("连接点参数：");
        Object[] args = pjp.getArgs();
        for (Object o : args)
            System.out.println("\t" + o.getClass().getSimpleName() + ":" + o);



        System.out.println("---------------------------------------");
        System.out.println("调用后添加：" + LocalTime.now());
        return value;
    }*/

    /*3.切入,或称绑定通知*/
    //@Before(value = "methodPointcut()")
    /*4.加入连接点参数JoinPoint pjp可以获取连接点的信息*/
    /*ProceedingJoinPoint 与 JoinPoint的区别是，前者可以使用放行方法，后者不需要*/
    /*public Object logger(ProceedingJoinPoint pjp) throws Throwable {
        return null;
    }*/

}
