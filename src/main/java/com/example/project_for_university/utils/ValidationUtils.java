package com.example.project_for_university.utils;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final String doubleRegex = "[0-9]{1,13}(\\.[0-9]{0,10})?";
    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    public static final DecimalFormat df = new DecimalFormat("#.##########", dfs);

    public static final UnaryOperator<TextFormatter.Change> doubleFilter = change -> {
        String fullString = change.getControlText() + change.getText();

        if (change.isReplaced()) {
           if (!fullString.matches(doubleRegex)) {
               String pastedStr = change.getText();

               Pattern pattern = Pattern.compile(doubleRegex);
               Matcher matcher = pattern.matcher(pastedStr);

               if (matcher.find()) {
                   String matchedString = matcher.group(0);
                   change.setText(matchedString);
               } else {
                   return null;
               }
           }
       } else if (change.isAdded()) {
            if (!fullString.matches(doubleRegex)) {
                change.setText("");
                return null;
            }
        }

        return change;
    };

    public static final UnaryOperator<TextFormatter.Change> integerFilter = change -> change.getText().matches("\\d*") ? change : null;

    public static boolean isValid(String num) {
        return num.matches("^[0-9]*[.]?[0-9]+(?:[eE][-+]?[0-9]+)?$");
    }
}