package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.Bullet;


public class Pistol extends Weapon
{
  private int cooldown = 10;
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().mouseButtonPressed(1))
    {
      if (cooldown < 0)
      {
        setBulletVel(en, x, y, w, h);
        en.getGame().peek().getManager().addObject(new Bullet(x + offx, y + offy, xvel, yvel));
        cooldown = 15;
      }

    }
    cooldown--;
  }

  @Override
  public void addAmmo(int ammo)
  {

  }
}
