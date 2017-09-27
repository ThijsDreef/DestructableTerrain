package game.GameObjects.Enemies;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

/**
 * Created by Thijs Dreef on 27/09/2017.
 */
public class Particle extends GameObject
{
  int updateLimit = 120;
  public Particle(int x, int y, float xvel, float yvel)
  {
    this.x = x;
    this.y = y;
    this.xVel = xvel * 2;
    this.yVel = yvel * 2;
    w = 1;
    h = 1;
    tag = "destruct";
    destruct = true;
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateLimit--;
    x += xVel;
    y += yVel;
    if (updateLimit < 0)
      kill();
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.setPixels((int)x, (int)y, 0xffa00000, ShadowType.HALF);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {

  }

  @Override
  public void dispose()
  {

  }
}
