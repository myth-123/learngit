package com.hbzl.dev.ibom.system.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;
/**
 * 
    * @ClassName: MybatisConfig  
    * @Description: 
    * 这个类为配置数据源的类，如果模块需要多个数据源，需要配置多个类
    * 该数据源名称为"userDataSource"（DataSourceConfig类中注入的数据源名称），
    * 执行mybatis的接口文件为"com.hbzl.dev.ibom.user.mapper"下的文件
    * 扫描的xml的SQL语句文件为"classpath:sqlmap/*.xml"
    * 注意：配置数据源时，数据源名称(本例为"userDataSource"，DataSourceConfig类中注入的数据源名称)，
    * basePackages = {"com.hbzl.dev.ibom.user.mapper"}, sqlSessionFactoryRef = "userSqlSessionFactory"
    * 不可重复。也就是说不同的数据源要执行的接口文件需要放在不同的路径下
    * basePackages,sqlSessionFactoryRef均不可重复
    * 执行sql语句文件路径可重复
    * 可以查看TestMybatisCofig类中代码示例
    * @author zhousw  
    * @date 2018年6月12日  
    *
 */
@Configuration("systemMybatisConfig")
@MapperScan(basePackages = {"com.hbzl.dev.ibom.system.mapper"}, sqlSessionFactoryRef = "systemSqlSessionFactory")
public class SystemMybatisConfig {
	
	@Autowired
	@Qualifier("systemDataSource")
	private HikariDataSource systemDataSource;
	
	
	static final String MAPPERXMLPATH="classpath:systemsql/*.xml";  
	
	@Bean
	@Primary
	public SqlSessionFactory systemSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(systemDataSource); // 使用userDataSource数据源, 连接user库
        //添加XML目录  
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
        //*.mapper.xml的地址
        factoryBean.setMapperLocations(resolver.getResources(MAPPERXMLPATH));  
        return factoryBean.getObject();
    }
	
	@Bean
	@Primary
	public DataSourceTransactionManager systemTransationManager(@Qualifier("systemDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
    @Bean
    @Primary
    public SqlSessionTemplate systemSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(systemSqlSessionFactory()); // 使用上面配置的Factory
        return template;
    }
    
	
}