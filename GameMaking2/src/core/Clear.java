package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class Clear extends Sprite {

    public Clear()  {
        super.imgPath = "/core/black.png";
        super.setImage(new Image(imgPath));
    }

}
