package com.mvnikitin.nettychat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ChatClientConfig.class);
        FXMLLoader loader = context.getBean("fxmlLoader", FXMLLoader.class);
        Network network = context.getBean("network", Network.class);

        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setupNetwork(network);
        primaryStage.setOnCloseRequest(event -> controller.exit());
        primaryStage.setTitle("Netty Chat Client");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
