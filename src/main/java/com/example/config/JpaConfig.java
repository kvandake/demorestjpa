package com.example.config;

import com.example.DemoRestJpaApplication;
import com.example.entity.MessageEntity;
import com.example.entity.UserEntity;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Конфигурация
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = DemoRestJpaApplication.class)
public class JpaConfig implements TransactionManagementConfigurer {


    @Bean(destroyMethod = "close")
    @Primary
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig("/datasource.properties");
        return new HikariDataSource(config);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(final EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource())
                .packages(
                        UserEntity.class,
                        MessageEntity.class)
                .persistenceUnit("persistenceUnit")
                .build();
    }


    @Bean
    @Primary
    public SessionFactory sessionFactoryBean(final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean(name = "transactionManager")
    @Primary
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }


}
