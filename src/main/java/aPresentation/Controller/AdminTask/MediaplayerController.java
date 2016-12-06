package aPresentation.Controller.AdminTask;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lenovo on 06-12-2016.
 */
public class MediaplayerController implements Initializable {
    @FXML private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    Slider volumeSlider;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File("C:\\Users\\Lenovo\\IdeaProjects\\2.-Semester-Eksamen\\src\\main\\java\\Media\\JavaFx Tutorial For Beginners 31 - Creating Media Player in JavaFX.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        DoubleProperty width = mv.fitWidthProperty();
        DoubleProperty height = mv.fitHeightProperty();
        width.bind(Bindings.selectDouble(mv.sceneProperty(),"width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(),"height"));
        volumeSlider.setValue(mp.getVolume()* 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volumeSlider.getValue() / 100);
            }
        });

    }

    public void play(javafx.event.ActionEvent actionEvent) {
        mp.play();
        mp.setRate(1);
    }

    public void pause(javafx.event.ActionEvent actionEvent) {
        mp.pause();
    }

    public void fast(javafx.event.ActionEvent actionEvent) {
        mp.setRate(2);
    }

    public void slow(javafx.event.ActionEvent actionEvent) {
        mp.setRate(.5);
    }

    public void reload(javafx.event.ActionEvent actionEvent) {
        mp.seek(mp.getStartTime());
        mp.play();
    }

    public void start(javafx.event.ActionEvent actionEvent) {
        mp.seek(mp.getStartTime());
        mp.stop();
    }

    public void last(javafx.event.ActionEvent actionEvent) {
        mp.seek(mp.getTotalDuration());
        mp.stop();
    }
}
