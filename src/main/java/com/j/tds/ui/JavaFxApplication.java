/*
 *  LICENSE HERE
 */
package com.j.tds.ui;

import com.j.tds.SBootApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author <a href="mailto:jitendra.s@emeasurematics.com">Jitendra Sachwani</a>
 */
public class JavaFxApplication extends Application {

    private static final FxmlView INITIAL_VIEW = FxmlView.LANDING;
    
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(SBootApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.applicationContext.publishEvent(new SwitchSceneEvent(this, stage, INITIAL_VIEW));
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
