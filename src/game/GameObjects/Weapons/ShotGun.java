package game.GameObjects.Weapons;

import engine.Engine;
import game.GameObjects.Weapons.Projectiles.ShotGunShell;

public class ShotGun extends Weapon
{
  private int cooldown = 20;
  public ShotGun()
  {
    ammo = 50;
  }
  @Override
  public void shoot(Engine en, int x, int y, int w, int h)
  {
    if (en.getInput().mouseButtonPressed(1) && cooldown < 0)
    {
      if (ammo > 0)
      {
        for (int i = 0; i < 8; i++)
        {
          int mouseX = en.getInput().getMouseX() + en.getCamera().getTransx();
          int mouseY = en.getInput().getMouseY() + en.getCamera().getTransy();
          int dx = (x + w / 2) - mouseX + (int)(Math.random() * 96 - 48);
          int dy = (y + h / 2) - mouseY + (int)(Math.random() * 96 - 48);
          float mag = (float)Math.sqrt(dx * dx + dy * dy);
          xvel = -((dx / mag) * 2);
          yvel = -((dy / mag) * 2);
          offx = (xvel >= 0.3f) ? w : (xvel <= -0.3f) ? 0 : w / 2;
          offy = (yvel >= 0.3f) ? h : (yvel <= -0.3f) ? -1 : h / 2;
          en.getGame().peek().getManager().addObject(new ShotGunShell(x + offx, y + offy, xvel * 5, yvel * 5));
        }
        cooldown = 30;
        ammo--;
      }

    }
    cooldown --;
  }

  @Override
  public void addAmmo(int ammo)
  {
    this.ammo += ammo / 6;
  }
}
