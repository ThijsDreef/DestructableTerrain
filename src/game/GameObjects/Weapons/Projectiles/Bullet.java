package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

public class Bullet extends GameObject
{
  public Bullet(int x, int y, int xVel, int yVel)
  {
    destruct = true;
    tag = "destruct";
    this.w = 14;
    this.h = 14;
    this.x = x - w/2;
    this.y = y - h/2;
    this.xVel = xVel * 2;
    this.yVel = yVel * 2;
  }
  @Override
  public void update(Engine en, float dt)
  {
    x += xVel * 3;
    y += yVel * 3;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff00ff00, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
