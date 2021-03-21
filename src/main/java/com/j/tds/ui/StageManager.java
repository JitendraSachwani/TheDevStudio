package com.j.tds.ui;

import com.j.tds.ui.controller.BaseController;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author empc012
 */
@Component
public class StageManager implements ApplicationListener<SwitchSceneEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StageManager.class);

    @Autowired
    private FxWeaver fxWeaver;
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    private final boolean forceLegacy = false;

    @Override
    public void onApplicationEvent(SwitchSceneEvent e) {
        switchScene(e.getStage(), e.getFxmlView());
    }

    public void switchScene(Stage primaryStage, FxmlView view) {
        Parent rootNode;
        if (forceLegacy) {
            rootNode = loadRootLegacy(view);
        } else {
            rootNode = loadRoot(view);
        }

        show(primaryStage, rootNode, view.getTitle());
    }

    private Parent loadRoot(FxmlView view) {
        Parent rootNode = fxWeaver.loadView(BaseController.class, view.getFilePath());
        Objects.requireNonNull(rootNode, "A Root FXML node must not be null. "
                + "Please check the File Path for " + view);

        return rootNode;
    }

    @Deprecated
    private Parent loadRootLegacy(FxmlView view) {
        URL fxmlFileUri = getClass().getResource(view.getFilePath());
        Objects.requireNonNull(fxmlFileUri,
                "Couldnt FXML at the specified File Path. "
                + "Please check the File Path for " + view);

        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        loader.setLocation(fxmlFileUri);
        try {
            return loader.load();
        } catch (IOException ex) {
            LOGGER.error("INIT: Error Loading the FxmlView({}). \n Error: {}", view, ex);
            return null;
        }
    }

    private void show(Stage primaryStage, Parent rootnode, String title) {
        Scene scene = primaryStage.getScene();
        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();

        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit("Unable to show scene for title" + title, exception);
        }
    }

    private void logAndExit(String errorMsg, Exception e) {
        e.printStackTrace();
        LOGGER.error(errorMsg, e, e.getCause());
        this.applicationContext.close();
        Platform.exit();
    }

}
