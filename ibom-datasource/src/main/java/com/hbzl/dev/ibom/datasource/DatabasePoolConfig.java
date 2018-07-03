package com.hbzl.dev.ibom.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePoolConfig {
	

	public HikariDataSource createDataSource(String properFileName) {

		String FILE_PATH_FREFIX = "/";
		HikariConfig config = new HikariConfig(FILE_PATH_FREFIX+properFileName);
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}


}
