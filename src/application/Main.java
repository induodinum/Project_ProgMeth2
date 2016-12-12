package application;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import logic.GameManager;
import model.ThreadHolder;
import ui.GameScreen;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 800, 800);
		GameScreen gsc = new GameScreen(800, 800);
		GameManager gm = new GameManager();
		root.getChildren().add(gsc);
		List<Thread> holder = ThreadHolder.instance.getThreads();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				gm.receiveKey(event.getCode());
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				gm.dropKey(event.getCode());
			}
		});
		Thread t = new Thread(new Runnable() {
			public void run() {
				AudioClip sound = new AudioClip(ClassLoader.getSystemResource("bgm.mp3").toString());
				sound.setVolume(0.05);
				sound.play();
				while (true) {
					try {
						if (gm.getPlayer().isDestroy()) {
							System.out.println("game over");
							Platform.runLater(new Runnable() {
								public void run() {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setHeaderText(null);
									alert.setTitle("GameOver");
									alert.setContentText("git gud");
									alert.showAndWait();
								}
							});
							break;
						} else if (gm.getBoss().isDestroy()) {
							System.out.println("game over");
							Platform.runLater(new Runnable() {
								public void run() {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setHeaderText(null);
									alert.setTitle("You won");
									alert.setContentText("got gud :D");
									alert.showAndWait();
								}
							});
							break;
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println("sad");
						break;
					}
				}
			}
		});
		holder.add(t);
		t.start();

		new AnimationTimer() {
			Long start = 0l;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000l) { // 100000000l = 100ms.
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

	public void stop() {
		for (Thread x : ThreadHolder.instance.getThreads()) {
			x.interrupt();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}