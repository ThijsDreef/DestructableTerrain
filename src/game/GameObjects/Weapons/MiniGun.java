package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.ParticleBullet;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_RIGHT;

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
    if (en.getInput().isKeyPressed(KeyEvent.VK_LEFT) || en.getInput().isKeyPressed(VK_RIGHT) || en.getInput().isKeyPressed(KeyEvent.VK_UP) || en.getInput().isKeyPressed(KeyEvent.VK_DOWN))
    {
      int xvel = 0;
      int yvel = 0;
      int offx;
      int offy;
      if (en.getInput().isKeyPressed(KeyEvent.VK_LEFT))
        xvel--;
      else if (en.getInput().isKeyPressed(KeyEvent.VK_RIGHT))
        xvel++;
      if (en.getInput().isKeyPressed(KeyEvent.VK_UP))
        yvel--;
      else if (en.getInput().isKeyPressed(KeyEvent.VK_DOWN))
        yvel++;
      offx = (xvel == 1) ? w : (xvel == -1) ? 0 : w / 2;
      offy = (yvel == 1) ? h : (yvel == -1) ? -1 : h / 2;
      if (ammo > 0)
      {
        for (int i = 0; i < 50; i++)
          en.getGame().peek().getManager().addObject(new ParticleBullet((int) x + offx, (int) y + offy, (float) (xvel + (-0.5f + Math.random())) * 4, (float) (yvel + (-0.5f + Math.random())) * 4));
        ammo--;
      }
    }
  }
}
