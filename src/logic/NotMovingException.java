package logic;

public class NotMovingException extends Exception{
	public NotMovingException(){
		super();
		System.out.println("Entity not moving");
	}
}
