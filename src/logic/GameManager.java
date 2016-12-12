package logic;

import java.util.List;
import java.util.Random;
import model.IRenderable;
import model.RenderableHolder;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import model.*;

public class GameManager {
	Random rand = new Random();
	private int once = 1;
	private int timer = 0;
	private int spawnDelay = 30;
	private PlayerChar player;
	private BossChar boss;
	private Slash slash = new Slash(1000, 1000, player);
	private int counter = 0;

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

	public PlayerChar getPlayer() {
		return player;
	}

	public BossChar getBoss() {
		return boss;
	}

	public int getTimer() {
		return timer;
	}

	public void update() {
		// TODO Auto-generated method stub
		// Fill in here
		timer++;
		move();
		moveBullet();
		bossMove();
		checkCollision();
		if(boss.isDestroy()){
			for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
				if (i instanceof Bullet) {
					((Bullet) i).setDestroy();
				}
			}
		}
		removeDestroyEntity();
		spawnBullet();
		decreaseDelay();
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
					if (player.colorType == 0) {
						player.decreaseLife();
						player.decreaseLife();
						player.decreaseLife();
						player.decreaseLife();
						player.decreaseLife();
					} else if (player.colorType == 1) {
						player.decreaseLife();
						player.decreaseLife();
					} else {
						player.decreaseLife();
					}
				}
				if (player.colorType == ((Bullet) i).colorType) {
					if (isCollide(slash, (Bullet) i)){
						((Bullet) i).setDestroy();
						counter++;
						if(counter == 5){
							counter = 0;
							player.increaseLife();
						}
					}
						
				}
			}
			if (i instanceof BossChar) {
				if (isCollide(player, (BossChar) i)) {
					player.decreaseLife();
				}
				if (isCollide(slash, (BossChar) i) && boss.delay == 0) {
					if (player.colorType == 0) {
						boss.decreaseLife();
						boss.decreaseLife();
					} else {
						boss.decreaseLife();
					}

					System.out.println("jeb");
					boss.delay = 4;
					AudioClip jeb = new AudioClip(ClassLoader.getSystemResource("jeb.m4a").toString());
					jeb.play(0.3);
				}

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
			if (CodeUtility.keyPressed.contains(KeyCode.W) && player.getY() - 10 >= 50) {
				player.setY(player.getY() - 10);
			}
		}
		if (player.getY() + 10 <= 500 && !CodeUtility.keyPressed.contains(KeyCode.W)) {
			player.setY(player.getY() + 10);
		}
	}

	private void moveBulletNormal(IRenderable i) {
		if (((Bullet) i).moveDirectionX == 1) {
			((Bullet) i).setX(((Bullet) i).getX() + ((Bullet) i).moveSpeedX);
		} else if (((Bullet) i).moveDirectionX == -1) {
			((Bullet) i).setX(((Bullet) i).getX() - ((Bullet) i).moveSpeedX);
		}
		if (((Bullet) i).moveDirectionY == 1) {
			((Bullet) i).setY(((Bullet) i).getY() + ((Bullet) i).moveSpeedY);
		} else if (((Bullet) i).moveDirectionY == -1) {
			((Bullet) i).setY(((Bullet) i).getY() - ((Bullet) i).moveSpeedY);
		}
	}

	private void moveBullet() {
		for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
			if (i instanceof Bullet) {
				// check out of stage
				if (outOfStageX(((Bullet) i))) {
					if (((Bullet) i).bounce > 0) {
						((Bullet) i).bounce--;
						((Bullet) i).moveDirectionX *= -1;
						moveBulletNormal(i);
					} else if (((Bullet) i).bounce <= 0) {
						((Bullet) i).setDestroy();
					}
				} else if (outOfStageY(((Bullet) i))) {
					if (((Bullet) i).bounce > 0) {
						((Bullet) i).bounce--;
						((Bullet) i).moveDirectionY *= -1;
						moveBulletNormal(i);
					} else if (((Bullet) i).bounce <= 0) {
						((Bullet) i).setDestroy();
					}

				} else {
					// move normally
					moveBulletNormal(i);
				}
			}
		}
	}

	public void bossMove() {
		if (boss.life >= 11) {
			if (boss.getDirectionX() == -1 && boss.getX() == 50) {
				boss.setDirectionY(1);
				boss.setDirectionX(0);
			} else if (boss.getDirectionY() == -1 && boss.getY() == 50) {
				boss.setDirectionX(-1);
				boss.setDirectionY(0);
			} else if (boss.getDirectionX() == 1 && boss.getX() == 600) {
				boss.setDirectionY(-1);
				boss.setDirectionX(0);
			} else if (boss.getDirectionY() == 1 && boss.getY() == 400) {
				boss.setDirectionX(1);
				boss.setDirectionY(0);
			}
		} else if (boss.life < 11 && boss.life > 0) {
			if(once == 1){
				int moveDirectionX = rand.nextInt(3) - 1;
				int moveDirectionY = rand.nextInt(3) - 1;
				while (moveDirectionX == 0 || moveDirectionY == 0) {
					moveDirectionX = rand.nextInt(3) - 1;
					moveDirectionY = rand.nextInt(3) - 1;
				}
				boss.setDirectionX(moveDirectionX);
				boss.setDirectionY(moveDirectionY);
				once--;
			}
			if(outOfStageX(boss)){
				boss.setDirectionX(boss.getDirectionX()*-1);
			}else if(outOfStageY(boss)){
				boss.setDirectionY(boss.getDirectionY()*-1);
			}
		}
		if (boss.life < 6) {
			spawnDelay = 10;
			boss.setSpeed(3);
		}
		if (boss.getDirectionX() == -1) {
			boss.setX(boss.getX() - boss.getSpeed());
		} else if (boss.getDirectionX() == 1) {
			boss.setX(boss.getX() + boss.getSpeed());
		}
		if (boss.getDirectionY() == -1) {
			boss.setY(boss.getY() - boss.getSpeed());
		} else if (boss.getDirectionY() == 1) {
			boss.setY(boss.getY() + boss.getSpeed());
		}
	}

	private boolean outOfStageX(Entity b) {
		return b.getX() < 50 || b.getX() + b.getWidth() > 750;
	}

	private boolean outOfStageY(Entity b) {
		return b.getY() < 50 || b.getY() + b.getHeight() > 550;
	}

	private void spawnBullet() {
		if (timer % spawnDelay == 0 && !player.isDestroy() && !boss.isDestroy()) {
			int colorType = rand.nextInt(3);
			int moveSpeedX = rand.nextInt(3);
			int moveSpeedY = rand.nextInt(3);
			while (moveSpeedX == 0 && moveSpeedY == 0) {
				moveSpeedX = rand.nextInt(3);
				moveSpeedY = rand.nextInt(3);
			}
			int moveDirectionX = rand.nextInt(3) - 1;
			int moveDirectionY = rand.nextInt(3) - 1;
			while (moveDirectionX == 0 && moveDirectionY == 0) {
				moveDirectionX = rand.nextInt(3) - 1;
				moveDirectionY = rand.nextInt(3) - 1;
			}
			addEntity(new Bullet(boss.getX() + boss.getWidth() / 2, boss.getY() + boss.getHeight() / 2, colorType,
					moveSpeedX, moveSpeedY, moveDirectionX, moveDirectionY));
		}
	}

	private void decreaseDelay() {
		if (timer % 10 == 0) {
			if (player.delay > 0) {
				player.delay--;
			} else {
				player.setAttack(false);
			}

			if (slash.delay > 0) {
				slash.delay--;
			} else {
				for (IRenderable i : RenderableHolder.getInstance().getEntities()) {
					if (i instanceof Slash) {
						((Slash) i).setDestroy();
						slash = new Slash(1000, 1000, player);
					}
				}
			}

			if (boss.delay > 0) {
				boss.delay--;
			}
		}
	}

	public void receiveKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		if (!CodeUtility.keyPressed.contains(new_code)) {
			CodeUtility.keyPressed.add(new_code);
			CodeUtility.keyTriggered.add(new_code);
			if (new_code == KeyCode.J && !player.isAttack()) {
				player.colorType = (player.colorType + 1) % 3;
			}
			if (new_code == KeyCode.K && player.delay == 0 && !player.isDestroy()) {
				player.setAttack(true);
				player.delay = 5;
				if (player.faceDirection == 1) {
					slash = new Slash(player.getX() + player.getWidth(), player.getY() - 15, player);
				} else {
					slash = new Slash(player.getX() - 100, player.getY() - 15, player);
				}
				slash.setFaceDirection(player.faceDirection);
				slash.delay = 1;
				addEntity(slash);
				AudioClip slashh = new AudioClip(ClassLoader.getSystemResource("slash.m4a").toString());
				slashh.setVolume(0.05);
				slashh.play();
			}

		}
	}

	public void dropKey(KeyCode new_code) {
		// TODO Auto-generated method stub
		CodeUtility.keyPressed.remove(new_code);
		CodeUtility.keyTriggered.remove(new_code);
	}
}
