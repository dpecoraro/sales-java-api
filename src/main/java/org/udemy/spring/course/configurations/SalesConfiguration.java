package org.udemy.spring.course.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.udemy.spring.course.repositories.ClientDAO;

@DevelopmentConfiguration
public class SalesConfiguration {

    @Bean
    public CommandLineRunner exec(@Autowired ClientDAO dao) {
        return args -> {
            System.out.println("Running on development Mode");
        };
    }
}
