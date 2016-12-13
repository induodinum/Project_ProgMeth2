package model;

import javafx.scene.canvas.GraphicsContext;

public class BossChar extends Entity {
	public static boolean isDestroy;

	public static int life;
	public int delay = 0;
	public int directionX = 1, directionY = 0;
	private int speed = 2;

	// directionX=0 is left
	// directionY=1 is down

	public BossChar(int x, int y) {
		// Fill in here
		super(x, y, 150, 150);
		life = 15;
		isDestroy = false;
		z = 2;

	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub

		gc.drawImage(RenderableHolder.bossChar, x, y);

	}

	@Override
	public boolean isDestroy() {
		// TODO Auto-generated method stub
		return isDestroy;
	}

	public void setDestroy() {
		isDestroy = true;
	}

	public void decreaseLife() {
		life--;
		if (life == 0) {
			setDestroy();
		}
	}

	public void setDirectionX(int direction) {
		directionX = direction;
	}

	public int getDirectionX() {
		return directionX;
	}

	public void setDirectionY(int direction) {
		directionY = direction;
	}

	public int getDirectionY() {
		return directionY;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
}
