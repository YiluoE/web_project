import cn.yiluoe.ssm.user.entity.User;
import cn.yiluoe.ssm.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: ssm
 * @description 单元测试
 * @user: 本以罗伊斯
 * @create: 2020/11/29 23:22
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml") /*只测逻辑层向下*/
public class SsmJunitTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        /*List<User> list = userService.queryAll();
        list.forEach( e-> System.out.println(e) );*/

        /*System.out.println( userService.queryByCount() );*/

        /*System.out.println( userService.queryById("fd85c25dae6c4022aac47c9e5e7acccc ") );*/


        //测试事务
        /*User user = new User();
        user.setId( UUID.randomUUID().toString().replace("-","") );
        user.setName("mezie");

        userService.create(user);*/


    }

}
