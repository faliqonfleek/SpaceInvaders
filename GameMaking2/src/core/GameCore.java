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

public class GameCore implements CoreFunc {

    // Main Game variables should be here
    int playerX = 450;
    int playerY = 450;
    int enemyX = new Random().nextInt(960);
    int enemyY = new Random().nextInt(10);
    int enemy2X = new Random().nextInt(960);
    int enemy2Y = new Random().nextInt(10);
    int shotX = enemyX + 15;
    int shotY = enemyY + 10;
    int shot2X = enemy2X + 15;
    int shot2Y = enemy2Y + 10;
    int random = 0;
    int shotpX = 0;
    int shotpY = 0;
    int count = 0;
    int kill = 0;
    int kill2 = 0;
    int count2 = 0;
    int exit = 0;
    int winning = 0;
    int losing = 0;

    Player player = new Player();
    Enemy enemy = new Enemy();
    Enemy2 enemy2 = new Enemy2();
    Shot shot = new Shot();
    Shot shot2 = new Shot();
    PlayerShot shotp = new PlayerShot();
    Explosion explosion = new Explosion();
    Explosion explosion2 = new Explosion();
    Clear clear = new Clear();
    GameOver over = new GameOver();
    WinGame win = new WinGame();

@Override
    public void init(GraphicsContext gc) {
        // initialize objects (initial position, initial size, etc)

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 960, 540);

        player.resize(0.15);
        player.render(gc, playerX, playerY);

        enemy.resize(0.15);
        enemy.render(gc, enemyX, enemyY);

        enemy2.resize(0.15);
        enemy2.render(gc, enemyX, enemyY);

        shot.resize(0.15);
        shot.render(gc, shotX, shotY);

        shot2.resize(0.15);
        shot2.render(gc, shot2X, shot2Y);

        shotp.resize(0.15);
        shotp.render(gc, shotpX, shotpY);

        explosion.resize(0.15);
        explosion.render(gc, 0, 0);

        explosion2.resize(0.15);
        explosion2.render(gc, 0, 0);

        clear.resize(0.15);
        clear.render(gc, 0, 0);
    }

    @Override
    public void animate(GraphicsContext gc, int time, ArrayList input) {
        // any animations and keyboard controls should be here

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 960, 540);

        if (kill == 1 && count == 1) {
            enemyX = 1000;
            enemyY = 1000;
            count = 2;
        }

        if (kill2 == 1 && count2 == 1) {
            enemy2X = 1000;
            enemy2Y = 1000;
            count2 = 2;
        }

        if (enemyY >= 540) {
            if (count == 1) {
                enemy2X = new Random().nextInt(960);
                enemy2Y = new Random().nextInt(10);
            }

            else if (count2 == 1) {
                enemyX = new Random().nextInt(960);
                enemyY = new Random().nextInt(10);
            }

            else {
                enemyX = new Random().nextInt(960);
                enemyY = new Random().nextInt(10);
                enemy2X = new Random().nextInt(960);
                enemy2Y = new Random().nextInt(10);
            }
        }

        random = new Random().nextInt(10);

        if (shotY >= 540 && random > 8) {
            if (count == 1) {
                shotX = 1000;
                shotY = 1000;
                shot2X = enemy2X + 15;
                shot2Y = enemy2Y + 10;
            }

            else if (count2 == 1) {
                shotX = enemyX + 15;
                shotY = enemyY + 10;
                shot2X = 1000;
                shot2Y = 1000;
            }

            else {
                shotX = enemyX + 15;
                shotY = enemyY + 10;
                shot2X = enemy2X + 15;
                shot2Y = enemy2Y + 10;
            }
        }

        shot.render(gc, shotX, shotY+=3);
        shot2.render(gc, shot2X, shot2Y+=3);

        player.render(gc, playerX, playerY);

        enemy.render(gc, enemyX, enemyY+=1);
        enemy2.render(gc, enemy2X, enemy2Y+=1);

        if (input.contains("UP")) {
            player.render(gc, playerX, playerY-=5);
        }
        if(input.contains("LEFT")) {
            player.render(gc, playerX-=5, playerY);
        }
        if(input.contains("RIGHT")) {
            player.render(gc, playerX+=5, playerY);
        }
        if(input.contains("DOWN")) {
            player.render(gc, playerX, playerY+=5);
        }

        if(input.contains("SPACE") && shotpY < 0) {
            shotpX = playerX + 30;
            shotpY = playerY + 10;
        }

        shotp.render(gc, shotpX, shotpY-=7);

        if (player.collide(enemy)) {
            System.out.println("Dead by crashing enemy 1");
            losing = 1;
        }

        if (player.collide(enemy2)) {
            System.out.println("Dead by crashing enemy 2");
            losing = 1;
        }

        if (shot.collide(player)) {
            System.out.println("Dead by enemy shot 1");
            losing = 1;
        }

        if (shot2.collide(player)) {
            System.out.println("Dead by enemy shot 2");
            losing = 1;
        }

        if (shotp.collide(enemy)) {
            kill = 1;
            count = 1;
            System.out.println("Enemy 1 killed");
            explosion.render(gc, enemyX, enemyY);
            enemy.changeImage("/core/black.png");
            shot.changeImage("/core/black.png");
        }

        if (shotp.collide(enemy2)) {
            kill2 = 1;
            count2 = 1;
            System.out.println("Enemy 2 killed");
            explosion2.render(gc, enemy2X, enemy2Y);
            enemy2.changeImage("/core/black.png");
            shot2.changeImage("/core/black.png");
        }

        if (kill == 1 && kill2 == 1) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 960, 540);
            win.render(gc,280,90);
            winning = 1;
            exit++;
        }

        if (losing == 1) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 960, 540);
            over.render(gc,340,150);
            exit++;
        }

        if (exit == 50 && winning == 1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.exit(0);
        }

        if (exit == 50 && losing == 1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.exit(0);
        }

    }

    @Override
    public void mouseClick(MouseEvent e) {
        // mouse click event here
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // mouse move event here
    }

}
