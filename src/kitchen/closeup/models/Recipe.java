package kitchen.closeup.models;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import kitchen.closeup.interfaces.IRecipe;

public class Recipe implements IRecipe {
	private String name;
	private String description;
	private List<String> ingredients;
	private List<String> instructions;
	private Image image;
	private String imagePath; 

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath.strip();
		this.setImage(new ImageIcon(this.imagePath).getImage());
	}

	public Recipe(){
		this.ingredients = new ArrayList<>();
		this.instructions = new ArrayList<>();
	}
	
	public Recipe(List<String> ingredients, List<String> instructions){
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	public List<String> getIngredients(){
		return this.ingredients;
	}

	public List<String> getInstructions(){
		return this.instructions;
	}

	//Call the following four functions in main.java when initializing a recipe
	public void setName(String name){
		this.name = name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setIngredients(List<String> ingredients){
		this.ingredients = ingredients;
	}

	public void setInstructions(List<String> instructions){
		this.instructions = instructions;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append("\n");
		sb.append("Description: " + description);
		sb.append("\n");
		sb.append("Ingredients: " + ingredients.toString().replace("[", "").replace("]", ""));
		sb.append("\n");
		sb.append("Instructions: " + instructions.toString().replace("[", "").replace("]", ""));
		/*sb.append("\n");
		sb.append("Image Path: " + imagePath);*/
		
		return sb.toString();
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public String getFullDescription() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public void setImage(Image image) {
		// TODO Auto-generated method stub
		this.image = image;
		
	}


}