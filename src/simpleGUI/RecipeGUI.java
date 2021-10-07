package simpleGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import simpleGUI.RecipeGUI;
import simpleGUI.Card;
import simpleGUI.Deck;
import simpleGUI.DrawFrame;
import simpleGUI.RecipeGUI.NewRecipeListener;

import java.util.ArrayList;




public class RecipeGUI {
	
	
	private JFrame frame;
	//deck 
	private Deck deck;
	//draw panel
	private DrawFrame drawPanel;
	//message text
	private String message = "";
	//recipe on
	private boolean recipeOn;
	
	public static void main (String[] args) {
		RecipeGUI gui = new RecipeGUI ();
		gui.init();
	}
	
	/*
	 * initialize the GUI
	 */
	public void init() {
		//new frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//new draw panel
		drawPanel = new DrawFrame();
		drawPanel.setBounds(0, 0, 600, 500);
		drawPanel.setLayout(null);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(drawPanel);
		frame.setSize(600,500);
		frame.setVisible(true);
		//add new recipe button to panel
		JButton newRecipeButton = new JButton("NEW RECIPE");
		newRecipeButton.setBounds(145, 415, 100, 35);
		drawPanel.add(newRecipeButton);
		//register new recipe button event listener
		newRecipeButton.addActionListener(new NewRecipeListener());
	}
	
	/*
	 * set up a new recipe
	 */
	private void setupNewRecipe() {
		//create a new deck
		deck = new Deck();
		//clear message
		message = "Salad Recipe";
		//recipe is on
		recipeOn = true;
	}
	
	/*
	 * new recipe button event handling
	 */
	class NewRecipeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//start new recipe
			if (!recipeOn) {
				setupNewRecipe();
				//draw recipes
				drawPanel.setRecipes(deck.getRecipes());
				drawPanel.setMessage(message);
				drawPanel.setRecipeOn(recipeOn);
				frame.repaint();
			}
		}
	}
	
}

/*
 * class used to draw the panel
 */
class DrawFrame extends JPanel {
	
	//recipes
	private ArrayList<Card> recipes;
	//message
	String message = "";
	//game on
	boolean recipeOn;
	
	/*
	 * set recipes to be drawn on panel
	 */
	public void setRecipes(ArrayList<Card> recipes) {
		this.recipes = recipes;
	}
	
	/*
	 * set message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * set recipeOn signal
	 */
	public void setRecipeOn(boolean recipeOn) {
		this.recipeOn = recipeOn;
	}
	
	/*
	 * the actual method used to draw the panel
	 */
	public void paintComponent(Graphics g) {
		//green background
		g.setColor(new Color(0.0f, 0.5f, 0.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(1.0f, 0.0f, 0.0f));
		g.drawString(message,240,225);
		//draw recipes
		if (recipes != null) {
			for (int i=0; i < recipes.size(); i++) {
				Image image = recipes.get(i).getImage();
				g.drawImage(image,(240+i*20),(285),this);
			}	
		}
	
	}
	
}	