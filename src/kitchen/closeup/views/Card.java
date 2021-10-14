package kitchen.closeup.views;

import java.awt.Image;

public class Card {
	
	
	//image of the card
	private Image image;
	private String recipe;
	
	public Card(Image image , String recipe) {
		this.image = image;
		this.recipe = recipe;
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
	
	
	/**
	 * return recipe
	 */
	public String getRecipe() {
		return recipe;
	}

	/**
	 * set recipe
	 */
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}
	
}
