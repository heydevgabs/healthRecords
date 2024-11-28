package org.system.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

@EntityScan(basePackages = "org.system.health.record.model")
public class HealthApplication {
    public static void main( String[] args ) {
        SpringApplication.run( HealthApplication.class, args );
    }
}
