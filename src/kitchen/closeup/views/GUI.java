package kitchen.closeup.views;


//Swing and awt libraries that we need
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;


import kitchen.closeup.interfaces.IRecipe;
import kitchen.closeup.interfaces.IRecipeCollection;
import kitchen.closeup.models.Recipe;
import kitchen.closeup.models.RecipeCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class GUI {
	
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	private JFrame frame;
	private IRecipeCollection recipeCol;
	private IRecipeCollection searchResultCol;
	private IRecipeCollection deleteCol;
	//draw panel
	private DrawPanel recipePanel;
	private HomePanel     homePanel;
	private AddRecipePanel     addRecipePanel;
	private SearchPanel       searchPanel;
	private SearchResultPanel       searchResultPanel;
	private DeletePanel       deletePanel;
	private DeleteResultPanel       deleteResultPanel;
	private ShowStepsPanel       showStepsPanel;
	//message text
	private String message = "";
	//recipe initialized
	private boolean initDone;
	
	private String path;
	private boolean appendToFile = true; 
	
	public GUI(String path) {
		this.path = path;
	}
	
	public void writeToFile(IRecipe recipe) {
		FileWriter write;
		try {
			write = new FileWriter(path,appendToFile);
			PrintWriter printWriter = new PrintWriter(write);
			printWriter.println(recipe.getFullDescription());
			printWriter.println("Image Path: " + recipe.getImagePath());
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private CardLayout cardLayout;
	
	public static void main (String[] args) {
		GUI gui = new GUI ("recipes/recipes.txt");
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
		
		searchPanel = new SearchPanel();
		searchPanel.setBounds(0, 0, WIDTH, HEIGHT);
		searchPanel.setLayout(null);
		
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setBounds(0, 0, WIDTH, HEIGHT);
		searchResultPanel.setLayout(null);
		
		deletePanel = new DeletePanel();
		deletePanel.setBounds(0, 0, WIDTH, HEIGHT);
		deletePanel.setLayout(null);
		
		deleteResultPanel = new DeleteResultPanel();
		deleteResultPanel.setBounds(0, 0, WIDTH, HEIGHT);
		deleteResultPanel.setLayout(null);
		
		showStepsPanel = new ShowStepsPanel();
		showStepsPanel.setBounds(0, 0, WIDTH, HEIGHT);
		showStepsPanel.setLayout(null);
		
		//frame.getContentPane().setLayout(null);
		cardLayout = new CardLayout();
		frame.getContentPane().setLayout(cardLayout);
		frame.getContentPane().add(homePanel ,"homePanel");
		frame.getContentPane().add(recipePanel ,"recipePanel");
		frame.getContentPane().add(addRecipePanel ,"addRecipePanel");
		frame.getContentPane().add(searchPanel ,"searchPanel");
		frame.getContentPane().add(searchResultPanel ,"searchResultPanel");
		frame.getContentPane().add(deletePanel ,"deletePanel");
		frame.getContentPane().add(deleteResultPanel ,"deleteResultPanel");
		frame.getContentPane().add(showStepsPanel ,"showStepsPanel");
		
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		
		
		JButton buttonOne = new JButton("Go to Recipe Page");
		
		homePanel.add(buttonOne);
		buttonOne.setBounds(250,415, 200, 35);
		buttonOne.addActionListener(new HomeListener());
		//add new recipe button to panel
		JButton showNextRecipeButton = new JButton(">>");
		showNextRecipeButton.setBounds(80, 0, 75, 35);
		recipePanel.add(showNextRecipeButton);
		//register new recipe button event listener
		showNextRecipeButton.addActionListener(new ShowNextRecipeListener());
		
		JButton showLastRecipeButton = new JButton("<<");
		showLastRecipeButton.setBounds(5, 0, 75, 35);
		recipePanel.add(showLastRecipeButton);
		//register new recipe button event listener
		showLastRecipeButton.addActionListener(new ShowPreviousRecipeListener());
		
		JButton showStepsButton = new JButton("Show Steps");
		showStepsButton.setBounds(155, 0, 120, 35);
		recipePanel.add(showStepsButton);
		//register new recipe button event listener
		showStepsButton.addActionListener(new ShowStepsListener());
		
		
		JButton goHome = new JButton("Go Home");
		goHome.setBounds(275, 0, 100, 35);
		recipePanel.add(goHome);
		//register new recipe button event listener
		goHome.addActionListener(new GoHomeListener());
		
		JButton addRecipe = new JButton("Add Recipe");
		addRecipe.setBounds(375, 0, 140, 35);
		recipePanel.add(addRecipe);
		//register new recipe button event listener
		addRecipe.addActionListener(new AddRecipeListener());
		
		JButton searchRecipe = new JButton("Search");
		searchRecipe.setBounds(515, 0, 90, 35);
		recipePanel.add(searchRecipe);
		//register new recipe button event listener
		searchRecipe.addActionListener(new SearchListener());
		
		JButton deleteRecipe = new JButton("Delete");
		deleteRecipe.setBounds(605, 0, 90, 35);
		recipePanel.add(deleteRecipe);
		//register new recipe button event listener
		deleteRecipe.addActionListener(new DeleteListener());
		
		
		
		JButton showRecipeButton2 = new JButton("Show Recipes");
		showRecipeButton2.setBounds(105, 0, 140, 35);
		addRecipePanel.add(showRecipeButton2);
		//register new recipe button event listener
		showRecipeButton2.addActionListener(new HomeListener());
		
		
		addPanelComponents(addRecipePanel);
		addRecipePanelComponents();
		addRecipePanel.createComponentMap();
		
		addPanelComponents(searchPanel);
		addSearchPanelComponents();
		searchPanel.createComponentMap();
		
		addSearchResultPanelComponents();
		
		//addPanelComponents(deletePanel);
		addDeletePanelComponents();
		deletePanel.createComponentMap();
		
		addDeleteResultPanelComponents();
		
		addShowStepsPanelComponents();
		
		
	}
	
	private void addPanelComponents(JPanel panel)
	{
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.black);
		
		
		JLabel recipeName  = new JLabel("Recipe Name");
		recipeName.setBounds(145, 155, 100, 35);
		panel.add(recipeName);
		JTextField  name        =  new JTextField();
		panel.add(name);
		name.setBounds(245, 145, 200, 50);
		name.setName("name");
		name.setBorder(blackline);
		
		JLabel description  = new JLabel("Description");
		description.setBounds(145, 205, 100, 35);
		panel.add(description);
		JTextField  desc  =  new JTextField();
		panel.add(desc);
		desc.setBounds(245, 195, 200, 50);
		desc.setName("description");
		desc.setBorder(blackline);
		
		JLabel ingredients  = new JLabel("Ingredients");
		ingredients.setBounds(145, 255, 100, 35);
		panel.add(ingredients);
		JTextField  ing  =  new JTextField();
		panel.add(ing);
		ing.setBounds(245, 245, 200, 50);
		ing.setName("ingredients");
		ing.setBorder(blackline);
		
		JLabel instructions  = new JLabel("Recipe");
		instructions.setBounds(145, 305, 100, 35);
		panel.add(instructions);
		JTextField  inst  =  new JTextField();
		panel.add(inst);
		inst.setBounds(245, 295, 200, 50);
		inst.setName("instructions");
		inst.setBorder(blackline);
		
		
		
		JButton goHome = new JButton("Go Home");
		goHome.setBounds(245, 0, 100, 35);
		panel.add(goHome);
		//register new recipe button event listener
		goHome.addActionListener(new GoHomeListener());
	}
	
	private void addShowStepsPanelComponents()
	{
		
		JButton showRecipeButton = new JButton("Show Recipes");
		showRecipeButton.setBounds(155, 0, 140, 35);
		showStepsPanel.add(showRecipeButton);
		//register new recipe button event listener
		showRecipeButton.addActionListener(new HomeListener());
		
		JButton goHome = new JButton("Go Home");
		goHome.setBounds(295, 0, 100, 35);
		showStepsPanel.add(goHome);
		//register new recipe button event listener
		goHome.addActionListener(new GoHomeListener());
		
		JButton showNextRecipeButton = new JButton(">>");
		showNextRecipeButton.setBounds(80, 0, 75, 35);
		showStepsPanel.add(showNextRecipeButton);
		//register new recipe button event listener
		showNextRecipeButton.addActionListener(new ShowNextStepListener());
		
		JButton showLastRecipeButton = new JButton("<<");
		showLastRecipeButton.setBounds(5, 0, 75, 35);
		showStepsPanel.add(showLastRecipeButton);
		//register new recipe button event listener
		showLastRecipeButton.addActionListener(new ShowPreviousStepListener());
	}
	
	private void addRecipePanelComponents()
	{
		
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.black);
		
		JLabel imageLabel  = new JLabel("Image Path");
		imageLabel.setBounds(470, 100, 200, 50);
		addRecipePanel.add(imageLabel);
		
		JTextField  imageField        =  new JTextField();
		addRecipePanel.add(imageField);
		imageField.setBounds(445, 145, 200, 50);
		imageField.setName("imagename");
		imageField.setBorder(blackline);
		
		JButton saveRecipe = new JButton("Save Recipes");
		saveRecipe.setBounds(345, 0, 140, 35);
		addRecipePanel.add(saveRecipe);
		//register new recipe button event listener
		saveRecipe.addActionListener(new SaveRecipeListener(this));
		
		
		JButton getImagePath = new JButton("Recipe Image");
		getImagePath.setBounds(505, 0, 140, 35);
		addRecipePanel.add(getImagePath);
		//register new recipe button event listener
		getImagePath.addActionListener(new OpenImageListener(addRecipePanel));
	}
	
	private void addSearchPanelComponents()
	{
		
		JButton searchRecipe = new JButton("Search");
		searchRecipe.setBounds(345, 0, 100, 35);
		searchPanel.add(searchRecipe);
		//register new recipe button event listener
		searchRecipe.addActionListener(new SearchResultListener());
		
	}
	
	
	private void addDeletePanelComponents()
	{
		Border blackline;
		blackline = BorderFactory.createLineBorder(Color.black);
		
		JButton deleteRecipe = new JButton("Delete");
		deleteRecipe.setBounds(345, 0, 100, 35);
		deletePanel.add(deleteRecipe);
		//register new recipe button event listener
		deleteRecipe.addActionListener(new DeleteResultListener());
		
		JLabel recipeName  = new JLabel("Recipe Name");
		recipeName.setBounds(145, 155, 100, 35);
		deletePanel.add(recipeName);
		JTextField  name        =  new JTextField();
		deletePanel.add(name);
		name.setBounds(245, 145, 200, 50);
		name.setName("name");
		name.setBorder(blackline);
		
		JButton goHome = new JButton("Go Home");
		goHome.setBounds(245, 0, 100, 35);
		deletePanel.add(goHome);
		//register new recipe button event listener
		goHome.addActionListener(new GoHomeListener());
		
	}
	
	private void addSearchResultPanelComponents()
	{
		
		JButton searchRecipe = new JButton("Back to Search");
		searchRecipe.setBounds(545, 0, 140, 35);
		searchResultPanel.add(searchRecipe);
		//register new recipe button event listener
		searchRecipe.addActionListener(new SearchListener());
		
	}
	
	private void addDeleteResultPanelComponents()
	{
		JButton deleteRecipe = new JButton("Confirm Delete");
		deleteRecipe.setBounds(400, 0, 140, 35);
		deleteResultPanel.add(deleteRecipe);
		
		deleteRecipe.addActionListener(new ConfirmDeleteListener());
		JButton searchRecipe = new JButton("Back to Search");
		searchRecipe.setBounds(545, 0, 140, 35);
		deleteResultPanel.add(searchRecipe);
		//register new recipe button event listener
		searchRecipe.addActionListener(new DeleteListener());
		
	}
	
	
	
	
	private void parseRecipes(String recipeString) {
		recipeCol.clear();
		Scanner scanner = new Scanner(recipeString);
		int fieldId = 0;
		int numFields = 5;
		
		IRecipe recipe = null; 
		while(scanner.hasNextLine())
		{			
			String line = scanner.nextLine();
			String[] str;
			switch(fieldId) {
				case 0:
					recipe = new Recipe(); 
					str = line.split(":");
					recipe.setName(str[1].strip());
					break;
				case 1:
					str = line.split(":");
					recipe.setDescription(str[1].strip());
					break;
				case 2:
					str = line.split(":");
					String[] ingredients  = str[1].replace("[", "").replace("]", "").split(",");
					List<String> ing = new ArrayList<>();
					for(String s: ingredients) {
						ing.add(s.strip());						
					}
					recipe.setIngredients(ing);
					break;
				case 3:
					str = line.split(":");
					String[] instructions  = str[1].replace("[", "").replace("]", "").split(",");
					List<String> inst = new ArrayList<>();
					for(String s: instructions) {
						inst.add(s.strip());						
					}
					recipe.setInstructions(inst);
					break;
				case 4:
					str = line.split(":");
					recipe.setImagePath(str[1].strip());
					recipeCol.add(recipe);
					break;
				default:
					break;
			}
			
			
			
			fieldId +=1;
			fieldId %=numFields;
		}
		
	}
	
	/*
	 * set up a new recipe
	 */
	private void setupNewRecipe() {
		
		recipeCol = new RecipeCollection();
		//clear message
		message = "All Recipes";
		//recipe is on
		initDone = true;
		try {
			FileReader fr = new FileReader(this.path );
			int i;
			StringBuilder sb = new StringBuilder();
			
		
			while((i = fr.read())!= -1) {
				sb.append((char)i);
			}
			String recipe = sb.toString();
			parseRecipes(recipe);	
			fr.close();	
				
			
		} catch (IOException e) {
			
		}
		
	}
	
	
	private void setupSearchResultPanel() {
		
		searchResultCol = new RecipeCollection();
		//clear message
		message = "Search Results";
		//recipe is on
		
		
	}
	
	private void setupDeleteResultPanel() {
		
		deleteCol = new RecipeCollection();
		//clear message
		message = "Search Results - for Delete";
		//recipe is on
		
		
	}
	
	
	class HomeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!initDone) {
				setupNewRecipe();
				recipePanel.setRecipes(recipeCol);
				recipePanel.setMessage(message);
				recipePanel.setinitDone(initDone);			
				
				//frame.repaint();
			}
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
	
	
	class SearchListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(frame.getContentPane(),"searchPanel");
			frame.repaint();
		}
		
	}
	
	
	class DeleteListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(frame.getContentPane(),"deletePanel");
			frame.repaint();
		}
		
	}
	
	
	class SaveRecipeListener implements ActionListener {
		private GUI gui;
		public SaveRecipeListener(GUI gui) {
			this.gui = gui;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String recipeName = addRecipePanel.getRecipeName();
			String recipeDesc = addRecipePanel.getRecipeDescription();
			String recipeIng = addRecipePanel.getRecipeIngredients();
			String recipeInst = addRecipePanel.getRecipeInstructions();
			String recipeImgPath = addRecipePanel.getImagePath();
			if (!initDone) {
				setupNewRecipe();
				
				IRecipe nrecipe = new Recipe(); // This will move to the Recipe Factory
				nrecipe.setName(recipeName);
				nrecipe.setDescription(recipeDesc);
				//nrecipe.setImage(new ImageIcon("pictures/pizza_resize.png").getImage());
				nrecipe.setImagePath(recipeImgPath);
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
				gui.writeToFile(nrecipe);
				
				recipePanel.setRecipes(recipeCol);
				//makes sure the recipe panel is initialized
				recipePanel.setinitDone(initDone);
				frame.repaint();
			}
			else {
				IRecipe nrecipe = new Recipe(); // This will move to the Recipe Factory
				nrecipe.setName(recipeName);
				nrecipe.setDescription(recipeDesc);
				//nrecipe.setImage(new ImageIcon("pictures/pizza_resize.png").getImage());
				nrecipe.setImagePath(recipeImgPath);
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
				gui.writeToFile(nrecipe);
				
				recipePanel.setRecipes(recipeCol);
			}
			
			
			cardLayout.show(frame.getContentPane(),"recipePanel");
			frame.repaint();
		}
		
	}
	
	/*
	 * new recipe button event handling
	 */
	class ShowNextRecipeListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			//start new recipe
			if (!initDone) {
				setupNewRecipe();
				recipePanel.setRecipes(recipeCol);
				recipePanel.setMessage(message);
				recipePanel.setinitDone(initDone);			
				
				frame.repaint();
			}
			else {
				recipeCol.next();
				frame.repaint();
			}
		}
	}
	
	class ShowPreviousRecipeListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			//start new recipe
			if (!initDone) {
				setupNewRecipe();
				recipePanel.setRecipes(recipeCol);
				recipePanel.setMessage(message);
				recipePanel.setinitDone(initDone);			
				
				frame.repaint();
			}
			else {
				recipeCol.last();
				frame.repaint();
			}
		}
	}
	
	
	class ShowPreviousStepListener implements ActionListener { 
		
		public void actionPerformed(ActionEvent event) {
			showStepsPanel.previousIndex();
			frame.repaint();
			
		}
	}
	
	class ShowNextStepListener implements ActionListener { 
		
		public void actionPerformed(ActionEvent event) {
			showStepsPanel.nextIndex();
			frame.repaint();
			
		}
	}
	
	class ShowStepsListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			//start new recipe
			if (!initDone) {
				setupNewRecipe();
							
			}
			message = "Step By Step Recipe";
			showStepsPanel.setRecipes(recipeCol);
			showStepsPanel.setMessage(message);
			showStepsPanel.setinitDone(initDone);
			cardLayout.show(frame.getContentPane(),"showStepsPanel");
			frame.repaint();
		}
	}
	
	private void searchRecipes()
	{
		String recipeName = searchPanel.getRecipeName().toLowerCase();
		String recipeDesc = searchPanel.getRecipeDescription().toLowerCase();
		String recipeIng = searchPanel.getRecipeIngredients().toLowerCase();
		String recipeInst = searchPanel.getRecipeInstructions().toLowerCase();
		searchResultCol.clear();
		if (!initDone) {
			setupNewRecipe();
		}
		for (IRecipe recipe : recipeCol.getRecipes() )
		{
			if(!recipeName.isEmpty() && recipe.getName().toLowerCase().contains(recipeName)|| !recipeDesc.isEmpty() && recipe.getDescription().toLowerCase().contains(recipeDesc)) 
			{
				searchResultCol.add(recipe);
			}
			else if(!recipeIng.isEmpty() )
			{	
				for ( String r : recipe.getIngredients()) 
				{
					if(r.toLowerCase().contains(recipeIng))
					{
						searchResultCol.add(recipe);
						break;
					}
				}
			}
			else if (!recipeInst.isEmpty() ) 
			{
					
				for ( String r : recipe.getInstructions()) 
				{
					if(r.toLowerCase().contains(recipeIng))
					{
						searchResultCol.add(recipe);
						break;
					}
				}
			
			}
		}
	}
	
	private void deleteRecipes() {
		if (!initDone) {
			setupNewRecipe();
		}
		for (IRecipe recipe : deleteCol.getRecipes() ) {
			recipeCol.remove(recipe.getName());
		}
		
		boolean append = false;
		for (IRecipe recipe : recipeCol.getRecipes() ) {
			
			FileWriter write;
			try {
				write = new FileWriter(path,append);
				if(!append) {
					append = true;
				}
				PrintWriter printWriter = new PrintWriter(write);
				printWriter.println(recipe.getFullDescription());
				printWriter.println("Image Path: " + recipe.getImagePath());
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	private void searchDeleteRecipes()
	{
		String recipeName = deletePanel.getRecipeName().toLowerCase();
		
		deleteCol.clear();
		if (!initDone) {
			setupNewRecipe();
		}
		for (IRecipe recipe : recipeCol.getRecipes() )
		{
			if(!recipeName.isEmpty() && recipe.getName().toLowerCase().contains(recipeName)) 
			{
				deleteCol.add(recipe);
			}
			
		}
	}
	
	class SearchResultListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			
			cardLayout.show(frame.getContentPane(),"searchResultPanel");
			//start new recipe
			
			setupSearchResultPanel();
			searchRecipes();
			searchResultPanel.setRecipes(searchResultCol.getRecipes());
			
			searchResultPanel.setinitDone(initDone);
			frame.repaint();

		}
	}
	
	
	class ConfirmDeleteListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			deleteRecipes();
			recipePanel.setRecipes(recipeCol);
			cardLayout.show(frame.getContentPane(),"recipePanel");
			frame.repaint();

		}
	}
	
	
	class DeleteResultListener implements ActionListener { 
		public void actionPerformed(ActionEvent event) {
			
			
			cardLayout.show(frame.getContentPane(),"deleteResultPanel");
			//start new recipe
			
			setupDeleteResultPanel();
			searchDeleteRecipes();
			deleteResultPanel.setRecipes(deleteCol.getRecipes());
			
			deleteResultPanel.setinitDone(initDone);
			frame.repaint();

		}
	}
	
	
	
	
	/*
	 * new recipe button event handling
	 */
	class OpenImageListener implements ActionListener {
		private JPanel parent;
		private final JFileChooser fc = new JFileChooser();
		
		public OpenImageListener(JPanel parent)
		{
			this.parent = parent;
		}
		public void actionPerformed(ActionEvent event) {
			
			int retVal = fc.showOpenDialog(parent);
			if(retVal  == JFileChooser.APPROVE_OPTION) {
				File file  = fc.getSelectedFile();
				AddRecipePanel panel = (AddRecipePanel) parent;
				Component comp  = panel.getComponent("imagename");
				((JTextField)comp).setText(file.getPath());
				
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
	private IRecipeCollection allRecipes;
	
	
	
	//message
	String message = "";
	//game on
	boolean initDone;
	
	/*
	 * set recipes to be drawn on panel
	 */
	
	
	
	public void setRecipes(IRecipeCollection allRecipes) {
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		//draw recipes
		
		if (allRecipes != null) {
			IRecipe irecipe = allRecipes.current();
			Image image = irecipe.getImage();
			g.drawImage(image,100,150,this);
			int y = 275;
			StringBuilder sb = new StringBuilder();
			sb.append("Name: " + irecipe.getName());
			sb.append("\n");
			sb.append("Description: " + irecipe.getDescription());
			sb.append("\n");
			sb.append("Ingredients: " + irecipe.getIngredients().toString().replace("[", "").replace("]", ""));
			sb.append("\n");		
			sb.append("Instructions:");
			
			int count=0; 
			for( String str : irecipe.getInstructions() ) {
				sb.append(count+1);
				sb.append(")");
				sb.append(str);
				sb.append("\n");
				
				count++;
			}
			
			String recipe = sb.toString();
			
			for(String line: recipe.split("\n")) {
				int charw = g.getFontMetrics().stringWidth(line)/line.length();
				int maxChars  = GUI.WIDTH/(2*charw);
				if (maxChars > line.length())
				{
					maxChars = line.length();
					
				}
				int numLeft  = line.length();
				int start = 0;
				int end = maxChars;
				String []parts = line.split(":");
				if(parts.length > 1) {
					g.setFont(new Font("Arial", Font.BOLD, 20));
					g.drawString(parts[0]+":",100,y);
					end = maxChars - (parts[0].length()+1);
					if(end  < 0 ) continue; 
				}
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				int index = parts.length - 1;
				
				while (numLeft >  0 ) {
					
					g.drawString(parts[index].substring(start,end),300,y);
					numLeft -=maxChars;
					start = end;
					end = start + maxChars;
					if(end > parts[index].length())
					{
						end = parts[index].length();
						
					}
					y+=g.getFontMetrics().getHeight();
					
				}
				y+=g.getFontMetrics().getHeight();
				
				
				
				
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
	String message = "WELCOME TO KITCHEN CLOSEUP";
	
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,160,225);
		
	
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		
	
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
	
	public String getImagePath()
	{
		Component comp = componentMap.get("imagename");
		return  ((JTextField)comp).getText();
	}
	
	public Component getComponent(String name)
	{
		Component comp = componentMap.get(name);
		return comp;
	}
	
	
	
}


/*
* class used to draw the Add Recipe panel
*/
class SearchPanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//message
	String message = "Search for a Recipe";
	
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,250,100);
		
	
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
	
	
	public Component getComponent(String name)
	{
		Component comp = componentMap.get(name);
		return comp;
	}
	
}


class SearchResultPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//recipes
	private List<IRecipe> allRecipes;
	
	//message
	String message = "Search Result";
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		//draw recipes
		
		
		if (allRecipes != null) {
			for (int i=0; i < allRecipes.size(); i++) {
				Image image = allRecipes.get(i).getImage();
				g.drawImage(image,(100+i*400),300,this);
				int y = 175;
				StringBuilder sb = new StringBuilder();
				sb.append("Name: " + allRecipes.get(i).getName());
				sb.append("\n");
				sb.append("Description: " + allRecipes.get(i).getDescription());
				String recipe = sb.toString();
				for(String line: recipe.split("\n")) {
					g.drawString(line,100+i*400,y+=g.getFontMetrics().getHeight());
				}
			}	
		}
	
	}
		
}	


class DeletePanel extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//message
	String message = "Delete a Recipe";
	
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		
	
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
	
	
	public Component getComponent(String name)
	{
		Component comp = componentMap.get(name);
		return comp;
	}
	
}

class DeleteResultPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//recipes
	private List<IRecipe> allRecipes;
	
	//message
	String message = "Search Result - For Delete";
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		//draw recipes
		
		
		if (allRecipes != null) {
			for (int i=0; i < allRecipes.size(); i++) {
				Image image = allRecipes.get(i).getImage();
				g.drawImage(image,(100+i*400),300,this);
				int y = 175;
				StringBuilder sb = new StringBuilder();
				sb.append("Name: " + allRecipes.get(i).getName());
				sb.append("\n");
				sb.append("Description: " + allRecipes.get(i).getDescription());
				String recipe = sb.toString();
				for(String line: recipe.split("\n")) {
					g.drawString(line,100+i*400,y+=g.getFontMetrics().getHeight());
				}
			}	
		}
	
	}
		
}	



class ShowStepsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	//recipes
	private IRecipeCollection allRecipes;
	
	
	
	//message
	String message = "Step by Step Recipe";
	//game on
	boolean initDone;
	
	private int index;
	
	public void nextIndex() {
		this.index++;
		int size = this.allRecipes.current().getInstructions().size();
		if(this.index > size - 1) {
			this.index= size-1;
		}
	}
	
	public void previousIndex() {
		this.index--;
		if (this.index < 0)
		{
			this.index = 0;
		}
	}
	
	/*
	 * set recipes to be drawn on panel
	 */
	
	
	
	public void setRecipes(IRecipeCollection allRecipes) {
		index = 0;
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
		g.setColor(new Color(1.0f, 1.0f, 1.0f));
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		//draw message
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0.0f, 0.0f, 0.0f));
		g.drawString(message,300,100);
		//draw recipes
		
		
		
		
		if (allRecipes != null) {
			IRecipe irecipe = allRecipes.current();
			Image image = irecipe.getImage();
			g.drawImage(image,100,150,this);
			int y = 275;
			StringBuilder sb = new StringBuilder();
			sb.append("Name: " + irecipe.getName());
			sb.append("\n");
			sb.append("Description: " + irecipe.getDescription());
			sb.append("\n");
			sb.append("Ingredients: " + irecipe.getIngredients().toString().replace("[", "").replace("]", ""));
			sb.append("\n");		
			sb.append("Instructions:");
			
			int count=0; 
			for( String str : irecipe.getInstructions() ) {
				if(count <= index)
				{
					sb.append(count+1);
					sb.append(")");
					sb.append(str);
					sb.append("\n");
				}
				count++;
			}
			String recipe = sb.toString();
			
			for(String line: recipe.split("\n")) {
				int charw = g.getFontMetrics().stringWidth(line)/line.length();
				int maxChars  = GUI.WIDTH/(2*charw);
				if (maxChars > line.length())
				{
					maxChars = line.length();
				}
				int numLeft  = line.length();
				int start = 0;
				int end = maxChars;
				String []parts = line.split(":");
				if(parts.length > 1) {
					g.setFont(new Font("Arial", Font.BOLD, 20));
					g.drawString(parts[0]+":",100,y);
					end = maxChars - (parts[0].length()+1);
					if(end  < 0 ) continue; 
				}
				g.setFont(new Font("Arial", Font.PLAIN, 20));
				int index = parts.length - 1;
				
				while (numLeft >  0 ) {
					
					g.drawString(parts[index].substring(start,end),300,y);
					numLeft -=maxChars;
					start = end;
					end = start + maxChars;
					if(end > parts[index].length())
					{
						end = parts[index].length();
						
					}
					y+=g.getFontMetrics().getHeight();
				}
				y+=g.getFontMetrics().getHeight();
			}	
				
		}
	
	}
	
	
	
}	