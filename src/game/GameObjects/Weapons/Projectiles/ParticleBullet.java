package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

import java.util.ArrayList;

public class ParticleBullet extends GameObject
{
  private float xVelocity, yVelocity;
  ArrayList<GameObject> walls =  new ArrayList<>();
  public ParticleBullet(int x, int y, float xVelocity, float yVelocity)
  {
    this.x = x;
    this.y = y;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    tag = "destruct";
    destruct = true;
    w = 1;
    h = 1;
  }
  @Override
  public void update(Engine en, float dt)
  {
    x += xVelocity;
    y += yVelocity;
    yVelocity *= 1.01;
    xVelocity *= 1.01;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.setPixels((int)x, (int)y, 0xff000000, ShadowType.HALF);
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
