package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// main group and scene
			Group root = new Group();
			Scene scene = new Scene(root,800,800);
			
			
			// create a test ship
			Ship ship = new Ship(50,100,10,"/home/andrew/git/SpaceInvaders/SpaceInvadersGame/img/playership.png");		
			root.getChildren().add(ship);
			
			
			// scene setup
			stage.setTitle("Space Invaders");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
