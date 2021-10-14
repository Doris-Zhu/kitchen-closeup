package kitchen.closeup.interfaces;

import java.util.List;

public interface IRecipeCollection {
	public  IRecipe next();
	public  IRecipe last();
	public void add(IRecipe recipe);
	public void remove(String name);
	public IRecipe find(String name);
	public List<IRecipe> getRecipes();
	
	

}
