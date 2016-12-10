package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		Scene scene = new Scene(root, 800, 600);
		Canvas canvas = new Canvas(800,600);		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.add(canvas, 0, 0);
		
		Image brick = new Image(ClassLoader.getSystemResource("brick.png").toString(),50 ,50 ,false ,false);
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(brick, 0, 0);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bobo Game");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}