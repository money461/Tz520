package com.tz.druid;

import com.alibaba.druid.pool.DruidDataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源链接配置
 * @author menglin
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.tz.mapper")
public class DruidAutoConfiguration implements EnvironmentAware {
	
	private RelaxedPropertyResolver propertyResolver; 
	
	@Override
	public void setEnvironment(Environment env) {
		// TODO Auto-generated method stub
		this.propertyResolver = new RelaxedPropertyResolver(env,"druid.");
	}
    @Bean
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        int  initialSize  =  Integer.parseInt(propertyResolver.getProperty("initialSize"));
        int  minIdle  =  Integer.parseInt(propertyResolver.getProperty("minIdle"));
        int  maxActive  =  Integer.parseInt(propertyResolver.getProperty("maxActive"));
        boolean testOnBorrow = Boolean.parseBoolean(propertyResolver.getProperty("testOnBorrow"));
        if (initialSize > 0) {
            dataSource.setInitialSize(initialSize);
        }
        if (minIdle > 0) {
            dataSource.setMinIdle(minIdle);
        }
        if (maxActive > 0) {
            dataSource.setMaxActive(maxActive);
        }
        dataSource.setTestOnBorrow(testOnBorrow);
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
