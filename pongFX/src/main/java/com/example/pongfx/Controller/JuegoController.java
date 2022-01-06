package com.example.pongfx.Controller;

import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.sql.Time;

public class JuegoController {

    // declaración componentes
    private Rectangle playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall;
    private Circle ball;
    private StackPane panel;
    private Label pointsPlayerOne, pointsPlayerTwo;
    private Timeline animation;

    // Constructor controlador
    public JuegoController(Rectangle playerOne, Rectangle playerTwo, Rectangle topWall, Rectangle bottomWall,
                           Rectangle leftWall, Rectangle rightWall, Circle ball, StackPane panel, Label pointsPlayerOne,
                           Label pointsPlayerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.topWall = topWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
        this.rightWall = rightWall;
        this.ball = ball;
        this.panel = panel;
        this.pointsPlayerOne = pointsPlayerOne;
        this.pointsPlayerTwo = pointsPlayerTwo;
    }
}
