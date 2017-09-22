package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

public class KnifeBullet extends GameObject
{

  public KnifeBullet(int x, int y, int xvel, int yvel)
  {
    tag = "destruct";
    destruct = true;
    this.w = 18;
    this.h = 18;
    this.x = x - w/2;
    this.y = y - h/2;
    addComponent(new Collider("knife", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff00ff00, ShadowType.FADE);
    setDead(true);
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
