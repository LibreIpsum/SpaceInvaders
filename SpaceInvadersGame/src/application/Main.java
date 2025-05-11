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
	
	int appWidth = 800;
	
	ArrayList<String> input = new ArrayList<String>();
	
	int laserCooldown = 30;
	int cooldownFrame = 0;
	ArrayList<Laser> playerLasers = new ArrayList<Laser>();
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	int enemySpeed = 1;
	int currentEnemyStep = 1;
	int enemySteps = appWidth / enemySpeed;
	int enemyPad = 50;
	int enemyWidth = 0;
	
	@Override
	public void start(Stage stage) {
		try {
			// main group and scene
			Group root = new Group();
			Scene scene = new Scene(root, appWidth, appWidth, Color.rgb(0, 0,	15));
			
			
			// create a player ship
			Player player = new Player(scene.getWidth()/2, scene.getHeight()-100);		
			root.getChildren().add(player);
			
			
			// create enemies			
			for (int w=0; w<12; w++) {
				for (int h=0; h<5; h++) {
					Enemy enemy = new Enemy((w*enemyPad), (h*enemyPad)+60, 1);
					enemies.add(enemy);
//					enemyBox.getChildren().add(enemy);
					root.getChildren().add(enemy);
				}
				enemyWidth += enemyPad;
			}
			enemySteps -= enemyWidth;
			
			// event handling
			scene.setOnKeyPressed(event -> handleKeyPress(event));
			scene.setOnKeyReleased(event -> handleKeyReleased(event));

			
			stage.setTitle("Space Invaders");
			stage.setScene(scene);
			stage.show();
			
			initializeGameLoop(root, player, playerLasers, enemies);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private void initializeGameLoop(Group root, Player player, ArrayList<Laser> lasers, ArrayList<Enemy> enemies) {
        // Set up the game loop with 60 FPS (16.67 milliseconds per frame)
        Duration frameDuration = Duration.millis(16.67);
        KeyFrame gameFrame = new KeyFrame(frameDuration, event -> {
			try {
				updateGame(root, player, lasers, enemies);
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

    private void updateGame(Group root, Player player, ArrayList<Laser> lasers, ArrayList<Enemy> enemies) throws FileNotFoundException {
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
        		Laser laser = new Laser(player.getX() + (player.getWidth()/2)-5, player.getY(), 10);
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
        
        
     // Enemy movement
        for (int i=0; i<enemies.size(); i++){
        	Enemy enemy = enemies.get(i);
        	enemy.setX(enemy.getX() + enemySpeed);
        	for (int j=0; j<lasers.size();j++) {
        		Laser laser = lasers.get(j);
        		if (laser.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
        			System.out.println("collision" + i + ", " + j);
        			lasers.remove(j);
        			root.getChildren().remove(laser);
        			enemies.remove(i);
        			root.getChildren().remove(enemy);
        		}
        	}
        }
        currentEnemyStep += 1;
        if (currentEnemyStep >= enemySteps) {
    		enemySpeed *= -1;
    		currentEnemyStep = 1;
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
