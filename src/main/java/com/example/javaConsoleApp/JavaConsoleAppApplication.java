package com.example.javaConsoleApp;

import com.example.javaConsoleApp.config.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableConfigurationProperties(Properties.class)
@EnableScheduling
@EnableJms
public class JavaConsoleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaConsoleAppApplication.class, args);
	}
}
