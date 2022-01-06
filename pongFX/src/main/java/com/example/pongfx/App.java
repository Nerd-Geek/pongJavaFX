package com.example.pongfx;

import com.example.pongfx.view.JuegoView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new JuegoView(), 600, 600);
        stage.setTitle("Juego Pong JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}