package com.kratos.demo;

import com.kratos.common.ExtendedJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.kratos"})
@EnableJpaRepositories(repositoryBaseClass = ExtendedJpaRepository.class, basePackages = {"com.kratos"})
@ServletComponentScan
@EntityScan({"com.kratos"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
