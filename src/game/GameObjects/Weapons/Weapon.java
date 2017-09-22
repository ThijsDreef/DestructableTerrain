package game.GameObjects.Weapons;

import engine.Engine;

/**
 * Created by Thijs Dreef on 21/09/2017.
 */
public abstract class Weapon
{
  public int ammo;
  public abstract void shoot(Engine en, int x, int y, int w, int h);
}
