import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class DietaryPlansPage extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ChefMate - Dietary Plans");

        // Main root container
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f4e1c1;");  // Sandal background

        // Create the header label
        Label header = new Label("Explore Healthy Dietary Plans 🍏");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        // Create a VBox container for the dietary plans
        VBox plansContainer = new VBox(15);
        plansContainer.setPadding(new Insets(10));
        plansContainer.setAlignment(Pos.TOP_CENTER);

        // Define the dietary plans
        Map<String, String> plans = new LinkedHashMap<>();
        plans.put("Keto Diet", "A low-carb, high-fat diet designed to help the body burn fat more efficiently for energy.\n\nMore Info: The keto diet forces your body into a state of ketosis, where fat is burned for fuel instead of carbs. It helps with rapid weight loss and improved energy levels.");
        plans.put("Mediterranean Diet", "Focuses on whole grains, fresh vegetables, olive oil, and lean proteins to support heart health.\n\nMore Info: The Mediterranean diet emphasizes balanced meals rich in Omega-3 fatty acids and antioxidants that support cardiovascular health and longevity.");
        plans.put("Intermittent Fasting", "A timed approach to eating that cycles between fasting and eating periods for metabolic benefits.\n\nMore Info: This method improves insulin sensitivity, boosts metabolism, and promotes fat burning.");
        plans.put("DASH Diet", "Designed to prevent hypertension by focusing on nutrient-rich foods and reducing sodium.\n\nMore Info: DASH stands for Dietary Approaches to Stop Hypertension, emphasizing fruits, vegetables, and whole grains.");
        plans.put("Vegan Diet", "Eliminates animal products entirely and focuses on plant-based foods rich in fiber and antioxidants.\n\nMore Info: A vegan diet can lower cholesterol, aid in weight loss, and reduce the risk of chronic diseases.");
        plans.put("Paleo Diet", "Inspired by early human diets, it promotes whole foods, lean meats, fish, fruits, vegetables, and nuts.\n\nMore Info: The Paleo diet avoids processed foods, grains, and dairy, aligning more with hunter-gatherer nutritional patterns.");
        plans.put("Low Carb Diet", "A flexible plan to reduce carbohydrate intake for better blood sugar control and weight loss.\n\nMore Info: Helps prevent insulin spikes and encourages your body to burn fat for fuel.");
        plans.put("Flexitarian Diet", "A mostly plant-based diet but allows for occasional meat and fish.\n\nMore Info: This diet offers flexibility while still focusing on plant-based health benefits.");
        plans.put("Gluten-Free Diet", "Eliminates gluten to help those with celiac disease or gluten sensitivity.\n\nMore Info: Can improve digestion and energy levels if you’re gluten intolerant.");
        plans.put("Whole30 Diet", "A 30-day reset to eliminate sugar, grains, dairy, and legumes, focusing on whole foods.\n\nMore Info: Helps identify food sensitivities and resets unhealthy eating habits.");

        // Add dietary plan cards
        for (Map.Entry<String, String> plan : plans.entrySet()) {
            Label title = new Label(plan.getKey());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #800000; -fx-cursor: hand;");

            Label desc = new Label(plan.getValue().split("\n\n")[0]); // short preview
            desc.setWrapText(true);
            desc.setStyle("-fx-text-fill: #4e4e4e;");

            VBox card = new VBox(8, title, desc);
            card.setPadding(new Insets(12));
            card.setStyle(
                    "-fx-background-color: #f4e1c1; " +
                            "-fx-border-color: #800000; " +
                            "-fx-border-radius: 8px; " +
                            "-fx-background-radius: 8px; " +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5,0,0,2);"
            );

            // Add click action to each title
            title.setOnMouseClicked(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(plan.getKey());
                alert.setHeaderText(plan.getKey() + " Details");
                alert.setContentText(plan.getValue());
                alert.showAndWait();
            });

            plansContainer.getChildren().add(card);
        }

        // Scroll pane for dietary plans
        ScrollPane scrollPane = new ScrollPane(plansContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);
        scrollPane.setStyle("-fx-background: #f4e1c1;");

        // Footer label
        Label footer = new Label("ChefMate - Eat Well, Live Well!");
        footer.setStyle("-fx-text-fill: #555555; -fx-font-size: 12px;");

        // Back button at the bottom of the page, centered
        Button backButton = new Button("Go Back To Main Page");
        backButton.setStyle("-fx-background-color: #800000; -fx-text-fill: white; -fx-font-size: 14px;");
        backButton.setOnAction(e -> goBack(stage));  // Action to go back

        VBox backButtonContainer = new VBox(backButton);
        backButtonContainer.setAlignment(Pos.CENTER);
        backButtonContainer.setPadding(new Insets(20));

        // Add all the components to the root layout
        root.getChildren().addAll(header, scrollPane, footer, backButtonContainer);

        // Create scene and display
        Scene scene = new Scene(root, 700, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Method to handle the back action
    private void goBack(Stage stage) {
        MainPage mainPage = new MainPage();  // Create a new instance of MainPage
        Stage newStage = new Stage();        // Create a new Stage
        mainPage.start(newStage);            // Launch MainPage on the new stage
        stage.close();                       // Close the current (DietaryPlansPage) stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
