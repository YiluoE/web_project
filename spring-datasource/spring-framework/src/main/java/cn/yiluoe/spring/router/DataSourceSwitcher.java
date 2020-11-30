package cn.yiluoe.spring.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @program: spring-datasource
 * @description 执行数据源的主备切换
 * @user: 本以罗伊斯
 * @create: 2020/11/26 18:59
 **/

@Component(value = "dataSourceSwitcher")/*可以不写*/
public class DataSourceSwitcher extends AbstractRoutingDataSource {

    /*主要是你和dataSourceSwitcher怎么简历起来关系的*/
    /*单例模式 static加不加都行吧。*/
    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    @Autowired
    public DataSourceSwitcher(DataSource dataSource,DataSource dataSourceAli){

        /*设置默认数据源*/
        super.setDefaultTargetDataSource(dataSource);

        /*封装所有的数据源(动态切换)*/
        super.setTargetDataSources(
                Map.of("dataSource",dataSource,"dataSourceAli",dataSourceAli)
        );

    }

    /*设置要使用哪个数据源*/
    public static void setDataSourceKey(String dataSource){
        dataSourceKey.set(dataSource);
    }

    /*获取(返回当前使用的数据源)*/
    /*set了这里才能get出去*/
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    /*清空恢复至默认数据源*/
    public static void clearDataSourceKey(){
        System.out.println(
                Thread.currentThread().getName()
        );

        dataSourceKey.remove();
    }
}
