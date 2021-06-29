package core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import libs.Configs;
import libs.CoreFunc;
import libs.GameText;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameCore implements CoreFunc {

    int playerX = 450;
    int playerY = 450;
    int shotX = 0;
    int shotY = 0;
    int firstShot = 0;
    int killCount = 0;
    int level = 1;
    int enemyNum = 5;
    int maxEnemy = 1;

    GameText text = new GameText(Color.WHITE,Color.WHITE);
    Player player = new Player();
    PlayerShot shotp = new PlayerShot();
    Enemy enemy[] = new Enemy[50];
    Background bg = new Background();
    int[] enemyX = new int[50];
    int[] enemyY = new int[50];
    int[] speed = new int[50];
    int[] deadState = new int[50];
    WinGame win = new WinGame();
    GameOver over = new GameOver();

    @Override
        public void init(GraphicsContext gc) {

            bg.resize(Configs.appWidth, Configs.appHeight);

            player.resize(0.15);

            shotp.resize(0.15);

            for (int i = 0; i < enemyNum; i++) {

                enemy[i] = new Enemy();
                enemy[i].resize(0.15);
                enemyX[i] = new Random().nextInt(960);
                enemyY[i] = new Random().nextInt(10);
                speed[i] = new Random().nextInt(5);
                deadState[i] = 0;

                if (speed[i] == 0) {
                    speed[i]++;
                }

            }

    }

    @Override
        public void animate(GraphicsContext gc, int time, ArrayList input) {

        bg.resize(Configs.appWidth, Configs.appHeight);
        bg.render(gc, 0, 0);

        text.setText(gc,"Score : " + killCount, 20, 10, 20);
        text.setText(gc,"Level : " + level, 20,10,20+20);

        player.render(gc, playerX, playerY);

        shotp.render(gc, shotX, shotY-=15);

        maxEnemy = enemyNum * level;

        for (int i = 0; i < maxEnemy; i++) {

            enemy[i].render(gc, enemyX[i], enemyY[i] += speed[i]);

            if (enemyY[i] >= 540 && deadState[i] == 0) {

                enemyX[i] = new Random().nextInt(960);
                enemyY[i] = new Random().nextInt(10);
                speed[i] = new Random().nextInt(5);

                if (speed[i] == 0) {
                    speed[i]++;
                }
            }

            if (player.collide(enemy[i])) {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 960, 540);
                over.render(gc, 340, 150);
                JOptionPane.showMessageDialog(null, "Game Over! Your Score: "+ killCount);
                System.exit(0);
            }

            if (shotp.collide(enemy[i])) {
                deadState[i] = 1;
                killCount++;
                enemyX[i] = 1000;
                enemyY[i] = 1000;
                speed[i] = 0;

                if (killCount == maxEnemy) {

                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, 960, 540);
                    win.render(gc, 280, 90);
                    JOptionPane.showMessageDialog(null, "Level complete! Your Score: "+ killCount);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.exit(0);
                }

            }
        }

        if (input.contains("UP")) {
            player.render(gc, playerX, playerY-=5);
        }

        if (input.contains("LEFT")) {
            player.render(gc, playerX-=5, playerY);
        }

        if (input.contains("RIGHT")) {
            player.render(gc, playerX+=5, playerY);
        }

        if (input.contains("DOWN")) {
            player.render(gc, playerX, playerY+=5);
        }

        if (shotY <= 50) {
            firstShot = 0;
        }

        if (firstShot == 0 && shotY <= 50) {

            if (input.contains("SPACE")) {
                shotX = playerX + 30;
                shotY = playerY + 10;
            }

            firstShot = 1;

        }

    }

    @Override
        public void mouseClick(MouseEvent e) {

        }

    @Override
        public void mouseMoved(MouseEvent e) {

        }

}