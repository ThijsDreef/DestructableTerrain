package game.GameObjects;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

/**
 * Created by Thijs Dreef on 15/09/2017.
 */
public class Wall extends GameObject
{
  int width, height;
  public Wall(int x, int y, int width, int height)
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    w = width;
    h = height;
//    System.out.println(x + " " + y + " " + width + " " + height);
    tag = "wall";
    addComponent(new Collider("wall", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, width, height, 0xff00ff00, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
//    setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
