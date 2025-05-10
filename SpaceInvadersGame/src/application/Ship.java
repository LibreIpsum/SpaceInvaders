package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship extends ImageView {

	private double speed;
	
	
	public Ship(double startX, double startY, double speed, String imageURL) throws FileNotFoundException {
		super(new Image(new FileInputStream(imageURL)));
		this.speed = speed;
		setX(startX);
		setY(startY);
		setFitWidth(35);
		setPreserveRatio(true);
	}
	
	public void moveLeft() {
		setX(getX()-speed);
	}
	
	public void moveRight() {
		setX(getX()+speed);
	}
	
	
	
}
