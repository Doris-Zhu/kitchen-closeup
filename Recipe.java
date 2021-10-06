import java.util.ArrayList;

public class Recipe{
	private String name;
	private String description;
	private ArrayList<String> ingredients;
	private ArrayList<String> instructions;

	public Recipe(){
		this.ingredients = new ArrayList<>();
		this.instructions = new ArrayList<>();
	}

	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	public ArrayList<String> getIngredients(){
		return this.ingredients;
	}

	public ArrayList<String> getInstructions(){
		return this.instructions;
	}

	//Call the following four functions in main.java when initializing a recipe
	public void setName(String name){
		this.name = name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setIngredients(ArrayList<String> ingredients){
		this.ingredients = ingredients;
	}

	public void setInstructions(ArrayList<String> instructions){
		this.instructions = instructions;
	}

}