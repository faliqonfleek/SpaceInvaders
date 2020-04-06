package core;

import libs.Sprite;
import javafx.scene.image.Image;


public class PlayerShot extends Sprite {

    public PlayerShot()  {
        super.imgPath = "/core/Laser.png";
        super.setImage(new Image(imgPath));
    }

}
