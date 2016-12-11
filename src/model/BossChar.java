package model;

import javafx.scene.canvas.GraphicsContext;

public class BossChar extends Entity{
	public static boolean isDestroy;
	public static int life,colorType;
	public static int delay=0;
	public BossChar(int x, int y) {
		//Fill in here
		super(x,y,150,150);
		life = 10;
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
	
	public void decreaseLife(){
		life--;
		if(life == 0){
			setDestroy();
		}
	}
}
