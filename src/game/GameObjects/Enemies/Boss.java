package game.GameObjects.Enemies;

import engine.Engine;
import engine.Fx.Pixel;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.GameObjects.Crates.NextLevelCrate;
import game.GameObjects.Weapons.Projectiles.EnemyBullet;
import game.managers.GameObject;

public class Boss extends GameObject
{
  private int health, searchRadius = 350, maxHealth;
  private GameObject player;
  private float bulletspeed = 1;
  private int shootCooldown = 0;
  private int color = 0xff000000;
  private boolean killed = false;
  public Boss(int x, int y, int w, int h, int health, GameObject player)
  {
    this.player = player;
    this.health = health + 1000 * player.getState();
    this.maxHealth = health;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    tag = "destruct";
    destruct = true;
    addComponent(new Collider("boss", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (x - searchRadius < player.getX() && x + searchRadius > player.getX() && y - searchRadius < player.getY() && y + searchRadius >player.getY() && shootCooldown < 0)
    {
      en.getGame().peek().getManager().addObject(new EnemyBullet((int)(x + w / 2), (int)(y + h / 2), (int)(player.getX() + player.getW() / 2), (int)(player.getY() + player.getH() / 2), (int)bulletspeed));
      shootCooldown = 12;
    }
    if (xVel < 0.4f && xVel > -0.4f)
      xVel = ((float)Math.random() * 2 - 1) * 5;
    if (yVel < 0.4f && yVel > -0.4f)
      yVel = ((float)Math.random() * 2 - 1) * 5;
    x += xVel;
    y += yVel;
    xVel *= 0.99f;
    yVel *= 0.99f;
    updateComponents(en, dt);
    shootCooldown--;
    if (killed)
    {
      for (int i = 0; i < 10000; i++)
        en.getGame().peek().getManager().addObject(new Particle((int)(x + w / 2), (int)(y + h / 2), (float)Math.random() * 2 - 1 , (float)Math.random() * 2 -1));
      en.getGame().peek().getManager().addObject(new NextLevelCrate((int)(x + w / 2), (int)(y + h / 2), player));
      kill();
    }
    if (isDead())
      en.getGame().score += 200;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, color, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (name.equals("wall"))
    {
      x -= xVel;
      y -= yVel;
      yVel = - yVel;
      xVel = - xVel;
    }
    if (!name.equals("enemyBullet") && !name.equals("ammoCrate"))
    {
      health -= object.getState();
      float colorStrength = 1 - health / (float)maxHealth;
      color = Pixel.getColor(1, colorStrength, 0, 0);
      if (object.getState() != 0)
      {
        bulletspeed *= 1.01f;
        if (bulletspeed > 5)
          bulletspeed = 5;
      }
      if (health <= 0)
        killed = true;
    }
  }

  @Override
  public void dispose()
  {

  }
}