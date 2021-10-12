import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
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
                    addRecipe();
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

    private static void addRecipe(){
        Scanner recipe = new Scanner(System.in);
        System.out.println("Enter the name of your recipe: ");
        String recipeName = recipe.nextLine();
        System.out.println("Enter a description of the recipe: ");
        String recipeDescr = recipe.nextLine();
        
        System.out.println("Enter the number of ingredients in your recipe: ");
        int numIngr = recipe.nextInt();
        recipe.nextLine();
        String[] ingredients = new String[numIngr];
        for (int i = 0; i < numIngr; i++) {
            int j = i + 1;
            System.out.println("Enter the name of ingredient " + j + ": ");
            String ingr = recipe.nextLine();
            ingredients[i] = ingr;
        }

        System.out.println("Enter the number of cooking steps in your recipe: ");
        int numSteps = recipe.nextInt();
        recipe.nextLine();
        String[] steps = new String[numSteps];
        for (int i = 0; i < numSteps; i++) {
            int j = i + 1;
            System.out.println("Enter cooking step " + j + ": ");
            String step = recipe.nextLine();
            steps[i] = step;
        }
        System.out.println("--------------------------------");

        // create file or make sure it is created
        // File recipes = new File("recipes.txt");
        // try {
        //     recipes.createNewFile();
        // } catch (IOException e) {
        //     System.out.println("An error occurred when opening file.");
        //     e.printStackTrace();
        // }

        //  write to file
        try {
            File file = new File("recipes");
            boolean dirCreated = file.mkdir();

            FileWriter recipeWriter = new FileWriter("recipes/" + recipeName + ".txt", true);
            recipeWriter.write("Recipe Name: " + recipeName + "\n");
            recipeWriter.write("Description: " + recipeDescr + "\n");
            
            recipeWriter.write("Ingredients: ");
            for (int i = 0; i < numIngr; i++) {
                recipeWriter.write(ingredients[i] + "//");
            }
            recipeWriter.write("\n");
            
            recipeWriter.write("Preparation Instructions: ");
            for (int j = 0; j < numSteps; j++) {
                recipeWriter.write(steps[j] + "//");
            }
            recipeWriter.write("\n\n");
            
            recipeWriter.close();
            System.out.println("Successfully saved your recipe.");
        } catch (IOException e) {
            System.out.println("An error occurred when writing to file.");
            e.printStackTrace();
        }
    }

    private static void retrieveRecipe(){
        Scanner scan = new Scanner(System.in);
        boolean boo = true;
        while(boo){
            ViewAllNames();
            System.out.print("Please enter the file name of the recipe(Add .txt after the name): ");
            String pre = "recipes/";
            String p = scan.nextLine();
            String path = pre + p;
            if(p.equals("")){
                break;
            }
            else{
                Recipe recipe = readFile(path);
                System.out.println("We found the recipe for you! Please choose the way you want to read it: ");
                System.out.println("1. Read the entire recipe.");
                System.out.println("2. Read the instruction one by one. ");
                System.out.print("Your choice: ");
                int num = scan.nextInt();
                if(num == 1){
                    System.out.println("Here is the recipe you want: ");
                    System.out.println(recipe);
                    break;
                }
                else if(num == 2){
                    System.out.println("You are reading the recipe " + recipe.name);
                    System.out.print("Please enter 's' to start reading the instructions and enter 'q' to quit > ");
                    String s = scan.next();
                    s = s.trim();
                    for(int i = 0; i < recipe.instructions.size(); i++){
                        if(s.equals("s")){
                            System.out.println(recipe.instructions.get(i));
                            System.out.print("Please enter 's' to start reading the instructions and enter 'q' to quit > ");
                            s = scan.next();
                            if(i == recipe.instructions.size()-1){
                                System.out.println("The last instruction is already the last one.");
                                boo = false;
                                break;
                            }
                            
                        }
                        else if(s.equals("q")){
                            boo = false;
                            break;
                        }
                        else{
                            System.out.print("Please enter 's' to start reading the instructions and enter 'q' to quit > ");
                            s = scan.next();
                            i--;
                        }
                    }
                }
            }
        }

        // create file or make sure it is created
        // File recipes = new File("recipes.txt");
        // try {
        //     recipes.createNewFile();
        // } catch (IOException e) {
        //     System.out.println("An error occurred when opening file.");
        //     e.printStackTrace();
        // }
    }

    private static ArrayList<String> getAllNames(){
        String dir = "./recipes";
        ArrayList<String> nameList = new ArrayList<String>();
       // try {
            File fileName = new File(dir);

            File[] fileList = fileName.listFiles();
            //ArrayList<String> nameList = new ArrayList<String>();

            for(File file: fileList){
                    nameList.add(file.getName());
            }
        //} catch (FileNotFoundException e) {
          //  System.out.printf("Error: file is not found.\n");
            //System.exit(1);
        //} catch (IOException e) {
         //   System.out.printf("Error: failed to read the file.\n");
           // System.exit(1);
        //}

        return nameList;
    }

    private static void ViewAllNames(){
        ArrayList<String> nameList = getAllNames();
        System.out.println("Here is the list of all the recipes. Please choose one to view: ");
        System.out.println(nameList.toString());
    }

    public static Recipe readFile(String path){
        Recipe target = new Recipe();
        try {
            FileReader fRead = new FileReader(path);
            BufferedReader bRead = new BufferedReader(fRead);
            String[] l_name = bRead.readLine().split(": ");
            String[] l_des = bRead.readLine().split(": ");
            target.setName(l_name[1]);
            target.setDescription(l_des[1]);
            String[] l_ingre = bRead.readLine().split(": ");
            String[] ingre = l_ingre[1].split("//");
            String[] l_instr = bRead.readLine().split(": ");
            String[] instr = l_instr[1].split("//");
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
