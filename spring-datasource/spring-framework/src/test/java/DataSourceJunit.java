import cn.yiluoe.spring.service.DataSourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @program: spring-datasource
 * @description 数据源单元测试
 * @user: 本以罗伊斯
 * @create: 2020/11/26 11:54
 **/

/*

调用关系：
    表现层通过注解去ioc里寻找逻辑层对象然后调用它的insert方法，而逻辑层注入数据层的接口调用数据层的方法，
    数据层通过注解去ioc里找jdbc模板类，jdbc模板类注入了一个可以实现动态切换数据源的类Switcher，
    最后逻辑层的方法被Advice类所代理，如果操作数据库发生异常Advice则调用Switcher的方法切换数据源后继续插入。

    注意：动态切换数据源的类DataSourceSwitcher 它的父类自身就是一个动态数据源，当它被注入模板类时它会指定一个默认的数据源。

    我好奇的是：DataSourceSwitcher是怎样与自己的属性ThreadLocal<String>绑定起来的。（反射？不会吧，不会吧。）
        如果闲了可以深究一下源码。

*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-datasource.xml")
@Controller
public class DataSourceJunit {

    @Autowired
    private DataSourceService dataSourceService;

    @Test
    public void test() throws SQLException {

        dataSourceService.insert("yiluoeeeee",666);

        /*System.out.println("ababaDataSource：" + dataSource);
        System.out.println(dataSource.getConnection());*/
    }

}
