package simpleGUI;

import java.awt.Image;

public class Card {
	
	
	//image of the card
	private Image image;
	
	public Card(Image image) {
		this.image = image;
	}

	
	/**
	 * return image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * set image
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
}
