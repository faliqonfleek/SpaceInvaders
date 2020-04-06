package core;

import javafx.scene.canvas.GraphicsContext;
import libs.Configs;
import libs.Sprite;
import javafx.scene.image.Image;

import java.util.Random;


public class WinGame extends Sprite {

    public WinGame()  {
        super.imgPath = "/core/youwin.png";
        super.setImage(new Image(imgPath));
    }

}
