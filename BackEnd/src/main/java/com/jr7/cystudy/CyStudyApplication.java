package com.jr7.cystudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jr7"})
@EnableJpaRepositories("com.jr7.cystudy.repository")
public class CyStudyApplication {
  public static void main(String[] args) {
    SpringApplication.run(CyStudyApplication.class, args);
  }
}
