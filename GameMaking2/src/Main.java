import core.GameCore;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import libs.Configs;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //private GraphicsContext gc;

        primaryStage.setTitle(Configs.appTitle);

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(Configs.appWidth, Configs.appHeight);
        root.getChildren().add(canvas);

        GameCore gameCore = new GameCore();

        // keyboard event
        ArrayList<String> input = new ArrayList<>();

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();

                        // only add once and prevents duplicate
                        if (!input.contains(code))
                            input.add(code);
                    }
                }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                    }
                }
        );

        // mouse event
        scene.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        gameCore.mouseClick(event);
                    }
                }
        );

        scene.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        gameCore.mouseMoved(event);
                    }
                }
        );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gameCore.init(gc);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                int t = Math.round((currentNanoTime - startNanoTime) / 1000000000);
                gameCore.animate(gc, t, input);
            }
        }.start();

        primaryStage.show();
    }

    /*MediaPlayer musicPlayer; {
        Media mp3music = new Media(getClass().getResource(Configs.musicPath).toExternalForm());
        musicPlayer = new MediaPlayer(mp3music);
        musicPlayer.setAutoPlay(true);
        musicPlayer.setVolume(0.2);

        musicPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                musicPlayer.seek(Duration.ZERO);
            }
        });
    }*/
}
