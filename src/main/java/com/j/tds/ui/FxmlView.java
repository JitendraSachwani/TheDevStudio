package com.j.tds.ui;

import lombok.ToString;

/**
 *
 * @author empc012
 */
@ToString
public enum FxmlView {
    LANDING {
        @Override
        String getTitle() {
            return "The Dev Studio - @TAGLINE";
        }

        @Override
        String getFilePath() {
            return "../fxml/base.fxmls";
        }

    },
    CREATE_NEW_PROJECT {
        @Override
        String getTitle() {
            return "Create New Application";
        }

        @Override
        String getFilePath() {
            return "./fxml/main.fxml";
        }

    },;

    abstract String getTitle();

    abstract String getFilePath();

}
