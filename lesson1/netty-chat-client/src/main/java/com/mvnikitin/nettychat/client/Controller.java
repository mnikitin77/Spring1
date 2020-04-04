package com.mvnikitin.nettychat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Network network;

    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        network = new Network((args) -> {
            textArea.appendText((String)args[0]);
            textArea.appendText("\n");
        });
    }

    public void sendMessage(ActionEvent actionEvent) {
        network.sendMessage(msgField.getText());
        msgField.clear();
        msgField.requestFocus();
    }

    public void exit() {
        network.close();
        Platform.exit();
    }
}
