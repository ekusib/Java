package jp.ne.sakura.sync.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="jp.ne.sakura.sync.web")
public class DataSourceConfiguration {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource
			dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setFailFast(true);
        return sessionFactory.getObject();
	}
}
