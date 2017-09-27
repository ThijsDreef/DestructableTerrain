package game.GameObjects.Enemies;

import engine.Engine;
import engine.Fx.Pixel;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.GameObjects.Weapons.Projectiles.EnemyBullet;
import game.managers.GameObject;

public class BoxEnemy extends GameObject
{
  private GameObject player;
  private int cooldown = 0;
  private int health = 500;
  private int color = 0xff000000;
  private boolean killed = false;
  public BoxEnemy(int x, int y, GameObject player)
  {
    this.player = player;
    this.x = x;
    this.y = y;
    this.w = 32;
    this.h = 32;
    addComponent(new Collider("enemy", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateComponents(en, dt);
    if (x - 350 < player.getX() && x + 350 > player.getX() && y - 350 < player.getY() && y + 350 >player.getY())
    {
      if (cooldown < 0)
      {
        en.getGame().peek().getManager().addObject(new EnemyBullet((int) (x + w / 2), (int) (y + h / 2), (int) (player.getX() + player.getW() / 2) + (int)(Math.random() * 128), (int) (player.getY() + player.getH() / 2) + (int)(Math.random() * 128), 5));
        cooldown = 20;
      }
    }
    if (killed)
    {
      for (int i = 0; i < 1000; i++)
        en.getGame().peek().getManager().addObject(new Particle((int)(x + w / 2), (int)(y + h / 2), (float)Math.random() * 2 - 1, (float)Math.random() * 2 - 1));
      en.getGame().score += 10;
      kill();
    }
    cooldown--;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, color, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (!name.equals("enemyBullet"))
    {
      health -= object.getState();
      float colorStrength = 1 - health / (float)500;
      color = Pixel.getColor(1, colorStrength, 0, 0);
      if (health <= 0)
        killed = true;
    }
  }

  @Override
  public void dispose()
  {

  }
}
