import cn.yiluoe.spring.datasource.service.DataSourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: spring-datasourc2
 * @description 单元测试
 * @user: 本以罗伊斯
 * @create: 2020/11/27 16:30
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-datasource-transaction-template.xml")
public class DataSourceTest {

    @Autowired
    private DataSourceService dataSourceService;

    @Test
    public void testFuc(){
        dataSourceService.insert("yiluoe",6);
    }

}
