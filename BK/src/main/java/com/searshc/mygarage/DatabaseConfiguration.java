package com.searshc.mygarage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

@Configuration
@EnableAutoConfiguration
@PropertySource(value = {"file:${spring.config.location}datasource-${spring.profiles.active}.properties"})
public class DatabaseConfiguration {

    private static final Log log = LogFactory.getLog(DatabaseConfiguration.class);

    @Inject
    private Environment environment;

    private static DataSource dataSource;

    @Bean
    public DataSource getDataSource() {
        if (this.environment == null) {
            throw new IllegalArgumentException("The Environment cannot be null");
        }

        log.info("############## >>>>> Active Profile: " + this.environment.getProperty("spring.profiles.active"));

        if (dataSource == null) {
            String url = this.environment.getProperty("jdbc.url");
            String username = this.environment.getProperty("jdbc.username");
            String password = this.environment.getProperty("jdbc.password");

            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            ds.setAutoClosePStmtStreams(true);
            ds.setAutoClosePStmtStreams(true);
            ds.setCacheCallableStatements(false);
            ds.setUseLocalSessionState(false);
            ds.setCacheServerConfiguration(false);

            DatabaseConfiguration.dataSource = ds;
            log.info("Database Connection: " + username + "@" + url);
        }
        return DatabaseConfiguration.dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        if (this.environment == null) {
            throw new IllegalArgumentException("The Environment cannot be null");
        }

        String location = this.environment.getProperty("spring.config.location");
        String profile = this.environment.getProperty("spring.profiles.active");

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(this.getDataSource());
        em.setPackagesToScan(new String[]{"com.searshc.mygarage.entities"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties hibernate = new Properties();
        try {
            hibernate.load(new FileInputStream(location + "hibernate-" + profile + ".properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        em.setJpaProperties(hibernate);

        return em;
    }

}
