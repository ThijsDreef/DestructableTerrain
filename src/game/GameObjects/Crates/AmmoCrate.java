package game.GameObjects.Crates;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class AmmoCrate extends GameObject
{
  public AmmoCrate(int x, int y, int ammo)
  {
    this.x = x;
    this.y = y;
    setState(ammo);
    this.w = 32;
    this.h = 32;
    System.out.println(ammo);
    addComponent(new Collider("ammoCrate", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff80ff80, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (name.equals("player"))
      setDead(true);
  }

  @Override
  public void dispose()
  {

  }
}
