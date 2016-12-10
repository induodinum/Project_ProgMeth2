package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameManager;
import model.IRenderable;
import model.RenderableHolder;
import model.Entity;

public class GameScreen extends Canvas {
	public static int screen_width, screen_height;

	public GameScreen(int width, int height) {
		super(width, height);
		screen_height = height;
		screen_width = width;
	}

	public void paintComponents() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, screen_width, screen_height);
		Image brick = new Image(ClassLoader.getSystemResource("brick.png").toString(), 50, 50, false, false);
		for (int i = 0; i < screen_width; i += 50) {
			for (int j = 0; j < screen_height; j += 50) {
				if (j == 0 || j == 550 || i == 0 || i == 750) {
					gc.drawImage(brick, i, j);
				}
			}
		}
//		for(IRenderable i : RenderableHolder.getInstance().getEntities()){
//			if (!i.isDestroy())
//				i.draw(gc);
//		}
//		drawScore(gc);

	}

	public void drawScore(GraphicsContext gc) {
		int sc = GameManager.score;
		String st = "";
		if (sc < 100 && sc >= 10) {
			st = "0" + sc;
		} else if (sc < 10 && sc >= 0) {
			st = "00" + sc;
		} else {
			st = Integer.toString(sc);
		}
		Font f = Font.font("Times New Roman", 50);
		gc.setFont(f);
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.WHITE);
		gc.strokeText(st, 350, 50);

		gc.fillText(st, 350, 50);
	}
}
