package com.dev.spring.config;

import com.dev.spring.model.User;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "com.dev.spring.dao",
        "com.dev.spring.service"
})
public class AppConfig {
    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setDriverClassName(environment.getProperty("driver"));
        dataSource.setUsername(environment.getProperty("user"));
        dataSource.setPassword(environment.getProperty("password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", environment.getProperty("show_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hbm2ddl.auto"));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }
}
