package kitchen.closeup.interfaces;

import java.awt.Image;
import java.util.List;

public interface IRecipe {
	public String getName();
	public String getDescription();
	public Image getImage();
	public List<String> getIngredients();
	public List<String> getInstructions();
	public String getFullDescription();
	
	
	public void setName(String name);
	public void setDescription(String description);
	public void setImage(Image image);
	public void setIngredients(List<String> ingredients);
	public void setInstructions(List<String> instructions);
	
}
