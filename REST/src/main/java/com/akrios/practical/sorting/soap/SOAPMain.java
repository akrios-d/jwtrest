package com.akrios.practical.sorting.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// For this rest application database won't be needed
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SOAPMain {

    public static void main(String[] args) {
        SpringApplication.run(SOAPMain.class, args);
    }
}



