package com.management.forutona;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class DefaultConfiguration {
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    DriverManagerDataSource datasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mysql://forutonadb.thkomeet.com:3306/forutona?useSSL=true&amp;verifyServerCertificate=false");
        dataSource.setUsername("neoforutona");
        dataSource.setPassword("neoforutona1020");
        return dataSource;
    }

    @Bean
    SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource());
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:Mappers/**/*.xml"));
        return sqlSessionFactory;
    }



}
