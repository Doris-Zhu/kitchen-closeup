package kitchen.closeup.models;

import java.util.ArrayList;
import java.util.List;

import kitchen.closeup.interfaces.IRecipe;
import kitchen.closeup.interfaces.IRecipeCollection;

public class RecipeCollection  implements IRecipeCollection{
	private List<IRecipe> recipes;
	private int index;
	
	public RecipeCollection() {
		this.recipes = new ArrayList<>();
		this.index = 0;
	}
	
	public RecipeCollection(List<IRecipe> recipes) {
		this.recipes = recipes;
		this.index = 0;
	}

	@Override
	public IRecipe next() {
		// TODO Auto-generated method stub
		if(recipes.size() == 0) {
			return null;
		}
		if(this.index < recipes.size() - 1) {
			this.index++;
			return recipes.get(index);
			
		}
		return null;
	}

	@Override
	public IRecipe last() {
		if(recipes.size() == 0) {
			return null;
		}
	
		// TODO Auto-generated method stub
		this.index = (this.recipes.size() -1);
		return this.recipes.get(index);
	}

	@Override
	public void add(IRecipe recipe) {
		// TODO Auto-generated method stub
		this.recipes.add(recipe);
		
	}

	@Override
	public void remove(String name) {
		// TODO Auto-generated method stub
		if(recipes.size() == 0) {
			return;
		}
		for (IRecipe recipe  : this.recipes ) {
			if(recipe.getName().equals(name)) {
				this.recipes.remove(name);
				this.index=0;
				break;
			}
		}
		
		
	}

	@Override
	public IRecipe find(String name) {
		// TODO Auto-generated method stub
		if(recipes.size() == 0) {
			return null;
		}
		for (IRecipe recipe  : this.recipes ) {
			if(recipe.getName().equals(name)) {
				return recipe;
			}
		}
		return null;
	}

	@Override
	public List<IRecipe> getRecipes() {
		// TODO Auto-generated method stub
		return this.recipes;
	}

}
