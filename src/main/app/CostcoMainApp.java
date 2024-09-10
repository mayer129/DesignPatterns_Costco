package app;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CostcoMainApp extends Application {

    private CostcoFacade facade = new CostcoFacade();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Costco Department Store");

        // Create the grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);  // Center the entire grid
        grid.setHgap(50);  // Increase horizontal spacing between buttons
        grid.setVgap(50);  // Increase vertical spacing between buttons
        grid.setPadding(new Insets(20, 20, 60, 20));  // Padding for top, right, bottom, left (added bottom padding)

        // Set window size (increased height to provide more space at the bottom)
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);  // Increased height

        // Set background color of the grid pane (main background)
        grid.setStyle("-fx-background-color: #f7f7f7;"); // Light gray background

        // Store title (aligned to center horizontally)
        Text storeTitle = new Text("Welcome to Costco!");
        storeTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 40));
        storeTitle.setStyle("-fx-fill: black;"); // Set title text to black (no shadow)

        // Set column span for the title and center it horizontally
        grid.add(storeTitle, 0, 0, 3, 1);
        GridPane.setHalignment(storeTitle, HPos.CENTER);  // Center title horizontally
        GridPane.setMargin(storeTitle, new Insets(0, 0, 20, 0));  // Reduced margin below the title

        // Create department buttons with the specified colors and larger text size
        Button bakeryBtn = createDepartmentButton("Bakery Department", "#FF6F61"); // Soft Coral
        Button supportBtn = createDepartmentButton("Customer Support", "#D98880"); // Dusty Rose
        Button securityBtn = createDepartmentButton("Security Department", "#F7DC6F"); // Mustard Yellow
        Button wifiBtn = createDepartmentButton("WiFi Access", "#7DCEA0"); // Olive Green
        Button empManageBtn = createDepartmentButton("Employee Management", "#48C9B0"); // Teal Blue
        Button catalogBtn = createDepartmentButton("Product Catalog", "#85C1E9"); // Powder Blue
        Button flyweightDemoBtn = createDepartmentButton("Discount Demo", "#C39BD3"); // Lavender
        Button foodCourtBtn = createDepartmentButton("Food Court", "#A99A86"); // Warm Taupe
        Button iteratorDemoBtn = createDepartmentButton("Iterator Demo", "#2C3E50"); // Charcoal Grey

        // Add department buttons to the grid (3x3 layout)
        grid.add(bakeryBtn, 0, 1);          // Row 1, Col 1
        grid.add(supportBtn, 1, 1);         // Row 1, Col 2
        grid.add(securityBtn, 2, 1);        // Row 1, Col 3
        grid.add(wifiBtn, 0, 2);            // Row 2, Col 1
        grid.add(empManageBtn, 1, 2);       // Row 2, Col 2
        grid.add(catalogBtn, 2, 2);         // Row 2, Col 3
        grid.add(flyweightDemoBtn, 0, 3);   // Row 3, Col 1
        grid.add(foodCourtBtn, 1, 3);       // Row 3, Col 2
        grid.add(iteratorDemoBtn, 2, 3);    // Row 3, Col 3

        // Set button actions to open respective departments (new windows)
        bakeryBtn.setOnAction(event -> openBakeryWindow());
        supportBtn.setOnAction(event -> openSupportWindow());
        securityBtn.setOnAction(event -> openSecurityWindow());
        wifiBtn.setOnAction(event -> openWiFiWindow());
        empManageBtn.setOnAction(event -> openEmployeeManagementWindow());
        catalogBtn.setOnAction(event -> openCatalogWindow());
        flyweightDemoBtn.setOnAction(event -> openFlyweightDemoWindow());
        foodCourtBtn.setOnAction(event -> openFoodCourtWindow());
        iteratorDemoBtn.setOnAction(event -> openIteratorDemoWindow());  // New action for Iterator demo

        // Set the scene and show the stage
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Utility method to create buttons with larger text size, colors, and text shadow
    private Button createDepartmentButton(String text, String colorHex) {
        // VBox to align the text
        VBox vbox = new VBox(10);  // Spacing between items
        vbox.setAlignment(Pos.CENTER);  // Center-align text

        // Text for the button with larger font size
        Text buttonText = new Text(text);
        buttonText.setFont(Font.font("Arial", FontWeight.BOLD, 18));  // Larger text font
        buttonText.setStyle("-fx-fill: white;");  // Text color set to white
        buttonText.setEffect(createTextShadow()); // Add shadow effect to button text

        // Add the text to the VBox
        vbox.getChildren().add(buttonText);

        // Create the button and set the VBox as its graphic
        Button button = new Button();
        button.setGraphic(vbox);
        button.setStyle("-fx-background-color: " + colorHex + "; -fx-border-color: #cccccc; -fx-border-radius: 10;");
        button.setPrefWidth(250);
        button.setPrefHeight(250);

        // Add drop shadow effect to the button itself
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        button.setEffect(shadow);

        return button;
    }

    // Create shadow effect for text
    private Effect createTextShadow() {
        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setOffsetX(2);
        textShadow.setOffsetY(2);
        textShadow.setRadius(3);
        return textShadow;
    }

    // Open new windows for respective departments
    private void openBakeryWindow() {
        Stage bakeryStage = new Stage();
        facade.openBakery(bakeryStage);
    }

    private void openSupportWindow() {
        Stage supportStage = new Stage();
        facade.openSupport(supportStage);
    }

    private void openSecurityWindow() {
        Stage securityStage = new Stage();
        facade.openSecurity(securityStage);
    }

    private void openWiFiWindow() {
        Stage wifiStage = new Stage();
        facade.openWiFi(wifiStage);
    }

    private void openEmployeeManagementWindow() {
        Stage empManageStage = new Stage();
        facade.openEmployeeManagement(empManageStage);
    }

    private void openCatalogWindow() {
        Stage catalogStage = new Stage();
        facade.openCompositeCatalog(catalogStage);
    }

    private void openFlyweightDemoWindow() {
        Stage flyweightStage = new Stage();
        facade.openFlyweightDemo(flyweightStage);
    }

    private void openFoodCourtWindow() {
        Stage foodCourtStage = new Stage();
        facade.openFoodCourt(foodCourtStage);
    }

    private void openIteratorDemoWindow() {
        Stage iteratorStage = new Stage();
        facade.openIteratorDemo(iteratorStage);  // Opens the Iterator Demo
    }
}
