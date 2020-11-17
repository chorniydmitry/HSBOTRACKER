package fx;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;


public class FxTest extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage myStage) throws Exception {
		myStage.setTitle("TEST FX");
		
		FlowPane rootNode = new FlowPane(10, 10);
		
		rootNode.setAlignment(Pos.CENTER);
		
		Scene myScene = new Scene(rootNode, 300, 100);
		
		Label response = new Label("Push the button");
		
		Button btnAlpha = new Button("Alpha");
		Button btnBeta = new Button("Beta");
		
		btnAlpha.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				response.setText("ALPHA was pressed.");
				
			}
		});
		
		btnBeta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				response.setText("BETA was pressed.");
				
			}
		});
		
		myStage.setScene(myScene);
		
		rootNode.getChildren().addAll(response, btnAlpha, btnBeta);
		
		myStage.show();
	}

}
