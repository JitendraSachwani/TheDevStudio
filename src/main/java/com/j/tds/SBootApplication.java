/*
 *  LICENSE HERE
 */
package com.j.tds;

import com.j.tds.ui.JavaFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author <a href="mailto:jitendra.s@emeasurematics.com">Jitendra Sachwani</a>
 */
@SpringBootApplication
public class SBootApplication {

    public static void main(String[] args) {
        // This is how normal Spring Boot app would be launched
        // SpringApplication.run(SBootApplication.class, args);

        // This is how we can launch a JavaFx based app inside the spring app container
        Application.launch(JavaFxApplication.class, args);
    }
}
