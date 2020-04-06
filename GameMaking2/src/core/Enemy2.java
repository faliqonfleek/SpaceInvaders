package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Enemy2 extends Sprite {

    public Enemy2()  {
        super.imgPath = "/core/enemy2.png";
        super.setImage(new Image(imgPath));
    }

}
