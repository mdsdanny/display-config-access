package com.mdsdanny.display.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication(scanBasePackages = "com.mdsdanny.display")
@PropertySource("classpath:custom.properties")
class InitApp implements CommandLineRunner {

    @Autowired
    private Environment env;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(InitApp.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}