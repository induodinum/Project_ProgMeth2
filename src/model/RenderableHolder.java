package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.IRenderable;
import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image[] playerChar,bullet;
	public static Image bossChar;
	
	public RenderableHolder(){
		entities = new ArrayList<IRenderable>();
		playerChar = new Image[3];
		bullet = new Image[3];
		comparator = (IRenderable o1, IRenderable o2) -> {
			if(o1.getZ() > o2.getZ()){
				return 1;
			}else if(o1.getZ() == o2.getZ()){
				return 0;
			}else
				return -1;
		};
	}
	static{
		loadResource();
	}
	
	public synchronized void add(IRenderable entity){
		//Fill in here
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}
	private static void loadResource() {
		// TODO Auto-generated method stub
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		playerChar[0] = new Image(loader.getResourceAsStream("playerChar0.png"));
		playerChar[1] = new Image(loader.getResourceAsStream("playerChar1.png"));
		playerChar[2] = new Image(loader.getResourceAsStream("playerChar2.png"));
		bossChar = new Image(loader.getResourceAsStream("bossChar.png"));
		bullet[0] = new Image(loader.getResourceAsStream("bullet0.png"));
		bullet[1] = new Image(loader.getResourceAsStream("bullet1.png"));
		bullet[2] = new Image(loader.getResourceAsStream("bullet2.png"));
		
	}
	public synchronized void remove(int index){
		System.out.println("remove");
		entities.remove(index);
		Collections.sort(entities, comparator);
	}

	public synchronized static RenderableHolder getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public synchronized List<IRenderable> getEntities() {
		// TODO Auto-generated method stub
		return entities;
	}
}
