/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jadedtdt.artificer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.jadedtdt.artificer")
public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
