package model;

import javafx.scene.canvas.GraphicsContext;

public class Bullet extends Entity {
	public boolean isDestroy;
	public int moveSpeedX,moveSpeedY;
	//directionX 1==right, -1==left, directionY 1==down, -1==up, 0 = stand still
	public int moveDirectionX,moveDirectionY;
	public int life,colorType;
	public int bounce;
	public Bullet(int x, int y, int colorType, int moveSpeedX, int moveSpeedY, int moveDirectionX, int moveDirectionY) {
		//Fill in here
		super(x,y,20,20);
		this.moveDirectionX = moveDirectionX;
		this.moveDirectionY = moveDirectionY;
		isDestroy = false;
		z = 3;
		this.colorType = colorType;
		if(colorType == 0){
			//RED = fast, no bounce
			bounce = 0;
			this.moveSpeedX = moveSpeedX + 3;
			this.moveSpeedY = moveSpeedY + 3;
		}else if(colorType == 1){
			//Green = slow, 1 bounce
			bounce = 2;
			this.moveSpeedX = (moveSpeedX / 2) + 1;
			this.moveSpeedY = (moveSpeedY / 2) + 1;
		}else if(colorType == 2){
			//Blue = normal, no bounce
			bounce = 1;
			this.moveSpeedX = moveSpeedX + 1;
			this.moveSpeedY = moveSpeedY + 1;
		}
		
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
