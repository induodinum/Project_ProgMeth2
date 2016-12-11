package logic;

import java.util.List;
import java.util.Random;

import model.IRenderable;
import model.RenderableHolder;
import javafx.scene.input.KeyCode;
import model.*;

public class GameManager {
	private int timer = 0;
	public static int hp = 100;
	private PlayerChar player;
	private BossChar boss;
	private Slash slash;

	public GameManager() {
		player = new PlayerChar(150, 500, 0);
		boss = new BossChar(550, 400);
		RenderableHolder.getInstance().add(player);
		RenderableHolder.getInstance().add(boss);

	}

	private void addEntity(IRenderable entity) {
		// TODO Auto-generated method stub
		RenderableHolder.getInstance().add(entity);
	}

	public int getTimer() {
		return timer;
	}

	public void update() {
		// TODO Auto-generated method stub
		// Fill in here
		timer++;
		move();
		checkCollision();
		removeDestroyEntity();
		if (timer % 20 == 0 && !player.isDestroy()) {
			Random rand = new Random();
			int xx = rand.nextInt(200);
			int yy = rand.nextInt(3);
			addEntity(new Bullet(boss.getX() - xx, boss.getY(),yy));
		}
		if(timer % 4 == 0){
			if(player.delay>0){
				player.delay--;
			}else{
				player.setAttack(false);
				for (IRenderable i : RenderableHolder.getInstance().getEntities()){
					if(i instanceof Slash){
						((Slash) i).setDestroy();
						slash = null;
					}
				}
			}
		}
	}

	private void removeDestroyEntity() {
		// TODO Auto-generated method stub
		List<IRenderable> entities = RenderableHolder.getInstance().getEntities();
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroy())
				RenderableHolder.getInstance().remove(i);
		}
	}

	private boolean isCollide(Entity a, Entity e) {
		return checkX(a, e) && checkY(a, e);
	}

	private boolean checkX(Entity a, Entity e) {
		return (a.getX() + a.getWidth() > e.getX() && a.getX() < e.getX())
				|| (e.getX() + e.getWidth() > a.getX() && e.getX() < a.getX());
	}

	private boolean checkY(Entity a, Entity e) {
		return (a.getY() + a.getHeight() > e.getY() && a.getY() < e.getY())
				|| (e.getY() + e.getHeight() > a.getY() && e.getY() < a.getY());
	}

	private void checkCollision() {
		// TODO Auto-generated method stub
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if (i instanceof Bullet) {
				if (isCollide(player, (Bullet) i)) {
					((Bullet) i).setDestroy();
					player.decreaseLife();
				}
				if(player.colorType == ((Bullet)i).colorType) {
					if(isCollide(slash, (Bullet) i))
						((Bullet) i).setDestroy();
				}
			}
			if(i instanceof BossChar){
				if(isCollide(player, (BossChar) i)) {
					player.decreaseLife();
				}
				if(isCollide(slash, (BossChar) i))
					 boss.decreaseLife();
			}
		}

	}

	private void move() {
        // TODO Auto-generated method stub
        // walk according to received key
        if (!CodeUtility.keyPressed.isEmpty()) {
            if (CodeUtility.keyPressed.contains(KeyCode.A) && player.getX() - 5 >= 50) {
                player.setX(player.getX() - 5);
                player.faceDirection = 0;
            }
            if (CodeUtility.keyPressed.contains(KeyCode.D) && player.getX() + 5 <= 700) {
                player.setX(player.getX() + 5);
                player.faceDirection = 1;
            }
            if (CodeUtility.keyPressed.contains(KeyCode.W) && player.getY() -10 >= 50) {
                player.setY(player.getY() - 10);
            }
        }
        if (player.getY() + 10 <= 500 && !CodeUtility.keyPressed.contains(KeyCode.W)) {
            player.setY(player.getY() + 10);
        }
    }

	public void receiveKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		if (!CodeUtility.keyPressed.contains(new_code)) {
			CodeUtility.keyPressed.add(new_code);
			CodeUtility.keyTriggered.add(new_code);
			if(new_code == KeyCode.J){
				player.colorType = (player.colorType + 1)%3;
			}
			if(new_code == KeyCode.K){
				player.setAttack(true);
				player.delay = 3;
				if(player.faceDirection == 1){
					slash = new Slash(player.getX() + player.getWidth(), player.getY() - 15, player);
				}else{
					slash = new Slash(player.getX() - 100, player.getY() - 15, player);
				}
				slash.setFaceDirection(player.faceDirection);
				addEntity(slash);
			}
			
		}
	}

	public void dropKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		CodeUtility.keyPressed.remove(new_code);
		CodeUtility.keyTriggered.remove(new_code);
	}
}
