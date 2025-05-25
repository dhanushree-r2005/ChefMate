import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

public class ChefMateRecipes extends Application {

    private final Map<String, List<String>> recipesByCuisine = Map.of(
            "Italian", List.of("Spaghetti Carbonara", "Lasagna", "Bruschetta", "Risotto", "Focaccia"),
            "Indian", List.of("Butter Chicken", "Paneer Tikka", "Biryani", "Dal Makhani", "Aloo Paratha"),
            "Chinese", List.of("Fried Rice", "Chow Mein", "Manchurian", "Kung Pao Chicken", "Spring Rolls"),
            "Mexican", List.of("Tacos", "Burritos", "Quesadilla", "Enchiladas", "Nachos")
    );

    private final Map<String, String> recipeInstructions = Map.ofEntries(
            Map.entry("Spaghetti Carbonara", "1. Boil pasta.\n2. Fry pancetta.\n3. Mix eggs & cheese.\n4. Combine with pasta off heat.\n5. Serve immediately."),
            Map.entry("Lasagna", "1. Layer pasta, meat sauce & cheese.\n2. Repeat layers.\n3. Bake at 180°C.\n4. Let it cool slightly.\n5. Slice and serve."),
            Map.entry("Bruschetta", "1. Toast bread.\n2. Rub garlic.\n3. Add diced tomatoes.\n4. Drizzle olive oil.\n5. Garnish with basil."),
            Map.entry("Risotto", "1. Sauté onions.\n2. Add Arborio rice.\n3. Pour broth gradually.\n4. Stir until creamy.\n5. Add butter & cheese."),
            Map.entry("Focaccia", "1. Prepare dough.\n2. Let rise.\n3. Dimple & oil the surface.\n4. Bake until golden.\n5. Slice and serve."),

            Map.entry("Butter Chicken", "1. Marinate chicken.\n2. Grill pieces.\n3. Cook tomato gravy.\n4. Add butter & cream.\n5. Combine and serve."),
            Map.entry("Paneer Tikka", "1. Marinate paneer.\n2. Skewer cubes.\n3. Grill or bake.\n4. Sprinkle lemon juice.\n5. Serve with chutney."),
            Map.entry("Biryani", "1. Marinate meat.\n2. Half-cook rice.\n3. Layer meat & rice.\n4. Slow-cook (dum).\n5. Garnish & serve."),
            Map.entry("Dal Makhani", "1. Soak lentils.\n2. Pressure cook.\n3. Prepare tomato masala.\n4. Add butter & cream.\n5. Simmer and serve."),
            Map.entry("Aloo Paratha", "1. Prepare potato filling.\n2. Stuff into dough.\n3. Roll flat.\n4. Cook on tawa.\n5. Serve with curd."),

            Map.entry("Fried Rice", "1. Cook rice.\n2. Stir-fry veggies.\n3. Add rice & soy sauce.\n4. Toss everything well.\n5. Serve hot."),
            Map.entry("Chow Mein", "1. Boil noodles.\n2. Stir-fry veggies.\n3. Add noodles.\n4. Pour sauces.\n5. Stir and serve."),
            Map.entry("Manchurian", "1. Make veggie balls.\n2. Deep fry them.\n3. Stir-fry with sauces.\n4. Add spring onions.\n5. Serve hot."),
            Map.entry("Kung Pao Chicken", "1. Marinate chicken.\n2. Fry peanuts.\n3. Stir-fry chicken.\n4. Add sauce mix.\n5. Combine and serve."),
            Map.entry("Spring Rolls", "1. Prepare filling.\n2. Wrap in sheets.\n3. Seal edges.\n4. Deep-fry until golden.\n5. Serve with sauce."),

            Map.entry("Tacos", "1. Warm tortillas.\n2. Prepare meat filling.\n3. Add toppings.\n4. Garnish with salsa.\n5. Serve folded."),
            Map.entry("Burritos", "1. Spread rice & beans.\n2. Add meat and cheese.\n3. Wrap tightly.\n4. Grill until crisp.\n5. Slice and serve."),
            Map.entry("Quesadilla", "1. Fill tortilla with cheese.\n2. Add toppings.\n3. Cover with another tortilla.\n4. Grill both sides.\n5. Slice into wedges."),
            Map.entry("Enchiladas", "1. Roll fillings in tortillas.\n2. Arrange in tray.\n3. Pour sauce on top.\n4. Bake at 180°C.\n5. Garnish and serve."),
            Map.entry("Nachos", "1. Spread chips on tray.\n2. Add cheese & toppings.\n3. Bake until cheese melts.\n4. Add salsa & jalapenos.\n5. Serve warm.")
    );

    private final ObservableList<String> recipeList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("ChefMate - Recipes");

        ComboBox<String> cuisineBox = new ComboBox<>();
        cuisineBox.getItems().addAll(recipesByCuisine.keySet());

        ListView<String> listView = new ListView<>(recipeList);
        TextArea recipeSteps = new TextArea();
        recipeSteps.setWrapText(true);
        recipeSteps.setEditable(false);
        recipeSteps.setPromptText("Select a recipe to see the steps...");

        cuisineBox.setOnAction(e -> {
            String selectedCuisine = cuisineBox.getValue();
            recipeList.setAll(recipesByCuisine.getOrDefault(selectedCuisine, List.of()));
            recipeSteps.clear();
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                recipeSteps.setText(recipeInstructions.getOrDefault(newVal, "Recipe steps not available."));
            }
        });

        VBox layout = new VBox(15, new Label("Select Cuisine:"), cuisineBox, listView, new Label("How to Make:"), recipeSteps);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}