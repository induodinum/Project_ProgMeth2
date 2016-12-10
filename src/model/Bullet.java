package model;

import javafx.scene.canvas.GraphicsContext;

public class Bullet extends Entity {
	public static boolean isDestroy;
	public static int moveSpeed;
	public static int moveDirectionX,moveDirectionY;
	public static int life,colorType;
	public Bullet(int x, int y) {
		//Fill in here
		super(x,y,20,20);
		moveSpeed = 5;
		isDestroy = false;
		z = 2;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
		if(colorType == 0){
			gc.drawImage(RenderableHolder.bullet[0], x, y);
		}else if(colorType == 1){
			gc.drawImage(RenderableHolder.bullet[1], x, y);
		}else if(colorType == 2){
			gc.drawImage(RenderableHolder.bullet[2], x, y);
		}
		
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
