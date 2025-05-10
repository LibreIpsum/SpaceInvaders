package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends ImageView {

	private int speed;
	
	
	public Ship(int startX, int startY, int speed, String imageURL) throws FileNotFoundException {
		
		super(new Image(new FileInputStream(imageURL)));
		this.speed = speed;
		setX(startX);
		setY(startY);
		setFitWidth(50);
		setPreserveRatio(true);
	}
	
	public void moveLeft() {
		setX(getX()-speed);
	}
	
	public void moveRight() {
		setX(getX()+speed);
	}
	
	
	
}
