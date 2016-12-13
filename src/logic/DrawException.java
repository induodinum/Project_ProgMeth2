package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DrawException extends Exception{
	
	public DrawException(){
		super();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("???");
		alert.setHeaderText(null);
		alert.setContentText("You win! but you also lose?");
	}

}
