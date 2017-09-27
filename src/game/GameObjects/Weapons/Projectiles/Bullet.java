package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class Bullet extends GameObject
{
  float xvel, yvel;
  public Bullet(int x, int y, float xVel, float yVel)
  {
    destruct = true;
    tag = "destruct";
    setState(50);
    this.w = 14;
    this.h = 14;
    this.x = x - w/2;
    this.y = y - h/2;
    this.xvel = xVel * 2;
    this.yvel = yVel * 2;
    addComponent(new Collider("bullet", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    x += xvel * 3;
    y += yvel * 3;
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff00ff00, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (!name.equals("player"))
      setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
