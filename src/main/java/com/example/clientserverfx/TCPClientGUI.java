package com.example.clientserverfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TCPClientGUI extends Application {
    private TextField messageField;
    private TextArea serverResponseArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TCP Client");

        messageField = new TextField();
        messageField.setPromptText("Enter a message or 'exit' to quit");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        serverResponseArea = new TextArea();
        serverResponseArea.setEditable(false);
        serverResponseArea.setWrapText(true);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(messageField, sendButton, serverResponseArea);

        Scene scene = new Scene(vBox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageField.getText();
        // Place your code here to send the message to the server and display the response
        // You can use the methods from the TCPClient class to handle communication with the server
        // Update serverResponseArea with the response received from the server
    }
}
