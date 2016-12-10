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

	public GameManager() {
		player = new PlayerChar(150, 475, 0);
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
			int xx = rand.nextInt(470);
			int yy = rand.nextInt(470);
			Bullet bullet = new Bullet(boss.getX() - 10, boss.getY());
			addEntity(bullet);
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

	private boolean isCollide(PlayerChar player, Entity e) {
		return checkX(player, e) && checkY(player, e);
	}

	private boolean checkX(PlayerChar player, Entity e) {
		return (player.getX() + player.getWidth() > e.getX() && player.getX() < e.getX())
				|| (e.getX() + e.getWidth() > player.getX() && e.getX() < player.getX());
	}

	private boolean checkY(PlayerChar player, Entity e) {
		return (player.getY() + player.getHeight() > e.getY() && player.getY() < e.getY())
				|| (e.getY() + e.getHeight() > player.getY() && e.getY() < player.getY());
	}

	private void checkCollision() {
		// TODO Auto-generated method stub
		// Body head = Snake.body.get(0);
		// Fill in here
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if (i instanceof Bullet) {
				if (isCollide(player, (Bullet) i)) {
					((Bullet) i).setDestroy();
					player.decreaseLife();
				}
			}
		}

	}

	/*
	 * private void checkDead(){ if(arrow.getX()<0 || arrow.getX()>451 ||
	 * arrow.getY()<0 || arrow.getY()>451){ arrow.isDestroy = true; }
	 * 
	 * }
	 */

	private void move() {
		// TODO Auto-generated method stub
		// walk according to received key
		if (!CodeUtility.keyPressed.isEmpty()) {
				if (CodeUtility.keyPressed.contains(KeyCode.A)&&player.getX()-10>=50) {
					player.setX(player.getX() - 10);
					if (player.getY() + 15 <= 475 && !CodeUtility.keyPressed.contains(KeyCode.W)) {
						player.setY(player.getY() + 15);
					}
				}
				if (CodeUtility.keyPressed.contains(KeyCode.D)&&player.getX()+10<=700) {
					player.setX(player.getX() + 10);
					if (player.getY() + 15 <= 475 && !CodeUtility.keyPressed.contains(KeyCode.W)) {
						player.setY(player.getY() + 15);
					}
				}
				if (CodeUtility.keyPressed.contains(KeyCode.W)&&player.getY()-15>=50) {
					player.setY(player.getY() - 15);
				}
			} else if (player.getY() + 15 <= 475) {
				player.setY(player.getY() + 15);
		}
	}

	public void receiveKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		if (!CodeUtility.keyPressed.contains(new_code)) {
			CodeUtility.keyPressed.add(new_code);
			CodeUtility.keyTriggered.add(new_code);

			move();
		}
	}

	public void dropKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		CodeUtility.keyPressed.remove(new_code);
		CodeUtility.keyTriggered.remove(new_code);
	}
}
