package se.kth.olof.beyar.labb4.labboration4.Controller;

import se.kth.olof.beyar.labb4.labboration4.Model.*;
import se.kth.olof.beyar.labb4.labboration4.View.*;

public class ImageHandlingController {

    private final HistogramModel hModel;
    private final GrayScaleModel gModel;
    private final InvertColorsModel iModel;
    private final ContrastModel cModel;

    private final HistogramView hView;
    private final GrayScaleView gView;
    private final InvertColorsView iView;
    private final ContrastView cView;

    public ImageHandlingController(HistogramModel hModel, GrayScaleModel gModel, InvertColorsModel iModel,
                                   ContrastModel cModel, HistogramView hView, GrayScaleView gView,
                                   InvertColorsView iView, ContrastView cView)
    {
        this.hModel = hModel;
        this.gModel = gModel;
        this.iModel = iModel;
        this.cModel = cModel;
        this.hView = hView;
        this.gView = gView;
        this.iView = iView;
        this.cView = cView;
    }

    void handleHistogramSelected(HistogramView hView,HistogramModel hModel){
        //TODO implement
    }

    void handleContrastSelected(ContrastView cView,ContrastModel cModel){
        // TODO implement
    }

    void handleGrayScaleSelected(GrayScaleView gView,GrayScaleModel gModel){
        //TODO implement
    }

    void handleInvertColorsSelected(InvertColorsView iView, InvertColorsModel iModel){
        //TODO implement
    }


}
