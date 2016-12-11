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
			Bullet bullet = new Bullet(boss.getX() - xx, boss.getY());
			// addEntity(bullet);
		}
		if(timer % 100 == 0){
			if(player.delay>0){
				player.delay--;
			}else{
				player.setAttack(false);
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
			if(i instanceof BossChar){
				if(isCollide(player, (BossChar) i)) {
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
            if (CodeUtility.keyPressed.contains(KeyCode.A) && player.getX() - 5 >= 50) {
                player.setX(player.getX() - 5);
            }
            if (CodeUtility.keyPressed.contains(KeyCode.D) && player.getX() + 5 <= 700) {
                player.setX(player.getX() + 5);
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
			}
			
		}
	}

	public void dropKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		CodeUtility.keyPressed.remove(new_code);
		CodeUtility.keyTriggered.remove(new_code);
	}
}
