package libs;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.media.AudioClip;
import java.nio.file.Paths;
//import java.awt.Image;

public class Sprite extends ImageView {

    //private boolean visible;
    //private Image image;
    //private boolean dying;

    int xpos;
    int ypos;
    //int dx;

    public String imgPath;
    Configs config = new Configs();

    /*public Sprite() {

        visible = true;
    }

    public void die() {

        visible = false;
    }

    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    public void setImage(Image image) {

        this.image = image;
    }

    public Image getImage() {

        return image;
    }

    public void setX(int xpos) {

        this.xpos = xpos;
    }

    public void setY(int ypos) {

        this.ypos = ypos;
    }

    public int getY() {

        return ypos;
    }

    public int getX() {

        return xpos;
    }

    public void setDying(boolean dying) {

        this.dying = dying;
    }

    public boolean isDying() {

        return this.dying;
    }*/

    public void resize(double factor) {
        double newdim = this.getImage().getHeight() * factor;
        this.setImage(new Image(this.imgPath, newdim, newdim, true, false));
    }

    public void resize(int width, int height) {
        // check first which one is larger between height and width
        Image tmp = new Image(this.imgPath);
        this.setImage(new Image(this.imgPath, width, height, false, true));
    }

    public void render(GraphicsContext gc, int x, int y) {
        gc.drawImage(this.getImage(), x, y);
        this.ypos = y;
        this.xpos = x;
    }

    public void changeImage(String imgPath) {
        double width = this.getImage().getWidth();
        double height = this.getImage().getHeight();
        this.setImage(new Image(imgPath, width, height, true, true));
    }

    public void soundEffect(String effectPath) {
        String path = "src" + effectPath;
        AudioClip audioClip = new AudioClip(Paths.get(path).toUri().toString());
        audioClip.play();
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(this.xpos, this.ypos, this.getImage().getWidth(), this.getImage().getHeight());
    }

    public boolean collide(Sprite sprite) {
        return (this.getBoundary().intersects(sprite.getBoundary()));
    }


}
