/*
package com.sula.ysn.config;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJdbcRepositories
@EnableJpaRepositories(entityManagerFactoryRef = "jdbcDSEntityManagerFactory",
        transactionManagerRef = "jdbcDSTransactionManager",
        basePackages = {"com.sula.ysn.dao"})
public class JdbcDSConfig {
    @Primary
    @Bean
    public DataSourceProperties jdbcDSDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource jdbcDSDataSource(@Qualifier("jdbcDSDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean jdbcDSEntityManagerFactory(@Qualifier("jdbcDSDataSource") DataSource hubDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(hubDataSource).packages("com.sula.ysn.model")
                .persistenceUnit("jdbcDS").build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager jdbcDSTransactionManager(@Qualifier("jdbcDSEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
*/
