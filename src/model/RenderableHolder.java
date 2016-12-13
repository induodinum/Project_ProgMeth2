/*
 * Korpong Sawataksornchuen 5831004821
 * Natt Ruangkriengsin 		5831016321 
 */
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
	public static Image[] playerCharLeft,playerCharRight,playerCharAttackLeft,playerCharAttackRight, bullet;
	public static Image bossChar,bossCharMini,slashLeft,slashRight,heart;

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		playerCharLeft = new Image[3];
		playerCharRight = new Image[3];
		playerCharAttackLeft = new Image[3];
		playerCharAttackRight = new Image[3];
		bullet = new Image[3];
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ()) {
				return 1;
			} else if (o1.getZ() == o2.getZ()) {
				return 0;
			} else
				return -1;
		};
	}

	static {
		loadResource();
	}

	public synchronized void add(IRenderable entity) {
		// Fill in here
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	private static void loadResource() {
		// TODO Auto-generated method stub
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		playerCharLeft[0] = new Image(loader.getResource("playerCharLeft0.png").toString(), 50, 50, false, false);
		playerCharLeft[1] = new Image(loader.getResource("playerCharLeft1.png").toString(), 50, 50, false, false);
		playerCharLeft[2] = new Image(loader.getResource("playerCharLeft2.png").toString(), 50, 50, false, false);
		playerCharRight[0] = new Image(loader.getResource("playerCharRight0.png").toString(), 50, 50, false, false);
		playerCharRight[1] = new Image(loader.getResource("playerCharRight1.png").toString(), 50, 50, false, false);
		playerCharRight[2] = new Image(loader.getResource("playerCharRight2.png").toString(), 50, 50, false, false);
		playerCharAttackLeft[0] = new Image(loader.getResource("playerCharAttackLeft0.png").toString(), 50, 50, false, false);
		playerCharAttackLeft[1] = new Image(loader.getResource("playerCharAttackLeft1.png").toString(), 50, 50, false, false);
		playerCharAttackLeft[2] = new Image(loader.getResource("playerCharAttackLeft2.png").toString(), 50, 50, false, false);
		playerCharAttackRight[0] = new Image(loader.getResource("playerCharAttackRight0.png").toString(), 50, 50, false, false);
		playerCharAttackRight[1] = new Image(loader.getResource("playerCharAttackRight1.png").toString(), 50, 50, false, false);
		playerCharAttackRight[2] = new Image(loader.getResource("playerCharAttackRight2.png").toString(), 50, 50, false, false);
		bossChar = new Image(loader.getResource("bossChar.png").toString(), 150, 150, false, false);
		bossCharMini = new Image(loader.getResource("bossChar.png").toString(), 50, 50, false, false);
		bullet[0] = new Image(loader.getResourceAsStream("bullet0.png"));
		bullet[1] = new Image(loader.getResourceAsStream("bullet1.png"));
		bullet[2] = new Image(loader.getResourceAsStream("bullet2.png"));
		slashLeft = new Image(loader.getResource("slashLeft.png").toString(), 100, 80, false, false);
		slashRight = new Image(loader.getResource("slashRight.png").toString(), 100, 80, false, false);
		heart = new Image(loader.getResource("heart.png").toString(), 30, 30, false, false);
	}

	public synchronized void remove(int index) {
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
