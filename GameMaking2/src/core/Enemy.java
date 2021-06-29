package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Enemy extends Sprite {

    int enemyX;
    int enemyY;
    int enemy2X = new Random().nextInt(960);
    int enemy2Y = new Random().nextInt(10);
    int shotX = enemyX + 15;
    int shotY = enemyY + 10;
    int shot2X = enemy2X + 15;
    int shot2Y = enemy2Y + 10;

    public Enemy()  {
        super.imgPath = "/core/image4.png";
        super.setImage(new Image(imgPath));
    }

    public void EnemyStats() {
        enemyX = new Random().nextInt(960);
        enemyY = new Random().nextInt(10);
    }

}
