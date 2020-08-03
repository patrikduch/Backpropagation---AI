package com.patrikduch.school.neuro.perceptron.helpers.javafx.controls;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlHelper {

    public static List<Node> getNodes(List<Node>inputCollection ) {

        List<Node> buttons = new ArrayList<>();

        for(Node node : inputCollection) {


            if(node instanceof Button) {

                buttons.add(node);

            }

        }

        return buttons;
    }

}
