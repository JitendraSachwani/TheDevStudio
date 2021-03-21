/*
 *  LICENSE HERE
 */
package com.j.tds.ui;

import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author <a href="mailto:jitendra.s@emeasurematics.com">Jitendra Sachwani</a>
 */
@Getter
public class SwitchSceneEvent extends ApplicationEvent {

    private final Stage stage;
    private final FxmlView fxmlView;

    public SwitchSceneEvent(Object source, Stage stage, FxmlView fxmlView) {
        super(source);
        this.stage = stage;
        this.fxmlView = fxmlView;
    }

}
