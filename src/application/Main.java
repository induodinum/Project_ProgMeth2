package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.GameManager;
import ui.GameScreen;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 800, 800);
		GameScreen gsc = new GameScreen(800,800);
		GameManager gm = new GameManager();
		root.getChildren().add(gsc);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				gm.receiveKey(event.getCode());
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				gm.dropKey(event.getCode());
			}
		});
		Thread t = new Thread(new Runnable(){
			public void run(){
				AudioClip sound = new AudioClip(ClassLoader.getSystemResource("bgm.mp3").toString());
				sound.setVolume(0.05);
				sound.play();
			}
		});
		t.start();
		new AnimationTimer() {
			Long start=0l;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(start==0l)start=now;
				long diff = now-start;
				if(diff>=10000000l){ //100000000l = 100ms.
					gm.update();
					gsc.paintComponents();
					start = now;
				}
			}
		}.start();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bobo Game");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}