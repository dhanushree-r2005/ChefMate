import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class RecipesPage extends Application {

    private final Map<String, List<Recipe>> cuisineRecipes = new HashMap<>();

    public RecipesPage() {
        // Initialize recipes for multiple cuisines

        // Italian Cuisine
        cuisineRecipes.put("Italian", List.of(
                new Recipe("Spaghetti Carbonara", List.of(
                        "1. Boil pasta in salted water",
                        "2. Cook bacon in a pan until crispy",
                        "3. Mix eggs with grated cheese",
                        "4. Reserve some pasta water before draining",
                        "5. Toss pasta with bacon and some pasta water",
                        "6. Quickly mix in the egg and cheese mixture",
                        "7. Serve with extra cheese and black pepper"
                ), List.of(
                        new Ingredient("Pasta", 200),
                        new Ingredient("Bacon", 150),
                        new Ingredient("Eggs", 50)
                ), 600, "Protein, Carbs, Fat"),

                new Recipe("Lasagna", List.of(
                        "1. Boil lasagna sheets and drain",
                        "2. Prepare meat sauce with ground beef and tomato sauce",
                        "3. Layer sheets, sauce, and cheese",
                        "4. Repeat layers and top with cheese",
                        "5. Cover and bake",
                        "6. Uncover and bake again until golden",
                        "7. Let cool before serving"
                ), List.of(
                        new Ingredient("Lasagna Sheets", 150),
                        new Ingredient("Ground Beef", 250),
                        new Ingredient("Tomato Sauce", 100)
                ), 700, "Protein, Carbs, Fat"),

                new Recipe("Margherita Pizza", List.of(
                        "1. Preheat oven to 475°F (245°C)",
                        "2. Roll out pizza dough on a floured surface",
                        "3. Spread tomato sauce on the dough",
                        "4. Add fresh mozzarella and basil leaves",
                        "5. Drizzle with olive oil and bake until golden",
                        "6. Serve with extra basil on top"
                ), List.of(
                        new Ingredient("Pizza Dough", 200),
                        new Ingredient("Tomato Sauce", 100),
                        new Ingredient("Mozzarella", 150)
                ), 800, "Protein, Carbs, Fat"),

                new Recipe("Fettuccine Alfredo", List.of(
                        "1. Boil fettuccine pasta in salted water",
                        "2. Cook garlic in butter until fragrant",
                        "3. Add cream and Parmesan cheese to the pan",
                        "4. Toss pasta in the creamy sauce",
                        "5. Season with salt and pepper",
                        "6. Serve with more cheese and fresh parsley"
                ), List.of(
                        new Ingredient("Fettuccine", 200),
                        new Ingredient("Butter", 50),
                        new Ingredient("Cream", 100)
                ), 750, "Carbs, Protein, Fat"),

                new Recipe("Risotto", List.of(
                        "1. Heat broth and keep warm",
                        "2. Sauté onions and garlic in butter",
                        "3. Add rice and cook until translucent",
                        "4. Gradually add warm broth, stirring constantly",
                        "5. Continue adding broth until rice is cooked",
                        "6. Stir in Parmesan and season with salt and pepper",
                        "7. Serve hot"
                ), List.of(
                        new Ingredient("Arborio Rice", 200),
                        new Ingredient("Chicken Broth", 400),
                        new Ingredient("Parmesan", 50)
                ), 750, "Carbs, Protein, Fat")
        ));

        // Indian Cuisine
        cuisineRecipes.put("Indian", List.of(
                new Recipe("Butter Chicken", List.of(
                        "1. Marinate chicken",
                        "2. Grill chicken",
                        "3. Prepare butter chicken sauce",
                        "4. Add chicken to sauce",
                        "5. Simmer and garnish",
                        "6. Serve with naan or rice",
                        "7. Adjust seasoning before serving"
                ), List.of(
                        new Ingredient("Chicken", 300),
                        new Ingredient("Butter", 50),
                        new Ingredient("Cream", 100)
                ), 750, "Protein, Fat"),

                new Recipe("Paneer Tikka", List.of(
                        "1. Marinate paneer cubes in yogurt and spices",
                        "2. Grill the paneer cubes until golden",
                        "3. Serve with mint chutney and sliced onions"
                ), List.of(
                        new Ingredient("Paneer", 250),
                        new Ingredient("Yogurt", 100),
                        new Ingredient("Spices", 20)
                ), 500, "Protein, Fat"),

                new Recipe("Chicken Tikka Masala", List.of(
                        "1. Marinate chicken in yogurt and spices",
                        "2. Grill the chicken until cooked",
                        "3. Prepare a tomato-based masala sauce",
                        "4. Add grilled chicken to the sauce and simmer",
                        "5. Garnish with cilantro and serve with rice"
                ), List.of(
                        new Ingredient("Chicken", 300),
                        new Ingredient("Yogurt", 100),
                        new Ingredient("Tomato Paste", 100)
                ), 800, "Protein, Fat"),

                new Recipe("Aloo Gobi", List.of(
                        "1. Heat oil and fry cumin seeds",
                        "2. Add onions, garlic, and ginger",
                        "3. Add potatoes and cauliflower",
                        "4. Cook with spices until soft",
                        "5. Garnish with cilantro and serve"
                ), List.of(
                        new Ingredient("Potatoes", 200),
                        new Ingredient("Cauliflower", 200),
                        new Ingredient("Cumin", 5)
                ), 450, "Carbs, Fiber"),

                new Recipe("Palak Paneer", List.of(
                        "1. Sauté onions, garlic, and ginger",
                        "2. Add spinach and cook until wilted",
                        "3. Blend spinach into a smooth paste",
                        "4. Add paneer cubes and cook in the spinach gravy",
                        "5. Garnish with cream and serve"
                ), List.of(
                        new Ingredient("Spinach", 300),
                        new Ingredient("Paneer", 200),
                        new Ingredient("Cream", 50)
                ), 600, "Protein, Carbs, Fat")
        ));

        // Mexican Cuisine
        cuisineRecipes.put("Mexican", List.of(
                new Recipe("Tacos", List.of(
                        "1. Cook ground beef with taco seasoning",
                        "2. Warm taco shells",
                        "3. Prepare toppings",
                        "4. Assemble tacos",
                        "5. Add toppings",
                        "6. Serve with lime wedges",
                        "7. Garnish with cilantro"
                ), List.of(
                        new Ingredient("Taco Shells", 100),
                        new Ingredient("Ground Beef", 200),
                        new Ingredient("Lettuce", 50)
                ), 450, "Protein, Carbs"),

                new Recipe("Burrito", List.of(
                        "1. Warm a large flour tortilla",
                        "2. Fill with seasoned rice, beans, and protein of choice",
                        "3. Add cheese, salsa, and guacamole",
                        "4. Roll up and serve"
                ), List.of(
                        new Ingredient("Flour Tortilla", 100),
                        new Ingredient("Rice", 150),
                        new Ingredient("Beans", 100)
                ), 700, "Carbs, Protein"),

                new Recipe("Quesadilla", List.of(
                        "1. Heat a tortilla in a pan",
                        "2. Add cheese and any filling (e.g., chicken, veggies)",
                        "3. Fold the tortilla and cook until golden brown",
                        "4. Serve with sour cream or salsa"
                ), List.of(
                        new Ingredient("Tortilla", 100),
                        new Ingredient("Cheese", 150),
                        new Ingredient("Chicken", 200)
                ), 600, "Carbs, Protein, Fat"),

                new Recipe("Enchiladas", List.of(
                        "1. Fill tortillas with chicken and cheese",
                        "2. Roll up the tortillas and place in a baking dish",
                        "3. Pour enchilada sauce over the tortillas",
                        "4. Bake until bubbly and golden",
                        "5. Garnish with cilantro and serve"
                ), List.of(
                        new Ingredient("Tortillas", 150),
                        new Ingredient("Chicken", 250),
                        new Ingredient("Enchilada Sauce", 100)
                ), 700, "Protein, Carbs"),

                new Recipe("Guacamole", List.of(
                        "1. Mash avocados",
                        "2. Add diced onions, tomatoes, and cilantro",
                        "3. Squeeze lime juice and season with salt",
                        "4. Serve with tortilla chips or tacos"
                ), List.of(
                        new Ingredient("Avocados", 200),
                        new Ingredient("Tomatoes", 50),
                        new Ingredient("Cilantro", 10)
                ), 350, "Healthy Fats, Fiber")
        ));

        // Chinese Cuisine
        cuisineRecipes.put("Chinese", List.of(
                new Recipe("Fried Rice", List.of(
                        "1. Cook rice and let it cool",
                        "2. Heat oil in a wok and sauté onions and garlic",
                        "3. Add mixed vegetables and stir-fry",
                        "4. Push veggies aside and scramble eggs in the same wok",
                        "5. Add rice, soy sauce, and sesame oil, and stir-fry everything together",
                        "6. Garnish with spring onions and serve hot"
                ), List.of(
                        new Ingredient("Rice", 200),
                        new Ingredient("Vegetables", 150),
                        new Ingredient("Eggs", 50)
                ), 500, "Carbs, Protein, Fiber"),

                new Recipe("Chow Mein", List.of(
                        "1. Boil noodles and set aside",
                        "2. Stir-fry vegetables and protein of choice",
                        "3. Add boiled noodles to the wok",
                        "4. Toss everything with soy sauce and spices",
                        "5. Garnish with spring onions before serving"
                ), List.of(
                        new Ingredient("Noodles", 200),
                        new Ingredient("Vegetables", 100),
                        new Ingredient("Soy Sauce", 20)
                ), 600, "Carbs, Protein, Fiber"),

                new Recipe("Sweet and Sour Chicken", List.of(
                        "1. Cut chicken into bite-sized pieces and coat with cornstarch",
                        "2. Deep fry until crispy",
                        "3. Prepare sauce using ketchup, vinegar, sugar, and soy sauce",
                        "4. Stir-fry vegetables, add the sauce and fried chicken",
                        "5. Toss until evenly coated and serve hot"
                ), List.of(
                        new Ingredient("Chicken", 250),
                        new Ingredient("Bell Peppers", 100),
                        new Ingredient("Cornstarch", 50)
                ), 700, "Protein, Carbs, Fat"),

                new Recipe("Manchurian", List.of(
                        "1. Make vegetable or chicken balls and deep-fry",
                        "2. Prepare sauce with garlic, soy sauce, and other spices",
                        "3. Toss fried balls in sauce and garnish with spring onions",
                        "4. Serve hot with rice or noodles"
                ), List.of(
                        new Ingredient("Vegetables", 200),
                        new Ingredient("Soy Sauce", 20),
                        new Ingredient("Ginger Garlic Paste", 10)
                ), 600, "Carbs, Protein"),

                new Recipe("Spring Rolls", List.of(
                        "1. Prepare filling with vegetables and spices",
                        "2. Wrap the filling in spring roll wrappers",
                        "3. Deep fry until crispy and golden",
                        "4. Serve with dipping sauce"
                ), List.of(
                        new Ingredient("Spring Roll Wrappers", 50),
                        new Ingredient("Cabbage", 100),
                        new Ingredient("Carrots", 50)
                ), 450, "Carbs, Fiber")
        ));

        // American Cuisine
        cuisineRecipes.put("American", List.of(
                new Recipe("Burger", List.of(
                        "1. Prepare beef patties",
                        "2. Grill patties to desired doneness",
                        "3. Toast buns and assemble with toppings",
                        "4. Serve with fries or salad"
                ), List.of(
                        new Ingredient("Ground Beef", 200),
                        new Ingredient("Burger Buns", 100),
                        new Ingredient("Cheese", 50)
                ), 750, "Protein, Fat, Carbs"),

                new Recipe("Mac and Cheese", List.of(
                        "1. Boil macaroni pasta",
                        "2. Prepare cheese sauce with butter, flour, and milk",
                        "3. Combine pasta and cheese sauce",
                        "4. Bake with extra cheese on top until golden"
                ), List.of(
                        new Ingredient("Macaroni", 200),
                        new Ingredient("Cheddar Cheese", 150),
                        new Ingredient("Milk", 100)
                ), 850, "Carbs, Fat, Protein"),

                new Recipe("Hot Dog", List.of(
                        "1. Grill or boil hot dog sausages",
                        "2. Place sausages in buns and top with condiments",
                        "3. Serve with pickles and chips"
                ), List.of(
                        new Ingredient("Hot Dog Sausages", 150),
                        new Ingredient("Hot Dog Buns", 100),
                        new Ingredient("Mustard", 20)
                ), 450, "Protein, Carbs, Fat"),

                new Recipe("Pancakes", List.of(
                        "1. Mix flour, eggs, milk, and sugar",
                        "2. Pour batter onto a hot griddle",
                        "3. Cook until golden on both sides",
                        "4. Serve with syrup and butter"
                ), List.of(
                        new Ingredient("Flour", 150),
                        new Ingredient("Milk", 100),
                        new Ingredient("Eggs", 50)
                ), 500, "Carbs, Protein"),

                new Recipe("BBQ Ribs", List.of(
                        "1. Season ribs with BBQ rub",
                        "2. Cook ribs low and slow until tender",
                        "3. Glaze with BBQ sauce and grill for a crispy finish",
                        "4. Serve with coleslaw and cornbread"
                ), List.of(
                        new Ingredient("Pork Ribs", 500),
                        new Ingredient("BBQ Sauce", 100),
                        new Ingredient("Cornbread Mix", 200)
                ), 900, "Protein, Fat")
        ));
    }
    private final Map<String, String> recipeImages = new HashMap<>() {{
        put("Spaghetti Carbonara", "Spaghetti Carbonara.jpg");
        put("Lasagna", "Lasagna.jpg");
        put("Margherita Pizza", "Margherita Pizza.jpg");
        put("Fettuccine Alfredo", "Fettuccine Alfredo.jpg");
        put("Risotto", "Risotto.jpg");
        put("Butter Chicken", "Butter Chicken.jpg");
        put("Paneer Tikka", "Paneer Tikka.jpg");
        put("Chicken Tikka Masala", "Chicken Tikka Masala.jpg");
        put("Aloo Gobi", "Aloo Gobi.jpg");
        put("Palak Paneer", "Palak Paneer.jpg");
        put("Tacos", "Tacos.jpg");
        put("Burrito", "Burrito.jpg");
        put("Quesadilla", "Quesadilla.jpg");
        put("Enchiladas", "Enchiladas.jpg");
        put("Guacamole", "Guacamole.jpg");
        put("Fried Rice", "Fried Rice.jpg");
        put("Chow Mein", "Chow Mein.jpg");
        put("Sweet and Sour Chicken", "Sweet and Sour Chicken.jpg");
        put("Manchurian", "Manchurian.jpg");
        put("Spring Rolls", "Spring Rolls.jpg");
        put("Burger", "Burger.jpg");
        put("Mac and Cheese", "Mac and Cheese.jpg");
        put("Hot Dog", "Hot Dog.jpg");
        put("Pancakes", "Pancakes.jpg");
        put("BBQ Ribs", "BBQ Ribs.jpg");
    }};


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Recipes");

        ComboBox<String> cuisineComboBox = new ComboBox<>();
        cuisineComboBox.getItems().addAll("Italian", "Indian", "Mexican", "Chinese", "American");
        cuisineComboBox.setValue("Choose Cuisine");

        // Create the content VBox for recipes that will go inside the ScrollPane
        VBox recipesContent = new VBox(10);
        recipesContent.setAlignment(Pos.CENTER);
        recipesContent.setPrefWidth(580); // Set preferred width to match container

        // Create the ScrollPane and add the recipes content to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(recipesContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400); // Set a preferred height for the scroll pane
        scrollPane.setStyle("-fx-background-color: transparent;");

        cuisineComboBox.setOnAction(e -> updateRecipes(recipesContent, cuisineComboBox.getValue()));

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("bg3.jpg", 600, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT
        );

        // Create back button to return to main page
        Button backButton = createBackButton("Back to Main Page", primaryStage);

        // Create an HBox container to center the back button
        HBox backButtonContainer = new HBox();
        backButtonContainer.setAlignment(Pos.CENTER);
        backButtonContainer.getChildren().add(backButton);

        VBox mainLayout = new VBox(20);
        mainLayout.setBackground(new Background(backgroundImage));
        mainLayout.getChildren().addAll(
                createCenteredText("Choose a Cuisine:"),
                cuisineComboBox,
                createCenteredText("Recipes:"),
                scrollPane,  // Add the scrollPane instead of recipesLayout directly
                backButtonContainer
        );

        // Add padding to mainLayout for better appearance
        mainLayout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(mainLayout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateRecipes(VBox recipesLayout, String cuisine) {
        recipesLayout.getChildren().clear();

        recipesLayout.getChildren().add(createCenteredText(cuisine));
        for (Recipe recipe : cuisineRecipes.getOrDefault(cuisine, new ArrayList<>())) {
            addRecipeToLayout(recipesLayout, recipe);
        }
    }

    private Button createBackButton(String text, Stage currentStage) {
        Button backButton = new Button(text);
        backButton.setPrefWidth(200);
        backButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6px; -fx-font-size: 14px;");
        backButton.setOnAction(e -> {
            MainPage mainPage = new MainPage();  // Create a new instance of MainPage
            Stage newStage = new Stage();        // Create a new Stage
            mainPage.start(newStage);            // Launch MainPage on the new stage
            currentStage.close();                // Close the current (RecipesPage) stage
        });
        return backButton;
    }

    private void addRecipeToLayout(VBox recipesLayout, Recipe recipe) {
        Button viewRecipeButton = new Button("View Recipe");
        viewRecipeButton.setOnAction(e -> showRecipeDetails(recipe));
        viewRecipeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        VBox recipeBox = new VBox(5);
        recipeBox.setStyle("-fx-padding: 10px; -fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10px; -fx-alignment: center;");
        recipeBox.getChildren().addAll(
                createCenteredText(recipe.getName()),
                viewRecipeButton
        );

        // Add some margin around each recipe box
        VBox.setMargin(recipeBox, new javafx.geometry.Insets(5, 0, 5, 0));

        recipesLayout.getChildren().add(recipeBox);
    }

    private void showRecipeDetails(Recipe recipe) {
        Stage recipeDetailsStage = new Stage();
        recipeDetailsStage.setTitle(recipe.getName());

        // Content for the ScrollPane
        VBox detailsContent = new VBox(10);
        detailsContent.setStyle("-fx-alignment: center; -fx-padding: 10;");

        // Show image if available
        String imagePath = recipeImages.get(recipe.getName());
        if (imagePath != null) {
            try {
                ImageView recipeImageView = new ImageView(
                        new Image(getClass().getResourceAsStream(imagePath))
                );
                recipeImageView.setFitWidth(300);
                recipeImageView.setPreserveRatio(true);
                detailsContent.getChildren().add(recipeImageView);
            } catch (Exception e) {
                System.out.println("Image not found for: " + recipe.getName());
            }
        }

        // Add recipe details
        detailsContent.getChildren().addAll(
                createBoldCenteredText(recipe.getName()),
                createCenteredText("Ingredients:"),
                createCenteredText(formatIngredients(recipe.getIngredients())),
                createCenteredText("Steps:"),
                createCenteredText(String.join("\n", recipe.getSteps())),
                createCenteredText("Calories: " + recipe.getCalories()),
                createCenteredText("Nutrient Content: " + recipe.getNutrientContent())
        );

        // Create a ScrollPane to allow scrolling if content is too large
        ScrollPane detailsScrollPane = new ScrollPane(detailsContent);
        detailsScrollPane.setFitToWidth(true);
        detailsScrollPane.setStyle("-fx-background-color: transparent;");

        Scene scene = new Scene(detailsScrollPane, 400, 600);
        recipeDetailsStage.setScene(scene);
        recipeDetailsStage.show();
    }

    private String formatIngredients(List<Ingredient> ingredients) {
        StringBuilder formatted = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            formatted.append(ingredient.getName()).append(": ").append(ingredient.getQuantity()).append("g\n");
        }
        return formatted.toString();
    }

    private Text createCenteredText(String text) {
        Text centeredText = new Text(text);
        centeredText.setWrappingWidth(380); // text wraps nicely
        centeredText.setStyle("-fx-text-alignment: center; -fx-font-size: 14px;");
        return centeredText;
    }

    private Text createBoldCenteredText(String text) {
        Text centeredText = new Text(text);
        centeredText.setWrappingWidth(380);
        centeredText.setStyle("-fx-text-alignment: center; -fx-font-size: 18px; -fx-font-weight: bold;");
        return centeredText;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Recipe and Ingredient classes
    static class Recipe {
        private final String name;
        private final List<String> steps;
        private final List<Ingredient> ingredients;
        private final int calories;
        private final String nutrientContent;

        public Recipe(String name, List<String> steps, List<Ingredient> ingredients, int calories, String nutrientContent) {
            this.name = name;
            this.steps = steps;
            this.ingredients = ingredients;
            this.calories = calories;
            this.nutrientContent = nutrientContent;
        }

        public String getName() { return name; }
        public List<String> getSteps() { return steps; }
        public List<Ingredient> getIngredients() { return ingredients; }
        public int getCalories() { return calories; }
        public String getNutrientContent() { return nutrientContent; }
    }

    static class Ingredient {
        private final String name;
        private final int quantity;

        public Ingredient(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public int getQuantity() { return quantity; }
    }
}