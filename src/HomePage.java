import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("ChefMate - Home");

        // Create VBox layout with 20px spacing
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT); // Align content to the left

        // Title of the page
        Label title = new Label("Welcome to ChefMate!");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");
        title.setMaxWidth(Double.MAX_VALUE); // Ensure the title takes full width

        // About section
        Label about = new Label("""
                ChefMate is your ultimate culinary companion!
                Explore a world of delicious recipes across global cuisines, 
                get personalized dietary plans for a healthy lifestyle, 
                and order fresh groceries & essential cooking ingredients 
                directly from your recipe list — all at your fingertips.
                Whether you're a home chef or a curious foodie,
                ChefMate brings the joy of cooking into your home.
            """);
        about.setWrapText(true);
        about.setStyle("-fx-text-fill: #FFFFFF;");

        // Why choose ChefMate section
        Label why = new Label("""
                Why Choose ChefMate?
                - Access to a wide variety of recipes from different cuisines.
                - Customizable dietary plans for a healthier lifestyle.
                - Quick and easy ordering of ingredients.
                - User-friendly interface to make cooking fun and easy.
                - Regular updates with new recipes and meal ideas.
            """);
        why.setWrapText(true);
        why.setStyle("-fx-text-fill: #FFFFFF;");

        // Contact Us section
        Label contact = new Label("""
                For any queries or support, feel free to contact us at:
                Email: support@chefmaterestaurant.com
                We're here to help!
            """);
        contact.setWrapText(true);
        contact.setStyle("-fx-text-fill: #FFFFFF;");

        // Create Back Button
        Button backButton = new Button("Back to Main Page");
        backButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: #000000;");
        backButton.setOnAction(e -> goToMainPage(stage));

        // Add all content to the layout
        layout.getChildren().addAll(title, about, why, contact, backButton);

        // Set background image
        BackgroundImage myBI = new BackgroundImage(
                new javafx.scene.image.Image("bg1.jpg", 700, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(myBI));

        // Set up the scene and show the stage
        Scene scene = new Scene(layout, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    // Method to switch to the Main Page
    private void goToMainPage(Stage stage) {
        MainPage mainPage = new MainPage();  // Instantiate MainPage
        stage.close();  // Close the current HomePage window
        Stage newStage = new Stage();
        mainPage.start(newStage);  // Open the MainPage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
