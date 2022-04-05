package com.example.Assignment2Bun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Assignment2BunApplication {

	public static void main(String[] args) {
	    SpringApplication.run(Assignment2BunApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE")
                        .allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }


}
