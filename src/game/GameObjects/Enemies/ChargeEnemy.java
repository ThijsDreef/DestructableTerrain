package game.GameObjects.Enemies;

import engine.Engine;
import engine.Fx.Pixel;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class ChargeEnemy extends GameObject
{
  private GameObject player;
  private int chargeTime = 0, health = 1000, color = 0xff000000;
  private float xVelocity, yVelocity, speed;
  private final int searchRadius = 400;
  boolean killed = false;
  public ChargeEnemy(int x, int y, GameObject player)
  {
    speed = 4 + (float)Math.random();
    tag = "destruct";
    destruct = true;
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
    if (x - searchRadius < player.getX() && x + searchRadius > player.getX() && y - searchRadius < player.getY() && y + searchRadius >player.getY() && chargeTime < 0)
    {
      int dx = (int)((x + w / 2) - player.getX() + player.getW() / 2);
      int dy = (int)((y + h / 2) - player.getY() + player.getH() / 2);
      float mag = (float)Math.sqrt(dx * dx + dy * dy);
      xVelocity = -((dx / mag) * speed);
      yVelocity = -((dy / mag) * speed);
      chargeTime = (int)(60 + Math.random() * 60);
    }
    if (chargeTime > 0)
    {
      x += xVelocity;
      y += yVelocity;
    }
    chargeTime --;
    updateComponents(en, dt);
    if (killed)
    {
      for (int i = 0; i < 1000; i++)
        en.getGame().peek().getManager().addObject(new Particle((int)(x + w / 2), (int)(y + h / 2), (float)Math.random() * 2 - 1 , (float)Math.random() * 2 -1));
      en.getGame().score += 20;
      kill();
    }
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
      x -= xVelocity;
      y -= yVelocity;
      yVelocity = - yVelocity;
      xVelocity = - xVelocity;
    }
    if (name.equals("collisionChecker"))
    {
      xVelocity *= 0.90f;
      yVelocity *= 0.90f;
    }
    else if (!name.equals("enemyBullet") && !name.equals("ammoCrate"))
    {
      health -= object.getState();
      float colorStrength = 1 - health / (float)1000;
      color = Pixel.getColor(1, colorStrength, 0, 0);
      xVelocity *= 1.01f;
      yVelocity *= 1.01f;
      if (health <= 0)
        killed = true;
    }
  }

  @Override
  public void dispose()
  {

  }
}
