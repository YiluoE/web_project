import cn.yiluoe.spring.ioc.service.IocService;
import cn.yiluoe.spring.ioc.service.impl.IocServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @program: spring-family
 * @description 单元测试
 * @user: 本以罗伊斯
 * @create: 2020/11/23 23:49
 **/
public class IocJunit {

    private ApplicationContext applicationContext;

    @Before
    public void init()
    {
        /*不友好，根据绝对路径在系统上寻找*/
        /*Resource resource = new FileSystemResource("...");*/

        /*因为项目目录resource编译后结果会被放到target的classes目录*/
        /*所以通过文件名可以直接在classpath目录下找到*/
        /*Resource resource = new ClassPathResource("spring-ioc.xml");*/

        /*它提供了更多的接口*/
        applicationContext = new ClassPathXmlApplicationContext(new String[]{"spring-ioc.xml","applicationContext.xml"});

    }

    @Test
    public void test(){
        //System.out.println("=v=");

        /*传统方式*/
        /*IocService iocService = new IocServiceImpl();
        iocService.insert();*/

        IocService iocService = applicationContext.getBean("iocService",IocServiceImpl.class);
        System.out.println(iocService.insert());
    }

}
