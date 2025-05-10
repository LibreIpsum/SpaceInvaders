package application;

import java.io.FileNotFoundException;

public class Player extends Ship{

	public Player(double startX, double startY) throws FileNotFoundException {
		super(startX, startY, 3, "/home/andrew/git/SpaceInvaders/SpaceInvadersGame/img/playership.png");
	}
}
