import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
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
                    // retrieveRecipe();
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

    private static void addRecipe() {
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
            
            recipeWriter.write("Preparation Instructions: \n");
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

    // private static void retrieveRecipe(){

    // }
}