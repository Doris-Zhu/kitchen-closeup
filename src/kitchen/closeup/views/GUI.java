package kitchen.closeup.views;


//Swing and awt libraries that we need
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import kitchen.closeup.interfaces.IRecipe;
import kitchen.closeup.interfaces.IRecipeCollection;
import kitchen.closeup.models.Recipe;
import kitchen.closeup.models.RecipeCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GUI {
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private JFrame frame;
	private IRecipeCollection recipeCol;
	//draw panel
	private DrawPanel recipePanel;
	private HomePanel     homePanel;
	private AddRecipePanel     addRecipePanel;
	//message text
	private String message = "";
	//recipe initialized
	private boolean initDone;
	
	
	
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
		recipePanel.setBounds(0, 0, WIDTH, HEIGHT);
		recipePanel.setLayout(null);
		
		homePanel = new HomePanel();
		homePanel.setBounds(0, 0, WIDTH, HEIGHT);
		homePanel.setLayout(null);
		
		addRecipePanel = new AddRecipePanel();
		addRecipePanel.setBounds(0, 0, WIDTH, HEIGHT);
		addRecipePanel.setLayout(null);
		
		//frame.getContentPane().setLayout(null);
		cardLayout = new CardLayout();
		frame.getContentPane().setLayout(cardLayout);
		frame.getContentPane().add(homePanel ,"homePanel");
		frame.getContentPane().add(recipePanel ,"recipePanel");
		frame.getContentPane().add(addRecipePanel ,"addRecipePanel");
		
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		
		
		JButton buttonOne = new JButton("Go to Recipe Page");
		
		homePanel.add(buttonOne);
		buttonOne.setBounds(250,415, 200, 35);
		buttonOne.addActionListener(new HomeListener());
		//add new recipe button to panel
		JButton showRecipeButton = new JButton("Show Recipes");
		showRecipeButton.setBounds(145, 415, 100, 35);
		recipePanel.add(showRecipeButton);
		//register new recipe button event listener
		showRecipeButton.addActionListener(new ShowRecipeListener());
		
		JButton goHome = new JButton("Go Home");
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
		
		
		addRecipePanelComponents(addRecipePanel);
		
	}
	
	private void addRecipePanelComponents(JPanel panel)
	{
		JLabel recipeName  = new JLabel("Recipe Name");
		recipeName.setBounds(145, 155, 100, 35);
		addRecipePanel.add(recipeName);
		JTextField  name        =  new JTextField();
		addRecipePanel.add(name);
		name.setBounds(245, 145, 200, 50);
		name.setName("name");
		
		
		JLabel description  = new JLabel("Description");
		description.setBounds(145, 205, 100, 35);
		addRecipePanel.add(description);
		JTextField  desc  =  new JTextField();
		addRecipePanel.add(desc);
		desc.setBounds(245, 195, 200, 50);
		desc.setName("description");
		
		JLabel ingredients  = new JLabel("Ingredients");
		ingredients.setBounds(145, 255, 100, 35);
		addRecipePanel.add(ingredients);
		JTextField  ing  =  new JTextField();
		addRecipePanel.add(ing);
		ing.setBounds(245, 245, 200, 50);
		ing.setName("ingredients");
		
		JLabel instructions  = new JLabel("Recipe");
		instructions.setBounds(145, 305, 100, 35);
		addRecipePanel.add(instructions);
		JTextField  inst  =  new JTextField();
		addRecipePanel.add(inst);
		inst.setBounds(245, 295, 200, 50);
		inst.setName("instructions");
		
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
		
		recipeCol = new RecipeCollection();
		//clear message
		message = "My Favourite Recipes";
		//recipe is on
		initDone = true;
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
			String recipeName = addRecipePanel.getRecipeName();
			String recipeDesc = addRecipePanel.getRecipeDescription();
			String recipeIng = addRecipePanel.getRecipeIngredients();
			String recipeInst = addRecipePanel.getRecipeInstructions();
			if (!initDone) {
				setupNewRecipe();
				
				IRecipe nrecipe = new Recipe(); // This will move to the Recipe Factory
				nrecipe.setName(recipeName);
				nrecipe.setDescription(recipeDesc);
				nrecipe.setImage(new ImageIcon("pictures/pizza_resize.png").getImage());
				List<String> ingredients = new ArrayList<>();
				for(String ing : recipeIng.split(",")) {
					ingredients.add(ing);
				}
				
				List<String> instructions = new ArrayList<>();
				for(String inst : recipeInst.split(",")) {
					instructions.add(inst);
				}
				
				
				nrecipe.setIngredients(ingredients);
				nrecipe.setInstructions(instructions);
				recipeCol.add(nrecipe);
				
				recipePanel.setRecipes(recipeCol.getRecipes());
				//makes sure the recipe panel is initialized
				recipePanel.setinitDone(initDone);
				frame.repaint();
			}
			else {
				IRecipe nrecipe = new Recipe(); // This will move to the Recipe Factory
				nrecipe.setName(recipeName);
				nrecipe.setDescription(recipeDesc);
				nrecipe.setImage(new ImageIcon("pictures/pizza_resize.png").getImage());
				List<String> ingredients = new ArrayList<>();
				for(String ing : recipeIng.split(",")) {
					ingredients.add(ing);
				}
				
				List<String> instructions = new ArrayList<>();
				for(String inst : recipeInst.split(",")) {
					instructions.add(inst);
				}
				
				
				nrecipe.setIngredients(ingredients);
				nrecipe.setInstructions(instructions);
				recipeCol.add(nrecipe);
				
				recipePanel.setRecipes(recipeCol.getRecipes());
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
			if (!initDone) {
				setupNewRecipe();
				recipePanel.setRecipes(recipeCol.getRecipes());
				recipePanel.setMessage(message);
				recipePanel.setinitDone(initDone);
				frame.repaint();
			}
		}
	}
	
}

/*
 * class used to draw the panel
 */
class DrawPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//recipes
	private List<IRecipe> allRecipes;
	
	//message
	String message = "";
	//game on
	boolean initDone;
	
	/*
	 * set recipes to be drawn on panel
	 */
	
	public void setRecipes(List<IRecipe> allRecipes) {
		this.allRecipes = allRecipes;
	}
	
	
	/*
	 * set message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/*
	 * set initDone signal
	 */
	public void setinitDone(boolean initDone) {
		this.initDone = initDone;
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
		//draw recipes
		
		
		if (allRecipes != null) {
			for (int i=0; i < allRecipes.size(); i++) {
				Image image = allRecipes.get(i).getImage();
				g.drawImage(image,(100+i*400),300,this);
				int y = 175;
				String recipe = allRecipes.get(i).getFullDescription();
				for(String line: recipe.split("\n")) {
					g.drawString(line,100+i*400,y+=g.getFontMetrics().getHeight());
				}
			}	
		}
	
	}
	
	
	
}	

/*
 * class used to draw the Home panel
 */
class HomePanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		g.drawString(message,260,225);
		
	
	}
}



/*
* class used to draw the Add Recipe panel
*/
class AddRecipePanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public String getRecipeName()
	{
		Component comp = componentMap.get("name");
		return  ((JTextField)comp).getText();
	}
	
	public String getRecipeDescription()
	{
		Component comp = componentMap.get("description");
		return  ((JTextField)comp).getText();
	}
	
	public String getRecipeIngredients()
	{
		Component comp = componentMap.get("ingredients");
		return  ((JTextField)comp).getText();
	}
	
	public String getRecipeInstructions()
	{
		Component comp = componentMap.get("instructions");
		return  ((JTextField)comp).getText();
	}
	
}