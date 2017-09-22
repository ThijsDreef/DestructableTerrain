package game.GameObjects.Player;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.GameObjects.Weapons.MiniGun;
import game.GameObjects.Weapons.Pistol;
import game.GameObjects.Weapons.Projectiles.Bomb;
import game.GameObjects.Weapons.Knife;
import game.GameObjects.Weapons.Weapon;
import game.managers.GameObject;

import java.awt.event.KeyEvent;

public class Player extends GameObject
{
  private int weapon = 0, noAmmoWeapons = 2;
  private String weapons [] = {"pistol", "knife", "minigun"};
  private int bombs;
  private Weapon weaponObjects[] = new Weapon[3];
  public Player(int x, int y)
  {
    weaponObjects[0] = new Pistol();
    weaponObjects[1] = new Knife();
    weaponObjects[2] = new MiniGun();
    weapons[0] = weaponObjects[0].getClass().toString();

    bombs = 3;
    this.x = x;
    this.y = y;
    h = 32;
    w = 32;
    tag = "destruct";
    addComponent(new Collider("player", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    changeWeapon(en);
    move(en);
    bomb(en);
    weaponObjects[weapon].shoot(en, (int)x, (int)y, (int)w, (int)h);
    en.getCamera().setTransx((int)(x - en.getWidth() / 2 + 16));
    en.getCamera().setTransy((int)(y - en.getHeight() / 2 + 16));

    x += xVel;
    y += yVel;
    updateComponents(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
      r.drawRect((int)x, (int)y, 32, 32, 0xffffffff, ShadowType.FADE);
      r.drawLargeString(weapons[weapon], 0xff000000, en.getCamera().getTransx(), en.getCamera().getTransy() + 20, ShadowType.FADE);
      r.drawLargeString("bombs: " + bombs, 0xff000000, en.getCamera().getTransx(), en.getCamera().getTransy() + 40, ShadowType.FADE);
      if (weapon >= 2)
        r.drawLargeString(weaponObjects[weapon].ammo + "", 0xff000000, en.getCamera().getTransx() + 70, en.getCamera().getTransy() + 20, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (name.equals("ammoCrate"))
    {
      if (weapon > 2)
        weaponObjects[weapon].ammo += object.getState();
      else
        weaponObjects[noAmmoWeapons].ammo += object.getState();
      bombs = 3;
    }
  }

  @Override
  public void dispose()
  {

  }
  private void changeWeapon(Engine en)
  {
    if (en.getInput().isKeyReleased(KeyEvent.VK_1))
    {
      weapon++;
      if (weapon >= weapons.length)
        weapon = 0;
    }
  }
  private void bomb(Engine en)
  {
    if (en.getInput().isKeyReleased(KeyEvent.VK_E))
    {
      if (bombs > 0)
      {
        en.getGame().peek().getManager().addObject(new Bomb((int) (x + w / 2), (int) (y + h / 2), 16, 16, 1, 90, 4));
        bombs--;
      }
    }
  }
  private void move(Engine en)
  {
    if (en.getInput().isKeyPressed(KeyEvent.VK_W))
      yVel = -2;
    else if (en.getInput().isKeyPressed(KeyEvent.VK_S))
      yVel = 2;
    else
      yVel = 0;
    if (en.getInput().isKeyPressed(KeyEvent.VK_A))
      xVel = -2;
    else if (en.getInput().isKeyPressed(KeyEvent.VK_D))
      xVel = 2;
    else
      xVel = 0;
  }
}
