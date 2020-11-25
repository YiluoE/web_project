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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-aop.xml")
public class AopJunit {

    @Autowired
    private AopService aopService; /*junit为什么会自动执行其它的单元测试呢？*/

    @Test
    public void run(){
        aopService.insert();
        aopService.update();
    }

}
