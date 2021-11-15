package ru.lanit.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.lanit.repository.entity.AddressEntity;
import ru.lanit.repository.entity.PersonEntity;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:hibernate.cfg.xml"));
        localSessionFactoryBean.setAnnotatedClasses(AddressEntity.class, PersonEntity.class);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(getSessionFactory().getObject());
        return hibernateTransactionManager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/person_db");
        dataSource.setUsername("timur");
        dataSource.setPassword("");

        return dataSource;
    }
}
