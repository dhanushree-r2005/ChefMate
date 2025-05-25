import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class LoginPage extends Application {

    // Connect to the database
    private Connection connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chefmate", "root", "leela@2006");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chef Mate - Login");

        Label nameLabel = new Label("Enter Your Name:");
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Your Name");
        nameField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");

        Label emailLabel = new Label("Enter Your Email:");
        emailLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("Your Email");
        emailField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");

        Button loginBtn = new Button("Login");
        loginBtn.setStyle("-fx-background-color: #81b29a; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-radius: 8px;");

        loginBtn.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && email.contains("@")) {
                if (validateUser(name, email)) {
                    MainPage mainPage = new MainPage();
                    Stage mainStage = new Stage();
                    mainPage.start(mainStage);
                    primaryStage.close();
                } else {
                    System.out.println("Invalid login: user not found!");
                    emailField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
                    nameField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
                }
            } else {
                emailField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
                nameField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
            }
        });

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-radius: 8px;");
        signUpBtn.setOnAction(e -> showSignUpForm());

        VBox layout = new VBox(15, nameLabel, nameField, emailLabel, emailField, loginBtn, signUpBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        String imagePath = getClass().getResource("/background.jpg").toExternalForm();
        layout.setStyle(
                "-fx-background-image: url('" + imagePath + "');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;"
        );

        Scene scene = new Scene(layout, 350, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Validate user login credentials
    private boolean validateUser(String name, String email) {
        try (Connection conn = connectDatabase()) {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE name = ? AND email = ?");
                stmt.setString(1, name);
                stmt.setString(2, email);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Show the sign-up form
    private void showSignUpForm() {
        Stage signUpStage = new Stage();
        signUpStage.setTitle("Recipe World - Sign Up");

        Label signUpLabel = new Label("Sign Up");
        signUpLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        TextField signUpNameField = new TextField();
        signUpNameField.setPromptText("Your Name");
        signUpNameField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");

        TextField signUpEmailField = new TextField();
        signUpEmailField.setPromptText("Your Email");
        signUpEmailField.setStyle("-fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");

        Button signUpSubmitBtn = new Button("Submit");
        signUpSubmitBtn.setStyle("-fx-background-color: #81b29a; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-radius: 8px;");
        signUpSubmitBtn.setOnAction(e -> {
            String name = signUpNameField.getText();
            String email = signUpEmailField.getText();

            if (!name.isEmpty() && email.contains("@")) {
                if (insertUser(name, email)) {
                    System.out.println("User signed up: " + name + ", " + email);
                    signUpStage.close();
                } else {
                    System.out.println("User already exists or error.");
                }
            } else {
                signUpEmailField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
                signUpNameField.setStyle("-fx-border-color: red; -fx-text-fill: white; -fx-prompt-text-fill: white; -fx-background-color: rgba(0,0,0,0.4);");
            }
        });

        VBox signUpLayout = new VBox(15, signUpLabel, signUpNameField, signUpEmailField, signUpSubmitBtn);
        signUpLayout.setAlignment(Pos.CENTER);
        signUpLayout.setPadding(new Insets(30));

        String imagePath = getClass().getResource("/background.jpg").toExternalForm();
        signUpLayout.setStyle(
                "-fx-background-image: url('" + imagePath + "');" +
                        "-fx-background-size: cover;" +
                        "-fx-background-position: center;" +
                        "-fx-background-repeat: no-repeat;"
        );

        Scene signUpScene = new Scene(signUpLayout, 300, 250);
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }

    // Insert new user into the database
    private boolean insertUser(String name, String email) {
        try (Connection conn = connectDatabase()) {
            if (conn != null) {
                // Check if the email already exists in the database
                PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) return false;  // Email already exists, prevent insertion

                // Insert the new user into the database
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)");
                insertStmt.setString(1, name);
                insertStmt.setString(2, email);
                int rows = insertStmt.executeUpdate();
                return rows > 0;  // If one or more rows are inserted, return true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Return false in case of any SQL exceptions
    }

    public static void main(String[] args) {
        launch(args);
    }
}
