package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Laser extends ImageView {

	private double speed;
	
	public Laser(double startX, double startY, double speed) throws FileNotFoundException {
		super(new Image(new FileInputStream("img/laser.png")));
		this.speed = speed;
		setX(startX);
		setY(startY);
		setFitWidth(30);
		setPreserveRatio(true);
	}
	
	public void update() {
		setY(getY()-speed);
	}
}
