package kitchen.closeup.views;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Deck {
	int index = 0;
		
	//ArrayList deck
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
			
		
		index++;
		deck.add(new Card( new ImageIcon("pictures/salad.png").getImage(), 
				"Chicken Ceaser Salad,\nIngredients: Chicken, Lettuce"));	
	/*	
		index++;
		deck.add(new Card( new ImageIcon("pictures/2.png").getImage()));
		
		index++;
		deck.add(new Card( new ImageIcon("pictures/3.png").getImage()));
			*/	
	}
	
	public ArrayList<Card> getRecipes() {
		return deck;
	}
	
	public void  addRecipe(String imgFile , String recipe)
	{
		deck.add(new Card( new ImageIcon(imgFile).getImage(), recipe));
		index++;
	}
}
