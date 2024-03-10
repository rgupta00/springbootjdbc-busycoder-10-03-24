package com.busycoder.productapp;

import com.busycoder.productapp.config.DbConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = {
//		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		JdbcTemplateAutoConfiguration.class
//})
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableConfigurationProperties({DbConn.class})
public class ProductappJdbctemplateApplication implements CommandLineRunner {

	//Autoconfiguration of the infra bean

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {
		if(dataSource!=null)
		System.out.println("ds is configured");

		if(jdbcTemplate!=null)
			System.out.println("jdbcTemplate is configured");


	}

	public static void main(String[] args) {
		SpringApplication.run(ProductappJdbctemplateApplication.class, args);
	}

}
