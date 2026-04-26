package com.scm;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class ScmApplication {

//    @Value("${spring.datasource.password}")
//    private String dbPassword;

    public static void main(String[] args) {
        SpringApplication.run(ScmApplication.class, args);
    }
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        // Ensure the URL matches your Docker setup
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/scm_db");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("postgres"); // Ensure this matches exactly
//        return dataSource;
//    }
//    @PostConstruct
//    public void checkConfig() {
//        System.out.println("DEBUG_LOG: Password being used is: " + dbPassword);
//    }
}
