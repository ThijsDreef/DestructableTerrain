package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.managers.GameObject;

/**
 * Created by Thijs Dreef on 22/09/2017.
 */
public class SplittingBullet extends GameObject
{
  private float xvel, yvel;
  boolean hit = false;
  public SplittingBullet(int x, int y, int w, int h, float xvel, float yvel)
  {
    tag = "destruct";
    destruct = true;
    setState(10);
    this.w = w;
    this.h = h;
    this.x = x - w/2;
    this.y = y - h/2;
    if (w < 2 || h < 2)
      setDead(true);

    this.xvel = xvel * h / 2;
    this.yvel = yvel * w / 2;
    addComponent(new Collider("splittingBullet", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    x += xvel;
    y += yvel;
    updateComponents(en, dt);
    if (hit)
    {
      setDead(true);
      for (int i = 0; i < 4; i++)
        en.getGame().peek().getManager().addObject(new SplittingBullet((int)x, (int)y, (int)w / 2, (int)h / 2, (float)(Math.random() * 2 -1) * 3, (float)(Math.random() * 2 -1) * 3));
    }
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff000050, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (!name.equals("player") && !name.equals("splittingBullet"))
      hit = true;
  }

  @Override
  public void dispose()
  {

  }
}
