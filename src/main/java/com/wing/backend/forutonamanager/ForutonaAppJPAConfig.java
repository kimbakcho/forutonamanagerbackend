package com.wing.backend.forutonamanager;

import com.wing.backend.forutonamanager.Preference.CustomPreference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = "com.wing.backend.forutonamanager.forutona",
        entityManagerFactoryRef = "forutonaAppEntityManagerFactory",
        transactionManagerRef = "forutonaAppTransactionManager"
)
public class ForutonaAppJPAConfig {
    final CustomPreference customPreference;

    @Bean
    public DataSource forutonaAppDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername(customPreference.getForutonaAppDataSourceUserName());
        dataSource.setPassword(customPreference.getForutonaAppDataSourcePassword());
        dataSource.setUrl(customPreference.getForutonaAppDataSourceUrl());
        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean forutonaAppEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(forutonaAppDataSource());
        em.setPackagesToScan(new String[]{"com.wing.backend.forutonamanager.forutona"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(customPreference.getForutonaAppJpaProperties());
        em.setPersistenceUnitName("forutonaApp");
        return em;
    }

    @Bean
    public PlatformTransactionManager forutonaAppTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(forutonaAppEntityManagerFactory().getObject());
        return transactionManager;
    }

}
