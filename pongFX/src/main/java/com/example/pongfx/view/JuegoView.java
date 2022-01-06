package com.example.pongFX.View;

import com.example.pongFX.Controller.JuegoController;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class JuegoView extends BorderPane {

    private Rectangle playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall;
    private Circle ball;
    private StackPane panel;
    private JuegoController controller;
    private Label pointsPlayerOne, pointsPlayerTwo, labelWin;

    public JuegoView() {
        init();
    }

    private void init() {
        // Inicializamos componentes
        this.playerOne = new Rectangle();
        this.playerTwo = new Rectangle();
        this.topWall = new Rectangle();
        this.bottomWall = new Rectangle();
        this.leftWall = new Rectangle();
        this.rightWall = new Rectangle();
        this.ball = new Circle();
        this.panel = new StackPane();
        this.pointsPlayerOne = new Label("0");
        this.pointsPlayerTwo = new Label("0");
        this.labelWin = new Label();
        this.controller = new JuegoController(playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall, ball,
                panel, pointsPlayerOne, pointsPlayerTwo, labelWin);

        // Propiedades jugadores
        playerOne.heightProperty().bind(panel.heightProperty().divide(10));
        playerOne.setWidth(12);
        playerOne.setFill(Color.WHITE);
        playerTwo.heightProperty().bind(panel.heightProperty().divide(10));
        playerTwo.setWidth(12);
        playerTwo.setFill(Color.WHITE);

        // Propiedades pared
        topWall.heightProperty().bind(panel.heightProperty().divide(20));
        topWall.widthProperty().bind(panel.widthProperty());
        topWall.setFill(Color.BLACK);

        bottomWall.heightProperty().bind(panel.heightProperty().divide(20));
        bottomWall.widthProperty().bind(panel.widthProperty());
        bottomWall.setFill(Color.BLACK);

        leftWall.heightProperty().bind(panel.heightProperty());
        leftWall.widthProperty().bind(panel.widthProperty().divide(500));
        leftWall.setFill(Color.BLACK);

        rightWall.heightProperty().bind(panel.heightProperty());
        rightWall.widthProperty().bind(panel.widthProperty().divide(500));
        rightWall.setFill(Color.BLACK);

        // propiedades ball
        ball.setRadius(8);
        ball.setFill(Color.WHITE);

        panel.setStyle("-fx-background-color: #000000");

        // propiedades puntos
        pointsPlayerOne.setTextFill(Color.WHITE);
        pointsPlayerTwo.setTextFill(Color.WHITE);

        labelWin.setFont(Font.font("Serif",80));
        labelWin.setTextFill(Color.RED);

        // propiedades panel
        panel.getChildren().addAll(playerOne,playerTwo,ball,topWall,bottomWall,leftWall,rightWall, pointsPlayerOne, pointsPlayerTwo, labelWin);
        // posicionamiento de elementos
        panel.setAlignment(playerOne,Pos.CENTER_LEFT);
        panel.setAlignment(playerTwo,Pos.CENTER_RIGHT);
        panel.setAlignment(ball,Pos.CENTER);
        panel.setAlignment(topWall, Pos.TOP_CENTER);
        panel.setAlignment(pointsPlayerOne, Pos.TOP_LEFT);
        panel.setAlignment(pointsPlayerTwo, Pos.TOP_RIGHT);
        panel.setAlignment(bottomWall, Pos.BOTTOM_CENTER);
        panel.setAlignment(leftWall, Pos.CENTER_LEFT);
        panel.setAlignment(rightWall, Pos.CENTER_RIGHT);

        this.setCenter(panel);
    }
}
