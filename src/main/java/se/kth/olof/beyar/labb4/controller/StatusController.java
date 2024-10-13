package se.kth.olof.beyar.labb4.controller;

import javafx.scene.layout.FlowPane;
import se.kth.olof.beyar.labb4.model.StatusModel;
import se.kth.olof.beyar.labb4.view.StatusView;

public class StatusController
{
    private StatusModel model;
    private StatusView view;

    public StatusController(StatusModel model, StatusView view) {
        this.model = model;
        this.view = view;
    }

    public void setStatusMsg(String statusMsg)
    {
        model.setStatusMsg(statusMsg);
    }

    public FlowPane buildStatusView()
    {
        return view.createStatus(model.getStatusMsg());
    }
}
