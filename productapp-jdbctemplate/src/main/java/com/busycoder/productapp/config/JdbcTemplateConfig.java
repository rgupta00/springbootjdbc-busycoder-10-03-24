package com.busycoder.productapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.busycoder"})
@EnableTransactionManagement
public class JdbcTemplateConfig {

    @Autowired
    private DbConn dbConn;

    //hikari conn pool in pace of DriverManagerDataSource
    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(dbConn.getDriverClassName());
        dataSource.setUrl(dbConn.getUrl());
        dataSource.setUsername(dbConn.getUsername());
        dataSource.setPassword(dbConn.getPassword());
        return dataSource;
    }
    //define the beans for jdbctemplate
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource){
//        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
//        return jdbcTemplate;
//    }
//    //tx mgr
//    @Primary
//    @Bean
//    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
//        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager(dataSource);
//        return transactionManager;
//    }
    @Primary
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager(driverManagerDataSource());
        return transactionManager;
    }
}
