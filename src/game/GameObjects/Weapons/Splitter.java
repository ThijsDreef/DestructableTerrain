package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.SplittingBullet;

public class Splitter extends Weapon
{
  private int cooldown = 10;
  public Splitter()
  {
    ammo = 32;
  }
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().mouseButtonPressed(1))
    {
      if (ammo > 0 && cooldown < 0)
      {
        setBulletVel(en, x, y, w, h);
        en.getGame().peek().getManager().addObject(new SplittingBullet(x + offx, y + offy, 32, 32, xvel, yvel));
        ammo--;
        cooldown = 45;
      }

    }
    cooldown--;
  }

  @Override
  public void addAmmo(int ammo)
  {
    this.ammo += ammo / 10;
  }
}
