package model;

import javafx.scene.canvas.GraphicsContext;

public class PlayerChar extends Entity{
	public static boolean isDestroy;
	public static int moveSpeed;
	public static int life,colorType;
	//direction 1 == right, 0 == left
	public static int faceDirection = 1;
	//private int animationFrame = 0;
	public static int delay=0;
	private boolean attack = false;
	public PlayerChar(int x, int y,int colorType) {
		//Fill in here
		super(x, y, 50, 50);
		life = 10;
		isDestroy = false;
		z = 3;
		this.colorType=colorType;
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if(colorType == 0){
			if(isAttack()){
				if(faceDirection == 1){
					gc.drawImage(RenderableHolder.playerCharAttackRight[0], x, y);
				}else{
					gc.drawImage(RenderableHolder.playerCharAttackLeft[0], x, y);
				}
			}else if(faceDirection == 1){
				gc.drawImage(RenderableHolder.playerCharRight[0], x, y);
			}else{
				gc.drawImage(RenderableHolder.playerCharLeft[0], x, y);
			}
		}else if(colorType == 1){
			if(isAttack()){
				if(faceDirection == 1){
					gc.drawImage(RenderableHolder.playerCharAttackRight[1], x, y);
				}else{
					gc.drawImage(RenderableHolder.playerCharAttackLeft[1], x, y);
				}
			}else if(faceDirection == 1){
				gc.drawImage(RenderableHolder.playerCharRight[1], x, y);
			}else{
				gc.drawImage(RenderableHolder.playerCharLeft[1], x, y);
			}
		}else if(colorType == 2){
			if(isAttack()){
				if(faceDirection == 1){
					gc.drawImage(RenderableHolder.playerCharAttackRight[2], x, y);
				}else{
					gc.drawImage(RenderableHolder.playerCharAttackLeft[2], x, y);
				}
			}else if(faceDirection == 1){
				gc.drawImage(RenderableHolder.playerCharRight[2], x, y);
			}else{
				gc.drawImage(RenderableHolder.playerCharLeft[2], x, y);
			}
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
	
	public boolean isAttack(){
		return attack;
	}
	
	public void setAttack(boolean attack){
		this.attack = attack;
	}
	
}
