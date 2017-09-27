package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class ShotGunShell extends GameObject
{
  float xVelocity, yVelocity;
  int stoppingPower = 2;
  public ShotGunShell(int x, int y, float xVelocity, float yVelocity)
  {
    setState(30);
    tag = "destruct";
    destruct = true;
    this.x = x;
    this.y = y;
    this.w = 16;
    this.h = 16;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    addComponent(new Collider("shell", this));
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
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff500050, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (name.equals("collisionChecker"))
    {
      stoppingPower--;
      if (stoppingPower < 0)
        setDead(true);
    }
    else if(!name.equals("player") && !name.equals("shell"))
      setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
