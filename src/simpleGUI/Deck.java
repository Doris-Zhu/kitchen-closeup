package simpleGUI;

import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Deck {
	
		
	//ArrayList deck
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck() {
		int index = 0;	
		
		index++;
		deck.add(new Card( new ImageIcon("pictures/salad.png").getImage()));	
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
			
}
