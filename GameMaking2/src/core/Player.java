package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Player extends Sprite {

    public Player()  {
        super.imgPath = "/core/image6.png";
        super.setImage(new Image(imgPath));
    }

}
