package testGUI;


//Swing and awt libraries that we need
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import testGUI.GUI;
import testGUI.Card;
import testGUI.Deck;
import testGUI.DrawPanel;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GUI {
	
	
	private JFrame frame;
	//deck 
	private Deck deck;
	//draw panel
	private DrawPanel recipePanel;
	private HomePanel     homePanel;
	private AddRecipePanel     addRecipePanel;
	//message text
	private String message = "";
	//recipe on
	private boolean recipeOn;
	private JButton buttonOne;
	private JButton goHome;
	
	
	private CardLayout cardLayout;
	
	public static void main (String[] args) {
		GUI gui = new GUI ();
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
		recipePanel = new DrawPanel();
		recipePanel.setBounds(0, 0, 600, 500);
		recipePanel.setLayout(null);
		
		homePanel = new HomePanel();
		homePanel.setBounds(0, 0, 600, 500);
		homePanel.setLayout(null);
		
		addRecipePanel = new AddRecipePanel();
		addRecipePanel.setBounds(0, 0, 600, 500);
		addRecipePanel.setLayout(null);
		
		//frame.getContentPane().setLayout(null);
		cardLayout = new CardLayout();
		frame.getContentPane().setLayout(cardLayout);
		frame.getContentPane().add(homePanel ,"homePanel");
		frame.getContentPane().add(recipePanel ,"recipePanel");
		frame.getContentPane().add(addRecipePanel ,"addRecipePanel");
		
		frame.setSize(600,500);
		frame.setVisible(true);
		
		
		buttonOne = new JButton("Go to Recipe Page");
		
		homePanel.add(buttonOne);
		buttonOne.setBounds(200,415, 200, 35);
		buttonOne.addActionListener(new HomeListener());
		//add new recipe button to panel
		JButton showRecipeButton = new JButton("Show Recipes");
		showRecipeButton.setBounds(145, 415, 100, 35);
		recipePanel.add(showRecipeButton);
		//register new recipe button event listener
		showRecipeButton.addActionListener(new ShowRecipeListener());
		
		goHome = new JButton("Go Home");
		goHome.setBounds(245, 415, 100, 35);
		recipePanel.add(goHome);
		//register new recipe button event listener
		goHome.addActionListener(new GoHomeListener());
		
		JButton addRecipe = new JButton("Add Recipe");
		addRecipe.setBounds(345, 415, 100, 35);
		recipePanel.add(addRecipe);
		//register new recipe button event listener
		addRecipe.addActionListener(new AddRecipeListener());
		
		
		JButton goHome2 = new JButton("Go Home");
		goHome2.setBounds(245, 415, 100, 35);
		addRecipePanel.add(goHome2);
		//register new recipe button event listener
		goHome2.addActionListener(new GoHomeListener());
		
		
		
		JButton showRecipeButton2 = new JButton("Show Recipes");
		showRecipeButton2.setBounds(145, 415, 100, 35);
		addRecipePanel.add(showRecipeButton2);
		//register new recipe button event listener
		showRecipeButton2.addActionListener(new HomeListener());
		
		
		JLabel recipeName  = new JLabel("Recipe Name");
		recipeName.setBounds(145, 215, 100, 35);
		addRecipePanel.add(recipeName);
		JTextField  edit        =  new JTextField();
		addRecipePanel.add(edit);
		edit.setBounds(245, 215, 300, 150);
		edit.setName("editField");
		
		JButton saveRecipe = new JButton("Save Recipes");
		saveRecipe.setBounds(345, 415, 100, 35);
		addRecipePanel.add(saveRecipe);
		//register new recipe button event listener
		saveRecipe.addActionListener(new SaveRecipeListener());
		addRecipePanel.createComponentMap();
		
	}
	
	/*
	 * set up a new recipe
	 */
	private void setupNewRecipe() {
		//create a new deck
		deck = new Deck();
		//clear message
		message = "My Favourite Recipes";
		//recipe is on
		recipeOn = true;
	}
	
	
	class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(frame.getContentPane(),"recipePanel");
			frame.repaint();
		}
		
	}
	
	class GoHomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(frame.getContentPane(),"homePanel");
			frame.repaint();
		}
		
	}
	
	
	class AddRecipeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(frame.getContentPane(),"addRecipePanel");
			frame.repaint();
		}
		
	}
	
	
	class SaveRecipeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String recipe = addRecipePanel.getRecipe();
			if (!recipeOn) {
				setupNewRecipe();
				//draw recipes
				deck.addRecipe("pictures/pizza_resize.png", recipe);
				recipePanel.setRecipes(deck.getRecipes());
				
				//makes sure the recipe panel is initialized
				recipePanel.setRecipeOn(recipeOn);
				frame.repaint();
			}
			else {
				deck.addRecipe("pictures/pizza_resize.png", recipe);
			}
			
			cardLayout.show(frame.getContentPane(),"recipePanel");
			frame.repaint();
		}
		
	}
	
	/*
	 * new recipe button event handling
	 */
	class ShowRecipeListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			//start new recipe
			if (!recipeOn) {
				setupNewRecipe();
				//draw recipes
				recipePanel.setRecipes(deck.getRecipes());
				recipePanel.setMessage(message);
				recipePanel.setRecipeOn(recipeOn);
				frame.repaint();
			}
		}
	}
	
}

/*
 * class used to draw the panel
 */
class DrawPanel extends JPanel {
	
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
		g.drawString(message,240,125);
		//draw recipes
		if (recipes != null) {
			for (int i=0; i < recipes.size(); i++) {
				Image image = recipes.get(i).getImage();
				g.drawImage(image,(100+i*300),300,this);
				int y = 225;
				String recipe = recipes.get(i).getRecipe();
				for(String line: recipe.split("\n")) {
					g.drawString(line,100+i*300,y+=g.getFontMetrics().getHeight());
				}
			}	
		}
	
	}
	
	
	
}	

/*
 * class used to draw the Home panel
 */
class HomePanel extends JPanel {
	
	
	//message
	String message = "HOME PAGE";
	
	/*
	 * set message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/*
	 * the actual method used to draw the panel
	 */
	public void paintComponent(Graphics g) {
		//green background
		g.setColor(new Color(0.5f, 0.5f, 0.5f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(1.0f, 0.0f, 0.0f));
		g.drawString(message,240,225);
		
	
	}
}



/*
* class used to draw the Add Recipe panel
*/
class AddRecipePanel extends JPanel {
	
	
	//message
	String message = "Add a Recipe";
	
	Map<String, Component> componentMap = new HashMap<>();
	
	
	/*
	 * set message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/*
	 * the actual method used to draw the panel
	 */
	public void paintComponent(Graphics g) {
		//green background
		g.setColor(new Color(0.5f, 0.5f, 0.5f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(1.0f, 0.0f, 0.0f));
		g.drawString(message,240,125);
		
	
	}
	
	public void createComponentMap() {
		
		Component[] components = getComponents();
		for(Component comp: components)
		{
			componentMap.put(comp.getName(),comp);
		}
	}
	
	public String getRecipe()
	{
		Component comp = componentMap.get("editField");
		return  ((JTextField)comp).getText();
	}
	
}