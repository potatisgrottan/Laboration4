package se.kth.olof.beyar.labb4.model;

/**
 * A model class for managing the status message in the application.
 */
public class StatusModel
{
    private String statusMsg;

    /**
     * Constructs a StatusModel object.
     */
    public StatusModel() {}

    /**
     * Gets the current status message.
     *
     * @return The current status message.
     */
    public String getStatusMsg()
    {
        return statusMsg;
    }

    /**
     * Sets the status message.
     *
     * @param statusMsg The status message to set.
     */
    public void setStatusMsg(String statusMsg)
    {
        this.statusMsg = statusMsg;
    }
}
