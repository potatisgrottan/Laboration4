package se.kth.olof.beyar.labb4.view;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class ContrastView {
    private Slider window;
    private Slider level;
    private final Label windowLabel;
    private final Label levelLabel;

    public ContrastView(){
        window = new Slider();
        level = new Slider();
        levelLabel = new Label("Level");
        windowLabel = new Label("Window");
        createSliders();
    }

    private void createSliders(){
        window.setMin(1);
        window.setMax(255);
        level.setMin(1);
        level.setMax(255);
        window.setValue(128);
        level.setValue(128);
    }

    public Label getWindowLabel(){
        return windowLabel;
    }

    public Label getLevelLabel() {
        return levelLabel;
    }

    public Slider getLevelSlider() {
        return level;
    }

    public Slider getWindowSlider() {
        return window;
    }
}
