package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameManager;
import model.IRenderable;
import model.RenderableHolder;
import model.Entity;

public class GameScreen extends Canvas{
	public static int screen_width, screen_height;
	
	public GameScreen(int width,int height){
		super(width,height);
		screen_height = height;
		screen_width = width;
	}
	
	public void paintComponents(){
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, screen_width, screen_height);
		for(IRenderable i : RenderableHolder.getInstance().getEntities()){
			if(!i.isDestroy())
				i.draw(gc);
		}
		drawScore(gc);
		
	}
	
	public void drawScore(GraphicsContext gc){
		int sc = GameManager.score;
		String st = "";
		if(sc<100 && sc >=10){
			st = "0" + sc;
		}else if(sc<10 && sc >=0){
			st = "00" + sc;
		}else{
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
