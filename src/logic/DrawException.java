package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DrawException extends Exception{
	
	public DrawException(){
		super();
		System.out.println("draw");
	}

}
