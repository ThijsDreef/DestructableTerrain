package game.GameObjects.Weapons;

import engine.Engine;

/**
 * Created by Thijs Dreef on 21/09/2017.
 */
public abstract class Weapon
{
  public int ammo;
  protected float xvel, yvel;
  protected int offx, offy;

  public abstract void shoot(Engine en, int x, int y, int w, int h);
  public abstract void addAmmo(int ammo);
  protected void setBulletVel(Engine en, int x, int y, int w, int h)
  {
    int mouseX = en.getInput().getMouseX() + en.getCamera().getTransx();
    int mouseY = en.getInput().getMouseY() + en.getCamera().getTransy();
    int dx = (x + w / 2) - mouseX;
    int dy = (y + h / 2) - mouseY;
    float mag = (float)Math.sqrt(dx * dx + dy * dy);
    xvel = -((dx / mag) * 2);
    yvel = -((dy / mag) * 2);
    offx = (xvel >= 0.3f) ? w : (xvel <= -0.3f) ? 0 : w / 2;
    offy = (yvel >= 0.3f) ? h : (yvel <= -0.3f) ? -1 : h / 2;
  }
}
