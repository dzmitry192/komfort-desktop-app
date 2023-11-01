package com.example.project_for_university.utils;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public class ValidationUtils {
    public static final UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
        if (change.isReplaced()) {
            if (change.getText().matches("[^0-9]")) {
                change.setText(change.getControlText().substring(change.getRangeStart(), change.getRangeEnd()));
            }
        }
        if (change.isAdded()) {
            if (change.getControlText().contains(".")) {
                if (change.getText().matches("[^0-9]")) {
                    change.setText("");
                }
            } else if (change.getText().matches("[^0-9.]")) {
                change.setText("");
            }
        }
        return change;
    };

    public static final UnaryOperator<TextFormatter.Change> integerFilter = change -> change.getText().matches("\\d*") ? change : null;
    public static final boolean waterproofDoubleFilter(ObservableValue observable, Boolean oldValue, Boolean newValue) {
        return !newValue;
    }
}