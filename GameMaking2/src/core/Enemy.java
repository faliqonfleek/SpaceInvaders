package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Enemy extends Sprite {

    public Enemy()  {
        super.imgPath = "/core/image4.png";
        super.setImage(new Image(imgPath));
    }

}
