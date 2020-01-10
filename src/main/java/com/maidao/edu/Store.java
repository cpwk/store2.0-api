package com.maidao.edu;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Store {

    public static void main(String[] args) {
        SpringApplication.run(Store.class, args);
    }

    public static String[] getScanPackages() {
        return Store.class.getAnnotation(ComponentScan.class).value();
    }

    public static Reflections getAppReflection() {
        return new Reflections(new ConfigurationBuilder().forPackages(getScanPackages()));
    }
}
