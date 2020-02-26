package com.example.javaConsoleApp;

import com.example.javaConsoleApp.config.ActivemqProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableConfigurationProperties(ActivemqProperties.class)
@EnableScheduling
@EnableJms
public class JavaConsoleApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaConsoleApplication.class, args);
	}
}
