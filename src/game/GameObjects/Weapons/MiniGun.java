package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.ParticleBullet;

/**
 * Created by Thijs Dreef on 21/09/2017.
 */
public class MiniGun extends Weapon
{
  public MiniGun()
  {
    ammo = 1000;
  }
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().mouseButtonPressed(1))
    {
      setBulletVel(en, x, y, w, h);
      if (ammo > 0)
      {
        for (int i = 0; i < 50; i++)
          en.getGame().peek().getManager().addObject(new ParticleBullet(x + offx, y + offy, (float) (xvel + (-0.5f + Math.random())) * 4, (float) (yvel + (-0.5f + Math.random())) * 4));
        ammo--;
      }
    }
  }

  @Override
  public void addAmmo(int ammo)
  {
    this.ammo += ammo;
  }
}
