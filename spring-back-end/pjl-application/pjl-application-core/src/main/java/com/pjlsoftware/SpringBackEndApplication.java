package com.pjlsoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        SpringBackEndApplication.class,
})
public class SpringBackEndApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBackEndApplication.class, args);
    }
}
