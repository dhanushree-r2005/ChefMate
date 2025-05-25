import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderPage extends Application {

    private static class Ingredient {
        private final String name;
        private final int price;
        private final String imagePath;

        public Ingredient(String name, int price, String imagePath) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

    private final List<Ingredient> ingredients = List.of(
            new Ingredient("Tomato", 15, "Tomato.jpg"),
            new Ingredient("Onion", 10, "Onion.jpg"),
            new Ingredient("Chicken Breast", 200, "Chicken Breast.jpg"),
            new Ingredient("Paneer Cubes", 120, "Paneer Cubes.jpg"),
            new Ingredient("Olive Oil", 150, "Olive Oil.jpg"),
            new Ingredient("Rice", 50, "Rice.jpg"),
            new Ingredient("Pasta", 60, "Pasta.jpg"),
            new Ingredient("Garlic", 20, "Garlic.jpg"),
            new Ingredient("Butter", 75, "Butter.jpg"),
            new Ingredient("Basil", 30, "Basil.jpg"),
            new Ingredient("Cheese", 100, "Cheese.jpg"),
            new Ingredient("Bell Pepper", 40, "Bell Pepper.jpg"),
            new Ingredient("Spices Combo", 200, "Spices Combo.jpg")
    );

    private final Map<Ingredient, Integer> cart = new HashMap<>();
    private Stage primaryStage;
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("ChefMate - Shop Ingredients");

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chefmate", "root", "leela@2006");
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Connection Error");
            alert.setContentText("Failed to connect to the database.");
            alert.showAndWait();
            return;
        }

        showProductsPage();
    }

    private void showProductsPage() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("ChefMate Ingredient Shop");
        header.setFont(Font.font("Arial", 28));
        header.setStyle("-fx-font-weight: bold;");

        FlowPane productPane = new FlowPane();
        productPane.setPadding(new Insets(15));
        productPane.setHgap(20);
        productPane.setVgap(20);
        productPane.setAlignment(Pos.CENTER);

        for (Ingredient ingredient : ingredients) {
            VBox productCard = createProductCard(ingredient);
            productPane.getChildren().add(productCard);
        }

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(productPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);

        Button viewCartButton = createStyledButton("View Cart 🛒 (" + cart.size() + ")", "#2196F3");
        viewCartButton.setOnAction(e -> showCartPage());

        Button backButton = createStyledButton("Back to Main Page", "#FFB6C1");
        backButton.setOnAction(e -> goToMainPage(primaryStage));

        root.getChildren().addAll(header, scrollPane, viewCartButton, backButton);

        Scene scene = new Scene(root, 800, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createProductCard(Ingredient ingredient) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        card.setPrefSize(180, 250);

        ImageView imageView;
        try {
            imageView = new ImageView(new Image(ingredient.getImagePath()));
        } catch (Exception e) {
            imageView = new ImageView();  // fallback empty image
        }
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Label nameLabel = new Label(ingredient.getName());
        nameLabel.setFont(Font.font("Arial", 16));
        nameLabel.setStyle("-fx-font-weight: bold;");

        Label priceLabel = new Label("₹" + ingredient.getPrice());
        priceLabel.setFont(Font.font("Arial", 14));
        priceLabel.setStyle("-fx-text-fill: #666;");

        Button addButton = createStyledButton("Add to Cart", "#4CAF50");
        addButton.setOnAction(e -> {
            cart.put(ingredient, cart.getOrDefault(ingredient, 0) + 1);
            showProductsPage();  // Refresh to update cart count
        });

        card.getChildren().addAll(imageView, nameLabel, priceLabel, addButton);
        return card;
    }

    private void showCartPage() {
        VBox cartLayout = new VBox(20);
        cartLayout.setPadding(new Insets(20));
        cartLayout.setAlignment(Pos.TOP_CENTER);

        Label header = new Label("🛒 Your Cart");
        header.setFont(Font.font("Arial", 26));
        header.setStyle("-fx-font-weight: bold;");

        VBox cartItems = new VBox(10);
        cartItems.setPadding(new Insets(10));

        final int[] totalWrapper = new int[1];  // Using an array to wrap the total

        for (Map.Entry<Ingredient, Integer> entry : cart.entrySet()) {
            Ingredient ingredient = entry.getKey();
            int quantity = entry.getValue();
            int subtotal = ingredient.getPrice() * quantity;

            HBox itemRow = new HBox(20);
            itemRow.setAlignment(Pos.CENTER_LEFT);

            Label nameLabel = new Label(ingredient.getName() + " x" + quantity);
            nameLabel.setPrefWidth(250);

            Label priceLabel = new Label("₹" + subtotal);
            priceLabel.setStyle("-fx-font-weight: bold;");

            Button removeButton = createStyledButton("Remove", "#f44336");
            removeButton.setOnAction(e -> {
                cart.remove(ingredient);
                showCartPage();
            });

            itemRow.getChildren().addAll(nameLabel, priceLabel, removeButton);
            cartItems.getChildren().add(itemRow);
            totalWrapper[0] += subtotal;  // Modify the value inside the array
        }

        int total = totalWrapper[0];  // Final total after loop

        Label totalLabel = new Label("Total: ₹" + total);
        totalLabel.setFont(Font.font(20));
        totalLabel.setStyle("-fx-font-weight: bold;");

        Button checkoutButton = createStyledButton("Checkout", "#FF9800");
        checkoutButton.setOnAction(e -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Enter Delivery Details");
            dialog.setHeaderText("Please provide your Delivery Address and Phone Number.");

            ButtonType confirmButtonType = new ButtonType("Confirm Order", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField addressField = new TextField();
            addressField.setPromptText("Delivery Address");
            TextField phoneField = new TextField();
            phoneField.setPromptText("Phone Number");

            grid.add(new Label("Address:"), 0, 0);
            grid.add(addressField, 1, 0);
            grid.add(new Label("Phone Number:"), 0, 1);
            grid.add(phoneField, 1, 1);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == confirmButtonType) {
                    return new Pair<>(addressField.getText(), phoneField.getText());
                }
                return null;
            });

            dialog.showAndWait().ifPresent(details -> {
                String address = details.getKey().trim();
                String phone = details.getValue().trim();

                if (!address.isEmpty() && !phone.isEmpty()) {
                    // Validate phone number format
                    if (phone.matches("\\d{10}")) {  // Ensures phone number is exactly 10 digits
                        insertOrder(address, phone, total);
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Invalid Phone Number");
                        errorAlert.setHeaderText("Phone number must be 10 digits.");
                        errorAlert.showAndWait();
                    }
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Missing Details");
                    errorAlert.setHeaderText("Please fill in both address and phone number.");
                    errorAlert.showAndWait();
                }
            });
        });

        // Back to Shopping Button
        Button backToShoppingButton = createStyledButton("Back to Shopping", "#4CAF50");
        backToShoppingButton.setOnAction(e -> showProductsPage());

        cartLayout.getChildren().addAll(header, cartItems, totalLabel, checkoutButton, backToShoppingButton);

        Scene scene = new Scene(cartLayout, 800, 600);
        primaryStage.setScene(scene);
    }

    private void insertOrder(String address, String phone, int totalAmount) {
        String insertQuery = "INSERT INTO orders (delivery_address, phone_number, total_amount, order_date) VALUES (?, ?, ?, NOW())";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, address);
            statement.setString(2, phone);
            statement.setInt(3, totalAmount);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Order Confirmation");
                successAlert.setHeaderText("Order Placed Successfully!");
                successAlert.setContentText("Your order has been placed. We will deliver it soon.");
                successAlert.showAndWait();
                cart.clear();  // Clear the cart after successful order
                showProductsPage();  // Return to the products page
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Order Failed");
                errorAlert.setHeaderText("Failed to place your order. Please try again.");
                errorAlert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Database Error");
            errorAlert.setContentText("Failed to insert the order into the database.");
            errorAlert.showAndWait();
        }
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        return button;
    }

    private void goToMainPage(Stage primaryStage) {
        // Logic to go back to the main page of the application
    }

    public static void main(String[] args) {
        launch(args);
    }
}
