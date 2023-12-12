package com.pjlsoftware;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        SpringBackEndApplication.class,
})
public class SpringBackEndApplication {
    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBackEndApplication.class, args);
    }
}
