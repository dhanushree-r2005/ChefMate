import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainPage extends Application {

    private String username;

    public MainPage() {
        this.username = "Guest";
    }

    public MainPage(String username) {
        this.username = username;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chef Mate - Welcome");

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        // Set background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("background1.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );
        layout.setBackground(new Background(bgImage));

        // Welcome Label
        Label welcomeLabel = new Label("Welcome, " + username + "!");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #4a3f35; -fx-background-color: rgba(255,255,255,0.7); -fx-padding: 10px; -fx-background-radius: 8px;");

        // Buttons
        Button homeBtn = createStyledButton("🏠 Home");
        Button recipesBtn = createStyledButton("🍽 Recipes");
        Button dietPlansBtn = createStyledButton("🥗 Diet Plans");
        Button orderBtn = createStyledButton("🛒 Order Ingredients");
        Button backBtn = createBackButton("🔙 Back");

        // Button Actions
        homeBtn.setOnAction(e -> {
            HomePage homePage = new HomePage();
            Stage newStage = new Stage();
            homePage.start(newStage);
        });

        recipesBtn.setOnAction(e -> {
            RecipesPage recipesPage = new RecipesPage();
            Stage newStage = new Stage();
            recipesPage.start(newStage);
        });

        dietPlansBtn.setOnAction(e -> {
            DietaryPlansPage dietPlansPage = new DietaryPlansPage();
            Stage newStage = new Stage();
            dietPlansPage.start(newStage);
        });

        orderBtn.setOnAction(e -> {
            OrderPage orderPage = new OrderPage();
            Stage newStage = new Stage();
            orderPage.start(newStage);
        });

        backBtn.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            Stage newStage = new Stage();
            loginPage.start(newStage);
            primaryStage.close();
        });

        layout.getChildren().addAll(welcomeLabel, homeBtn, recipesBtn, dietPlansBtn, orderBtn, backBtn);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #f4e1d2;" +
                        "-fx-text-fill: #4a3f35;" +
                        "-fx-border-color: #f4c2c2;" +
                        "-fx-border-width: 2px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 10px 20px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-radius: 10px;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #e2c8b0;" +
                        "-fx-text-fill: #2d2926;" +
                        "-fx-border-color: #f4c2c2;" +
                        "-fx-border-width: 2px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 10px 20px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-radius: 10px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #f4e1d2;" +
                        "-fx-text-fill: #4a3f35;" +
                        "-fx-border-color: #f4c2c2;" +
                        "-fx-border-width: 2px;" +
                        "-fx-font-size: 16px;" +
                        "-fx-padding: 10px 20px;" +
                        "-fx-background-radius: 10px;" +
                        "-fx-border-radius: 10px;"
        ));
        return button;
    }

    private Button createBackButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #e07a5f;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8px 16px;" +
                        "-fx-background-radius: 8px;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #d00000;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8px 16px;" +
                        "-fx-background-radius: 8px;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #e07a5f;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 8px 16px;" +
                        "-fx-background-radius: 8px;"
        ));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
