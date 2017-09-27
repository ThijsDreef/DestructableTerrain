package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.KnifeBullet;

public class Knife extends Weapon
{
  public Knife()
  {

  }
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().mouseButtonPressed(1))
    {
      setBulletVel(en, x, y, w, h);
      en.getGame().peek().getManager().addObject(new KnifeBullet(x + offx, y + offy));
    }
  }

  @Override
  public void addAmmo(int ammo)
  {

  }
}
