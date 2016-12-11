package model;

import javafx.scene.canvas.GraphicsContext;

public class Slash extends Entity {
	public boolean isDestroy;
	public int faceDirection;
	public Slash(int x, int y, PlayerChar player) {
		//Fill in here
		super(x,y,75,50);
		isDestroy = false;
		z = 3;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(faceDirection == 0){
			gc.drawImage(RenderableHolder.slashLeft, x, y);
		}else{
			gc.drawImage(RenderableHolder.slashRight, x, y);
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
	
	public void setFaceDirection(int faceDirection){
		this.faceDirection = faceDirection;
	}
}