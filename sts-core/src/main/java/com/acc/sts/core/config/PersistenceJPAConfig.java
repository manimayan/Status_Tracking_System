package com.acc.sts.core.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:properties/persistence.properties")
public class PersistenceJPAConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${sts.environment}")
    private String environment;

    @Value("${jdbc.driver}")
    private String jdbcDriver;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.user}")
    private String jdbcUser;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${hibernate.generate.statistics}")
    private String hibernateStatistics;

    @Value("${hibernate.showsql:true}")
    private boolean hibernateShowsql;

    @Value("${sts.database.platform}")
    private String databasePlatform;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPersistenceUnitName("mysql");
        entityManager.setPackagesToScan("com.acc.sts.model");
        entityManager.setJpaVendorAdapter(vendorAdapter());
        entityManager.setJpaProperties(additionalProperties());
        return entityManager;
    }

    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(hibernateShowsql);
        adapter.setDatabasePlatform(databasePlatform);
        adapter.setDatabase(Database.MYSQL);
        return adapter;
    }

    public javax.sql.DataSource dataSource() {

        PoolProperties props = new PoolProperties();
        props.setUrl(jdbcUrl);
        props.setDriverClassName(jdbcDriver);
        props.setUsername(jdbcUser);
        props.setPassword(jdbcPassword);
        props.setJmxEnabled(true);
        props.setTestWhileIdle(false);
        props.setTestOnBorrow(true);
        props.setValidationQuery("SELECT 1");
        props.setValidationQueryTimeout(45);
        props.setTestOnReturn(true);
        props.setValidationInterval(60000);
        props.setTimeBetweenEvictionRunsMillis(30000);
        props.setMaxActive(20);
        props.setInitialSize(5);
        props.setMaxWait(60000);
        props.setRemoveAbandonedTimeout(180);
        props.setMinEvictableIdleTimeMillis(30000);
        props.setMinIdle(5);
        props.setLogAbandoned(true);
        props.setRemoveAbandoned(true);

        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(props);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.id.new_generator_mappings", "false");
        properties.setProperty("hibernate.generate_statistics", hibernateStatistics);
        properties.setProperty("hibernate.dialect", databasePlatform);
        return properties;
    }
}
