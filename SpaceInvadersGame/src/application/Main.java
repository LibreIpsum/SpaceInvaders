package application;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	ArrayList<String> input = new ArrayList<String>();
	
	int laserCooldown = 30;
	int cooldownFrame = 0;
	ArrayList<Laser> playerLasers = new ArrayList<Laser>();
	
	@Override
	public void start(Stage stage) {
		try {
			// main group and scene
			Group root = new Group();
			Scene scene = new Scene(root, 800, 800, Color.BLACK);
			
			
			// create a player ship
			Player player = new Player(scene.getWidth()/2, scene.getHeight()-100);		
			root.getChildren().add(player);
			

			// event handling
			scene.setOnKeyPressed(event -> handleKeyPress(event));
			scene.setOnKeyReleased(event -> handleKeyReleased(event));

			
			stage.setTitle("Space Invaders");
			stage.setScene(scene);
			stage.show();
			
			initializeGameLoop(root, player, playerLasers);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private void initializeGameLoop(Group root, Player player, ArrayList<Laser> lasers) {
        // Set up the game loop with 60 FPS (16.67 milliseconds per frame)
        Duration frameDuration = Duration.millis(16.67);
        KeyFrame gameFrame = new KeyFrame(frameDuration, event -> {
			try {
				updateGame(root, player, lasers);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        Timeline gameLoop = new Timeline(gameFrame);
        gameLoop.setCycleCount(Animation.INDEFINITE);

        // Start the game loop
        gameLoop.play();
    }

    private void updateGame(Group root, Player player, ArrayList<Laser> lasers) throws FileNotFoundException {
        // counters
    	cooldownFrame-=1;
    	
    	
    	// player inputs
    	if (input.contains("LEFT")) {
        	player.moveLeft();
        }
        if (input.contains("RIGHT")) {
        	player.moveRight();
        }
        // laser firing
        if (input.contains("SPACE")) {
        	if (cooldownFrame <= 0) {
        		Laser laser = new Laser(player.getX(), player.getY(), 10);
    			lasers.add(laser);
    			root.getChildren().add(laser);
    			cooldownFrame = laserCooldown;
        	}
        	
        }
        
        
        // Laser movement
        for (int i=0;i<lasers.size();i++) {
        	Laser laser = lasers.get(i);
        	laser.update();
        	if (laser.getY() < -20) {
        		lasers.remove(i);
        		root.getChildren().remove(laser);
        	}
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
