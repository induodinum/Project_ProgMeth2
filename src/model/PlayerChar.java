package model;

import javafx.scene.canvas.GraphicsContext;

public class PlayerChar extends Entity{
	public static boolean isDestroy;
	public static int moveSpeed;
	public static int life,colorType;
	public static int width,height;
	public PlayerChar(int x, int y,int colorType) {
		//Fill in here
		super(x, y, 50, 75);
		life = 5;
		isDestroy = false;
		z = 2;
		this.colorType=colorType;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(colorType == 0){
			gc.drawImage(RenderableHolder.playerChar[0], x, y);
		}else if(colorType == 1){
			gc.drawImage(RenderableHolder.playerChar[1], x, y);
		}else if(colorType == 2){
			gc.drawImage(RenderableHolder.playerChar[2], x, y);
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
	
	public void decreaseLife(){
		life--;
		if(life == 0){
			setDestroy();
		}
	}
}
