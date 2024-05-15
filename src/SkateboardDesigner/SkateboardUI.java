
/**
 * Program: SKATEBOARD App
 * Author: K-Andre Harris
 * Date: May 5, 2024
 * 
 * StudentID: 1435677
 * Course: CIT111-Z02 Intro to Prog: Java
 * Spring 2024
 * 
 * TODO Still need to read more on javafx
*/
package SkateboardDesigner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SkateboardUI extends Application {
	private final double SALES_TAX_RATE = 0.07;
    private ListView<String> deckListView;
    private ListView<String> truckListView;
    private ListView<String> wheelListView;
    private ListView<String> miscellaneousListView;
    private Label subtotalLabel;
    private Label taxLabel;
    private Label totalLabel;
    
	@Override
	public void start(Stage stage) throws Exception {

		stage.setTitle("Skateboard Designer");
	   
		// Create the list views for decks, trucks, wheels, and miscellaneous prods
        deckListView = new ListView<>();
        truckListView = new ListView<>();
        wheelListView = new ListView<>();
        miscellaneousListView = new ListView<>();
        miscellaneousListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        // Populate list views with items
        deckListView.getItems().addAll("The Master Thrasher $60", "The Dictator $45", "The Street King $50");
        truckListView.getItems().addAll("7.75-inch axle $35", "8-inche axle $40", "8.5-inch axle $45");
        wheelListView.getItems().addAll("51mm $20", "55mm $22", "58mm $24", "61mm $28");
        miscellaneousListView.getItems().addAll("Grip tape: $10", "Bearings: $30", "Riser pads: $2", "Nuts & bolts kit: $4");
 
        
     // Create labels for subtotal, tax, and total
        subtotalLabel = new Label("Subtotal: $0.00");
        taxLabel = new Label("Tax (7%): $0.00");
        totalLabel = new Label("Total: $0.00");
        
        
        // Create calculate button
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnAction(e -> calculateTotal());
        
        
        // Create layout for selection controls
        VBox selectionLayout = new VBox(10);
        selectionLayout.getChildren().addAll(
                new Label("Select a Deck:"),
                deckListView,
                new Label("Select a Truck Assembly:"),
                truckListView,
                new Label("Select a Wheel Set:"),
                wheelListView,
                new Label("Select Miscellaneous Products:"),
                miscellaneousListView,
                calculateButton
        );

        // Create layout for order summary
        VBox summaryLayout = new VBox(10);
        summaryLayout.getChildren().addAll(
                subtotalLabel,
                taxLabel,
                totalLabel
        );

        // Create layout for the entire scene
        HBox root = new HBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(selectionLayout, summaryLayout);

        // Set up scene
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
	}

	  private void calculateTotal() {
	        double subtotal = 0.0;

	        // Calculate subtotal based on selected items
	        subtotal += getSelectedItemPrice(deckListView);
	        subtotal += getSelectedItemPrice(truckListView);
	        subtotal += getSelectedItemPrice(wheelListView);

	        // Calculate subtotal for miscellaneous products
	        for (String item : miscellaneousListView.getSelectionModel().getSelectedItems()) {
	            String[] parts = item.split("\\$");
	            subtotal += Double.parseDouble(parts[1]);
	        }

	        // Calculate tax and total
	        double tax = subtotal * SALES_TAX_RATE;
	        double total = subtotal + tax;

	       
	        subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subtotal));
	        taxLabel.setText("Tax (7%): $" + String.format("%.2f", tax));
	        totalLabel.setText("Total: $" + String.format("%.2f", total));
	    }

	 private double getSelectedItemPrice(ListView<String> listView) {
	        String selectedItem = listView.getSelectionModel().getSelectedItem();
	        if (selectedItem != null) {
	            String[] parts = selectedItem.split("\\$");
	            return Double.parseDouble(parts[1]);
	        }
	        return 0.0;
	    }
	  public static void main(String[] args) {
		launch();
	}

}
