package com.example.project_for_university.utils;

import javafx.scene.Node;

public class NodeUtils {
    public static final void displayNode(Node node, boolean isDisplay) {
        node.setVisible(isDisplay);
        node.setManaged(isDisplay);
    }
}
