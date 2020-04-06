package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Explosion extends Sprite {

    public Explosion()  {
        super.imgPath = "/core/explosion.png";
        super.setImage(new Image(imgPath));
    }

}
