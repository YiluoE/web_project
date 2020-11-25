import cn.yiluoe.spring.ioc.service.IocService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @program: spring-family2
 * @description 单元测试
 * @user: 本以罗伊斯
 * @create: 2020/11/24 19:41
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-ioc.xml")
public class IocJunit {

    @Resource
    private IocService iocService;

    /*@Resource
    public void setIocService(IocService iocService) {
        this.iocService = iocService;
    }*/

    @Test
    public void test(){
        System.out.println(iocService.insert());
    }

}
