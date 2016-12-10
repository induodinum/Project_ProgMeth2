package model;


public abstract class Entity implements IRenderable {
	protected int x,y,z;
	protected int width,height;
	
	public Entity(int x,int y,int width, int height){
		this.x=x;
		this.y=y;
		z=1;
		this.width = width;
		this.height = height;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	public void setZ(int z) {
		// TODO Auto-generated method stub
		this.z=z;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int w){
		this.width = w;
	}
	public int getWidth(){
		return width;
	}
	public void setHeight(int h){
		this.height = h;
	}
	public int getHeight(){
		return height;
	}
	

}
