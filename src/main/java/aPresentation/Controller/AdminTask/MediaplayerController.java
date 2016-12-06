package aPresentation.Controller.AdminTask;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;


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
    @FXML
    Slider timeSlider;

    private boolean atEndOfMedia = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File("C:\\Users\\Lenovo\\IdeaProjects\\2.-Semester-Eksamen\\src\\main\\java\\Media\\COSTA RICA IN 4K 60fps (ULTRA HD) w Freefly Movi.mp4").getAbsolutePath();
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
        mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                timeSlider.setValue(newValue.toSeconds());
            }
        });
        timeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mp.seek(Duration.seconds(timeSlider.getValue()));
            }
        });

        mp.setOnReady(new Runnable() {
            @Override
            public void run() {
                timeSlider.setMin(0.0);
                timeSlider.setValue(0.0);
                timeSlider.setMax(mp.getTotalDuration().toSeconds());
            }
        });
    }
    public void play(javafx.event.ActionEvent actionEvent) {
        Status status = mp.getStatus();
        if ( status == Status.PAUSED || status == Status.READY || status == Status.STOPPED)
        {
            mp.play();
        } else {
            mp.pause();
        }
    }

}
