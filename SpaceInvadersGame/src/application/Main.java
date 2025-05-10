package application;


import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	ArrayList<String> input = new ArrayList<String>();
	
	@Override
	public void start(Stage stage) {
		try {
			// main group and scene
			Group root = new Group();
			Scene scene = new Scene(root,800,800);
			
			
			// create a test ship
			Player player = new Player(scene.getWidth()/2, scene.getHeight()-100);		
			root.getChildren().add(player);
			
			
			// event handling
			scene.setOnKeyPressed(event -> handleKeyPress(event));
			scene.setOnKeyReleased(event -> handleKeyReleased(event));

			
			stage.setTitle("Space Invaders");
			stage.setScene(scene);
			stage.show();
			
			initializeGameLoop(player);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private void initializeGameLoop(Player player) {
        // Set up the game loop with 60 FPS (16.67 milliseconds per frame)
        Duration frameDuration = Duration.millis(16.67);
        KeyFrame gameFrame = new KeyFrame(frameDuration, event -> updateGame(player));
        Timeline gameLoop = new Timeline(gameFrame);
        gameLoop.setCycleCount(Animation.INDEFINITE);

        // Start the game loop
        gameLoop.play();
    }

    private void updateGame(Player player) {
        if (input.contains("LEFT")) {
        	player.moveLeft();
        }
        if (input.contains("RIGHT")) {
        	player.moveRight();
        }
    }
    
    // event handler functions
    private void handleKeyPress(KeyEvent event) {
    	String code = event.getCode().toString();
    	if (!input.contains(code)) {
    		input.add(code);
    	}
    }
    private void handleKeyReleased(KeyEvent event) {
		input.remove(event.getCode().toString());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
