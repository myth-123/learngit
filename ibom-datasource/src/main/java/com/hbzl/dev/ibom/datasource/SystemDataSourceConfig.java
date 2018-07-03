package com.hbzl.dev.ibom.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class SystemDataSourceConfig {
	@Bean(name="systemDataSource")
	@Primary
	public HikariDataSource hikariDataSource() {
		DatabasePoolConfig databasePoolConfig = new DatabasePoolConfig();
		HikariDataSource dataSource = databasePoolConfig.createDataSource("system.datasource.properties");
		return dataSource;
	}
}
