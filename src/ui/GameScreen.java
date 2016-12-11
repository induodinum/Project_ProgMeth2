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
import model.BossChar;
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
			for (int j = 0; j < 600; j += 50) {
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
		drawHPbar(gc);
	}

	public void drawHPbar(GraphicsContext gc) {
		int player_life = PlayerChar.life;
		int boss_life = BossChar.life;
		for(int i=0;i<player_life;i++){
			gc.drawImage(RenderableHolder.getInstance().heart, 120+(40*i), 650);
		}
		gc.drawImage(RenderableHolder.getInstance().playerCharRight[0], 50, 630);
		for(int j=0;j<boss_life;j++){
			gc.drawImage(RenderableHolder.getInstance().heart, 120+(40*j), 720);
		}
		gc.drawImage(RenderableHolder.getInstance().bossCharMini, 50, 700);
	}
}
