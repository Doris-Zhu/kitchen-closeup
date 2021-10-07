import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Main{
	public static void main(String[] args){
        chooseFunction();
	}
    private static void chooseFunction() {
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        while (running) {
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println("| Welcome to the recipe book|");
            System.out.println("-----------------------------");
            System.out.println("Please choose what to do next: ");
            System.out.println("1. Add a recipe");
            System.out.println("2. Retrieve a recipe");
            System.out.println("3. Quit");
            System.out.print("Your choice: ");
            switch (scan.nextInt()) {
                case 1: {
                    //addRecipe();
                    break;
                }
                case 2: {
                    retrieveRecipe();
                    break;
                }
                case 3: {
                    running = false;
                    break;
                }
                default: {
                	System.out.println("Invalid option.");
                	break;
                }
            }
        }
    }

//    private static void addRecipe(){

//    }
    private static void retrieveRecipe(){
    	Scanner scan = new Scanner(System.in);
    	while(true){
    		System.out.print("Please enter the file name of the recipe: ");
    		String path = scan.nextLine();
    		if(path.equals("")){
    			break;
    		}
    		else{
    			Recipe recipe = readFile(path);
    			System.out.println("Here is the recipe you want: ");
    			System.out.println(recipe);
    		}
    	}

    }
    public static Recipe readFile(String path){
    	Recipe target = new Recipe();
    	try {
    		FileReader fRead = new FileReader(path);
            BufferedReader bRead = new BufferedReader(fRead);
            target.setName(bRead.readLine());
            target.setDescription(bRead.readLine());
            String[] ingre = bRead.readLine().split(",");
            String[] instr = bRead.readLine().split(",");
            ArrayList<String> ingredients = new ArrayList<String>();
            ArrayList<String> instructions = new ArrayList<String>();
            for(String igd: ingre){
                ingredients.add(igd);
            }
            for(String im: instr){
                instructions.add(im);
            }
            target.setIngredients(ingredients);
            target.setInstructions(instructions);
            bRead.close();
            fRead.close();
        } catch (FileNotFoundException e) {
            System.out.printf("Error: file %s not found.\n", path);
            System.exit(1);
        } catch (IOException e) {
            System.out.printf("Error: failed to read %s.\n", path);
            System.exit(1);
        }
        return target;
    	
    }
}