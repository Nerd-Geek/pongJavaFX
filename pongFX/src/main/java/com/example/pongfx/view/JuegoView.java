package com.example.pongfx.view;

import com.example.pongfx.Controller.JuegoController;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class JuegoView extends BorderPane {

    private Rectangle playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall;
    private javafx.scene.shape.Rectangle tanque;
    private Circle ball;
    private StackPane panel;
    private JuegoController controller;
    private Label pointsPlayerOne, pointsPlayerTwo;

    public void JuegoView() {
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
        this.controller = new JuegoController(playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall, ball,
                panel, pointsPlayerOne, pointsPlayerTwo);

        // Propiedades jugadores
        playerOne.heightProperty().bind(panel.heightProperty().divide(20));
        playerOne.setWidth(12);
        playerOne.setFill(Color.CYAN);
        playerTwo.heightProperty().bind(panel.heightProperty().divide(20));
        playerTwo.setWidth(12);
        playerOne.setFill(Color.CYAN);

        // Propiedades pared
        topWall.heightProperty().bind(panel.heightProperty());
        topWall.widthProperty().bind(panel.widthProperty().divide(20));
        topWall.setFill(Color.CORAL);

        bottomWall.heightProperty().bind(panel.heightProperty());
        bottomWall.widthProperty().bind(panel.widthProperty().divide(20));
        bottomWall.setFill(Color.CORAL);

        leftWall.heightProperty().bind(panel.heightProperty());
        leftWall.widthProperty().bind(panel.widthProperty().divide(20));
        leftWall.setFill(Color.CORAL);

        rightWall.heightProperty().bind(panel.heightProperty());
        rightWall.widthProperty().bind(panel.widthProperty().divide(20));
        rightWall.setFill(Color.CORAL);

        // propiedades ball
        ball.setRadius(8);
        ball.setFill(Color.BLACK);

        // propiedades puntos
        pointsPlayerOne.setTextFill(Color.WHITE);
        pointsPlayerTwo.setTextFill(Color.WHITE);

        // propiedades panel
        panel.getChildren().addAll(playerOne,playerTwo,ball,topWall,bottomWall,leftWall,rightWall);
        // posicionamiento de elementos
        panel.setAlignment(playerOne,Pos.CENTER);
        panel.setAlignment(playerTwo,Pos.CENTER);
        panel.setAlignment(ball,Pos.CENTER);
        panel.setAlignment(topWall, Pos.TOP_CENTER);
        panel.setAlignment(bottomWall, Pos.BOTTOM_CENTER);
        panel.setAlignment(leftWall, Pos.CENTER_LEFT);
        panel.setAlignment(rightWall, Pos.CENTER_RIGHT);

        this.setCenter(panel);
    }
}
