package com.hbzl.dev.ibom.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class UserDataSourceConfig {
	@Bean(name="userDataSource")
	public HikariDataSource hikariDataSource() {
		DatabasePoolConfig databasePoolConfig = new DatabasePoolConfig();
		HikariDataSource dataSource = databasePoolConfig.createDataSource("user.datasource.properties");
		return dataSource;
	}
}
