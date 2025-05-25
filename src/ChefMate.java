package JAVA_PACKAGE;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ChefMate extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        Tab homeTab = new Tab("Home", createHomePage());
        Tab recipesTab = new Tab("Recipes", createRecipesPage());
        Tab dietPlansTab = new Tab("Dietary Plans", createDietPlansPage());
        Tab orderTab = new Tab("Order", createOrderPage());

        tabPane.getTabs().addAll(homeTab, recipesTab, dietPlansTab, orderTab);

        Scene scene = new Scene(tabPane, 900, 600);
        primaryStage.setTitle("Healthy Cooking Website");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createHomePage() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        Label about = new Label("Welcome to Healthy Cooking! Your guide to nutritious and delicious recipes, " +
                "dietary plans for a healthy lifestyle, and easy ingredient shopping.");
        about.setWrapText(true);
        vbox.getChildren().add(about);
        return vbox;
    }

    private VBox createRecipesPage() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        ComboBox<String> cuisineComboBox = new ComboBox<>();
        cuisineComboBox.getItems().addAll("Italian", "Indian", "Mexican", "Chinese", "Thai", "American", "Mediterranean");

        TextArea recipesArea = new TextArea();
        recipesArea.setEditable(false);

        cuisineComboBox.setOnAction(e -> {
            String selectedCuisine = cuisineComboBox.getValue();
            recipesArea.setText(getRecipesByCuisine(selectedCuisine));
        });

        vbox.getChildren().addAll(new Label("Select Cuisine:"), cuisineComboBox, recipesArea);
        return vbox;
    }

    private String getRecipesByCuisine(String cuisine) {
        switch (cuisine) {
            case "Italian":
                return "1. Margherita Pizza\n2. Pasta Carbonara\n3. Risotto\n4. Bruschetta\n5. Tiramisu\n6. Lasagna\n7. Pesto Pasta\n8. Focaccia\n9. Gnocchi\n10. Minestrone Soup";
            case "Indian":
                return "1. Butter Chicken\n2. Paneer Tikka\n3. Biryani\n4. Dosa\n5. Samosa\n6. Chole Bhature\n7. Dal Makhani\n8. Rogan Josh\n9. Aloo Paratha\n10. Gulab Jamun";
            case "Mexican":
                return "1. Tacos\n2. Enchiladas\n3. Guacamole\n4. Quesadillas\n5. Nachos\n6. Churros\n7. Burritos\n8. Tamales\n9. Sopes\n10. Pozole";
            case "Chinese":
                return "1. Kung Pao Chicken\n2. Dumplings\n3. Sweet and Sour Pork\n4. Fried Rice\n5. Chow Mein\n6. Spring Rolls\n7. Hot Pot\n8. Mapo Tofu\n9. Peking Duck\n10. Baozi";
            case "Thai":
                return "1. Pad Thai\n2. Tom Yum Soup\n3. Green Curry\n4. Massaman Curry\n5. Som Tum\n6. Thai Fried Rice\n7. Mango Sticky Rice\n8. Satay\n9. Larb\n10. Red Curry";
            case "American":
                return "1. Burger\n2. Fried Chicken\n3. Apple Pie\n4. BBQ Ribs\n5. Mac and Cheese\n6. Pancakes\n7. Meatloaf\n8. Clam Chowder\n9. Hot Dogs\n10. Buffalo Wings";
            case "Mediterranean":
                return "1. Greek Salad\n2. Falafel\n3. Hummus\n4. Shawarma\n5. Moussaka\n6. Tabbouleh\n7. Dolma\n8. Pita Bread\n9. Spanakopita\n10. Ratatouille";
            default:
                return "Please select a cuisine.";
        }
    }

    private VBox createDietPlansPage() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        TextArea dietPlans = new TextArea();
        dietPlans.setEditable(false);
        dietPlans.setText("1. Mediterranean Diet\n2. DASH Diet\n3. Plant-Based Diet\n4. Paleo Diet\n5. Low-Carb Diet\n6. Intermittent Fasting\n7. Flexitarian Diet\n8. Keto Diet\n9. Vegan Diet\n10. Whole30 Program");
        vbox.getChildren().addAll(new Label("Healthy Lifestyle Diet Plans:"), dietPlans);
        return vbox;
    }

    private VBox createOrderPage() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        ListView<String> groceryList = new ListView<>();
        groceryList.getItems().addAll("Olive Oil", "Basil", "Tomatoes", "Garlic", "Chicken Breast", "Brown Rice", "Quinoa", "Avocados", "Oats", "Almonds", "Chickpeas", "Spinach", "Broccoli");

        TextField totalField = new TextField();
        totalField.setEditable(false);

        Button orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> totalField.setText("Total Amount: $" + (groceryList.getSelectionModel().getSelectedItems().size() * 5)));

        vbox.getChildren().addAll(new Label("Select Ingredients and Groceries:"), groceryList, orderButton, totalField);
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
