package com.example.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;

/**
 * Конфигурация
 */
@Configuration
public class JpaConfig {

    /**
     * Необязательно это бинить
     * @param entityManagerFactory
     * @return
     */
    @Bean
    @Primary
    public SessionFactory sessionFactoryBean(final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
