package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class GameOver extends Sprite {

    public GameOver()  {
        super.imgPath = "/core/gameover.png";
        super.setImage(new Image(imgPath));
    }

}
