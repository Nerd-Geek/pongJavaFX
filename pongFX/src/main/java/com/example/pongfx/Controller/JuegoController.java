package com.example.pongFX.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class JuegoController {
    // declaración componentes
    private Rectangle playerOne, playerTwo, topWall, bottomWall, leftWall, rightWall;
    private Circle ball;
    private StackPane panel;
    private Label pointsPlayerOne, pointsPlayerTwo;
    private Label labelWin;
    private Timeline animation;

    // funcionamiento del juego
    private boolean top;
    private boolean bottom;
    private boolean left;
    private boolean right;
    private boolean start;
    private double speedBall;
    private double speedPlayer;

    private int pointPlayerOne;
    private int pointPlayerTwo;

    // Sonidos
    private String pointSound;
    private String restartSound;
    private String soundtrack;
    private String playerSound;
    private String wallSound;
    private String winSound;
    private AudioInputStream bgMusic;
    private Clip music;

    // Constructor controlador
    public JuegoController(Rectangle playerOne, Rectangle playerTwo, Rectangle topWall, Rectangle bottomWall,
                           Rectangle leftWall, Rectangle rightWall, Circle ball, StackPane panel, Label pointsPlayerOne,
                           Label pointsPlayerTwo, Label labelWin) {
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
        this.labelWin = labelWin;

        this.top = false;
        this.bottom = true;
        this.left = false;
        this.right = true;
        this.start = true;
        this.speedBall = 2;
        this.speedPlayer = 8;

        this.pointPlayerOne = 0;
        this.pointPlayerTwo = 0;

        // Nota: solo permite formato wav
        this.pointSound = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "point.wav";
        this.restartSound = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "restart.wav";
        this.soundtrack = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "soundtrack.wav";
        this.playerSound = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "player.wav";
        this.wallSound = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "wall.wav";
        this.winSound = System.getProperty("user.dir") + File.separator +"src" + File.separator + "main" +
                File.separator +"resources" + File.separator + "sound" + File.separator + "win.wav";

        startGame();
        controls();
    }

    // Bucle del juego
    private void startGame() {
        this.animation = new Timeline(new KeyFrame(Duration.millis(17), t -> {
            try {
                win();
                detectCollision();
                moveBall();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        animation.setCycleCount(Animation.INDEFINITE);
    }

    // Controles del juego
    private void controls() {
        panel.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    playerOne.setTranslateY(playerOne.getTranslateY() - speedPlayer);
                    animation.play();
                    break;
                case S:
                    playerOne.setTranslateY(playerOne.getTranslateY() + speedPlayer);
                    animation.play();
                    break;
                case UP:
                    playerTwo.setTranslateY(playerTwo.getTranslateY() - speedPlayer);
                    animation.play();
                    break;
                case DOWN:
                    playerTwo.setTranslateY(playerTwo.getTranslateY() + speedPlayer);
                    animation.play();
                    break;
                case SPACE:
                    animation.play();
                    try {
                        bgMusic = AudioSystem.getAudioInputStream(new File(soundtrack).getAbsoluteFile());
                        music = AudioSystem.getClip();
                        music.open(bgMusic);
                        music.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case R:
                    pointsPlayerOne.setText("0");
                    pointPlayerOne = 0;
                    pointsPlayerTwo.setText("0");
                    pointPlayerTwo = 0;
                    labelWin.setText("");
                    playerOne.setTranslateY(0);
                    playerTwo.setTranslateY(0);
                    ball.setTranslateY(0);
                    ball.setTranslateX(0);
                    try {
                        bgMusic = AudioSystem.getAudioInputStream(new File(restartSound).getAbsoluteFile());
                        music = AudioSystem.getClip();
                        music.open(bgMusic);
                        music.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    break;
            }
        });
        panel.setFocusTraversable(true);
    }

    // Colisiones del juego
    private void detectCollision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (playerOne.getBoundsInParent().intersects(topWall.getBoundsInParent())) {
            playerOne.setTranslateY(playerOne.getTranslateY() + 4);
        }
        if (playerOne.getBoundsInParent().intersects(bottomWall.getBoundsInParent())) {
            playerOne.setTranslateY(playerOne.getTranslateY() - 4);
        }
        if (playerTwo.getBoundsInParent().intersects(topWall.getBoundsInParent())) {
            playerTwo.setTranslateY(playerTwo.getTranslateY() + 4);
        }
        if (playerTwo.getBoundsInParent().intersects(bottomWall.getBoundsInParent())) {
            playerTwo.setTranslateY(playerTwo.getTranslateY() - 4);
        }
        if (ball.getBoundsInParent().intersects(topWall.getBoundsInParent())) {
            top = false;
            bottom = true;
            colisionSound();
        }
        if (ball.getBoundsInParent().intersects(bottomWall.getBoundsInParent())) {
            top = true;
            bottom = false;
            colisionSound();
        }
        if (ball.getBoundsInParent().intersects(playerOne.getBoundsInParent())) {
            right = true;
            left = false;
            speedBall += 0.5;
            touchPlayuer();
        }
        if (ball.getBoundsInParent().intersects(playerTwo.getBoundsInParent())) {
            right = false;
            left = true;
            speedBall += 0.5;
            touchPlayuer();
        }
        if (ball.getBoundsInParent().intersects(rightWall.getBoundsInParent())) {
            score(0);
            speedBall = 2;
            ball.setTranslateX(0);
            ball.setTranslateY(0);
            animation.stop();
            point();
        }
        if (ball.getBoundsInParent().intersects(leftWall.getBoundsInParent())) {
            score(1);
            speedBall = 2;
            ball.setTranslateX(0);
            ball.setTranslateY(0);
            animation.stop();
            point();
        }
    }

    // Método para sumar puntos
    private void score(int player) {
        if (player == 0) {
            pointPlayerOne++;
            pointsPlayerOne.setText(pointPlayerOne + "");
        } else {
            pointPlayerTwo++;
            pointsPlayerTwo.setText(pointPlayerTwo + "");
        }
    }

    // Método para mover la pelota
    private void moveBall() {
        if (bottom && right) {
            ball.setTranslateX(ball.getTranslateX() + speedBall);
            ball.setTranslateY(ball.getTranslateY() + speedBall);
        } else if (bottom && left) {
            ball.setTranslateX(ball.getTranslateX() - speedBall);
            ball.setTranslateY(ball.getTranslateY() + speedBall);
        } else if (top && right) {
            ball.setTranslateX(ball.getTranslateX() + speedBall);
            ball.setTranslateY(ball.getTranslateY() - speedBall);
        } else if (top && left) {
            ball.setTranslateX(ball.getTranslateX() - speedBall);
            ball.setTranslateY(ball.getTranslateY() - speedBall);
        }
    }

    // Método para cuando gana un jugador
    private void win() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (pointPlayerOne == 10) {
            animation.stop();
            labelWin.setText("player one wins");
            playerOne.setTranslateY(0);
            playerTwo.setTranslateY(0);
            ball.setTranslateY(0);
            ball.setTranslateX(0);
            winSound();
        } else if (pointPlayerTwo == 10){
            animation.stop();
            labelWin.setText("player one wins");
            playerOne.setTranslateY(0);
            playerTwo.setTranslateY(0);
            ball.setTranslateY(0);
            ball.setTranslateX(0);
            winSound();
        }
    }

    private void colisionSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(wallSound).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void touchPlayuer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(playerSound).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void point() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pointSound).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    private void winSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(winSound).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}