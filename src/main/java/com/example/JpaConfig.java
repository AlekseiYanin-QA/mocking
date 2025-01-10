package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example"); // пакет с сущностями

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Дополнительные настройки JPA
        em.setJpaProperties(additionalProperties());

        // Установка интерфейса EntityManagerFactory
        em.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Автоматическое обновление схемы базы данных
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect"); // Диалект для H2
        properties.setProperty("hibernate.show_sql", "true"); // Включить вывод SQL-запросов в консоль (опционально)
        properties.setProperty("hibernate.format_sql", "true"); // Форматирование SQL-запросов (опционально)
        return properties;
    }
}