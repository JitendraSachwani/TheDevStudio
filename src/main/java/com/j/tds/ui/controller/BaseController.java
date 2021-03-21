/*
 *  LICENSE HERE
 */
package com.j.tds.ui.controller;

import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

/**
 *
 * @author <a href="mailto:jitendra.s@emeasurematics.com">Jitendra Sachwani</a>
 */
@Component
public class BaseController {

//    @FXML
//    private StateInfoCtlr stateInfoController;

    public BaseController() {
        System.out.println("");
    }

    @FXML
    public void initialize() {
        System.out.println("");
    }
}
