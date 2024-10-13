package se.kth.olof.beyar.labb4.model;

public class MenuModel {
	private boolean isFileOpen;
	private String imagePath;

	public boolean isFileOpen() {
			return isFileOpen;
	}

	public void setFileOpen(boolean fileOpen) {
			isFileOpen = fileOpen;
	}

	public void setImage(String image)
	{
		this.imagePath = image;
	}

	public String getFileImagePath() { return imagePath; }
}
