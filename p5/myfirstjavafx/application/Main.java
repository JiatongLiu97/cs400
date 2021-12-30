package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Label greetingLabel = new Label("Hello World!");
			
			// Create a Label "CS400 MyFirstJavaFX"
			Label label1 = new Label("CS400 MyFirstJavaFX");
			
			// Build Combo Box
			// https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
			ObservableList<String> options = 
				    FXCollections.observableArrayList(
				        "Option 1",
				        "Option 2",
				        "Option 3"
				    );
				final ComboBox comboBox = new ComboBox(options);
				
			// Build bottoms
		    Button bottoma = new Button("Done"); 
		    
		    // Create a textfield
		    TextField textFeilda = new TextField();
		    
		    // Build an ImageView of an Image (jj.jpg)
		    Image image1 = new Image("x.jpg");
		    ImageView imageView1 = new ImageView(image1);

			BorderPane root = new BorderPane();
			root.setTop(greetingLabel);
			root.setTop(label1);
			root.setLeft(comboBox);
			root.setBottom(bottoma);
			root.setRight(textFeilda);
			root.setCenter(imageView1);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Jiatong's First JavaFX program");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
