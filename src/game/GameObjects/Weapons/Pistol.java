package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.Bullet;

import java.awt.event.KeyEvent;


public class Pistol extends Weapon
{
  private int cooldown = 10;
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().isKeyPressed(KeyEvent.VK_LEFT) || en.getInput().isKeyPressed(KeyEvent.VK_RIGHT) || en.getInput().isKeyPressed(KeyEvent.VK_UP) || en.getInput().isKeyPressed(KeyEvent.VK_DOWN))
    {
      if (cooldown < 0)
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
        en.getGame().peek().getManager().addObject(new Bullet(x + offx, y + offy, xvel, yvel));
        cooldown = 15;
      }

    }
    cooldown--;
  }
}
