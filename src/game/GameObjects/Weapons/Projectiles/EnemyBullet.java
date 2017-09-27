package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

/**
 * Created by Thijs Dreef on 22/09/2017.
 */
public class EnemyBullet extends GameObject
{
  float xVelocity, yVelocity;
  public EnemyBullet(int x, int y, int destX, int destY, int speed)
  {
    tag = "destruct";
    destruct = true;
    this.w = 16;
    this.h = 16;
    this.x = x - w / 2;
    this.y = y - h / 2;
    int dx = (x + (int)w / 2) - destX;
    int dy = (y + (int)h / 2) - destY;
    float mag = (float)Math.sqrt(dx*dx + dy * dy);
    this.xVelocity = -((dx / mag) * speed);
    this.yVelocity = -((dy / mag) * speed);
    addComponent(new Collider("enemyBullet", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    x += xVelocity;
    y += yVelocity;
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xffff0000, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (!name.equals("enemy") && !name.equals("boss") && !name.equals("enemyBullet"))
      setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
