package core;

import libs.Sprite;
import javafx.scene.image.Image;


public class Shot extends Sprite {

    public Shot()  {
        super.imgPath = "/core/enemyLaser.png";
        super.setImage(new Image(imgPath));
    }

}
