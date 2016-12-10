package model;

import javafx.scene.canvas.GraphicsContext;

public class BossChar extends Entity{
	public static boolean isDestroy;
	public static int life,colorType;
	public BossChar(int x, int y) {
		//Fill in here
		super(x,y,100,100);
		life = 5;
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
	
	public void setDestroy(){
		isDestroy=true;
	}
}
