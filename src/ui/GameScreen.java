package ui;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameManager;
import model.IRenderable;
import model.PlayerChar;
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
		List<IRenderable> entities = RenderableHolder.getInstance().getEntities();
		for(IRenderable i : entities){
			if(!i.isDestroy()){
				i.draw(gc);
			}
		}

	}

	public void drawHPbar(GraphicsContext gc) {
		int hp = GameManager.hp;
		
	}
}
