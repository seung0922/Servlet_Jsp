package org.zerock.util;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum CPUtil {
	INSTANCE;
	
	private DataSource ds;
	
	public DataSource getDS() {
		return ds;
	}
	
	CPUtil() {
		
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@192.168.25.38:1521:XE");
        config.setUsername("c##student");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(5000);
        config.getValidationTimeout();
//        config.setMaximumPoolSize(150);

        ds = new HikariDataSource(config);
		
	}
}