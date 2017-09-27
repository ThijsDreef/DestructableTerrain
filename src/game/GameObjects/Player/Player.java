package game.GameObjects.Player;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.GameObjects.Weapons.*;
import game.GameObjects.Weapons.Projectiles.Bomb;
import game.States.Menu;
import game.managers.GameObject;

import java.awt.event.KeyEvent;

public class Player extends GameObject
{
  private int weapon = 0, noAmmoWeapons = 2;
  private String weapons [] = new String[5];
  private int bombs;
  private Weapon weaponObjects[] = new Weapon[5];
  private int sprintTimer = 80;
  private boolean sprinting;
  private int weaponSwitch = 0;
  private int health = 3;
  private int invisTimer = 0;
  private boolean killed = false;

  public Player(int x, int y)
  {
    sprinting = false;
    weaponObjects[0] = new Pistol();
    weaponObjects[1] = new Knife();
    weaponObjects[2] = new MiniGun();
    weaponObjects[3] = new Splitter();
    weaponObjects[4] = new ShotGun();
    for (int i = 0; i < weaponObjects.length; i++)
      weapons[i] = weaponObjects[i].getClass().getSimpleName();

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
    x += xVel;
    y += yVel;
    invisTimer--;
    updateComponents(en, dt);
    if (killed)
      en.getGame().changeToState(new Menu());
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    en.getCamera().setTransx((int)(x - en.getWidth() / 2 + 16));
    en.getCamera().setTransy((int)(y - en.getHeight() / 2 + 16));
    r.drawRect((int)x, (int)y, 32, 32, 0xffffffff, ShadowType.FADE);
    r.drawString(weapons[weapon], 0xff000000, en.getCamera().getTransx(), en.getCamera().getTransy() + 40);
    r.drawString("bombs: " + bombs, 0xff000000, en.getCamera().getTransx(), en.getCamera().getTransy() + 60);
    r.drawString("score: " + en.getGame().score, 0xff000000, en.getCamera().getTransx(), en.getCamera().getTransy() + 80);
    drawBars(en, r);
    if (weapon >= noAmmoWeapons)
      r.drawString(weaponObjects[weapon].ammo + "", 0xff000000, en.getCamera().getTransx() + 70, en.getCamera().getTransy() + 40);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    switch (name)
    {
      case "ammoCrate":
        if (health < 3)
          health++;
        if (weapon > noAmmoWeapons)
          weaponObjects[weapon].addAmmo(object.getState());
        else
          weaponObjects[noAmmoWeapons].addAmmo(object.getState());
        bombs = 3;
        break;
      case "enemyBullet":
        if (invisTimer < 0)
        {
          health -= 1;
          invisTimer = 60;
        }
        if (health <= 0)
          killed = true;
        break;
      case "enemy":
        if (invisTimer < 0)
        {
          health -= 1;
          invisTimer = 60;
        }
        if (health <= 0)
          killed = true;
        break;
      case "nextLevel":
        setState(getState() + 1);
        break;
    }
  }

  @Override
  public void dispose()
  {

  }
  private void changeWeapon(Engine en)
  {
    weaponSwitch += en.getInput().getMouseWheelScroll();
    if (weaponSwitch >= 1 || weaponSwitch <=-1)
    {
      weapon += weaponSwitch;
      if (weapon >= weapons.length)
        weapon = 0;
      if (weapon < 0)
        weapon = weapons.length -1;
      weaponSwitch = 0;
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
  private void drawBars(Engine en, Renderer r)
  {
    r.drawNonFilledRect(en.getCamera().getTransx(), en.getCamera().getTransy() + 1, 97, 17, 0xff000000, ShadowType.FADE);
    r.drawRect(en.getCamera().getTransx() + 1, en.getCamera().getTransy() + 2, 32 * health, 16, 0xff800000, ShadowType.FADE);
    r.drawNonFilledRect(en.getCamera().getTransx(), en.getCamera().getTransy() + 20, 97, 17, 0xff000000, ShadowType.FADE);
    r.drawRect(en.getCamera().getTransx() + 1, en.getCamera().getTransy() + 21, (int)(96 * ((float)sprintTimer / 120)), 16, 0xff008000, ShadowType.FADE);
  }
  private void move(Engine en)
  {
    int sprintSpeed;
    if (en.getInput().isKeyPressed(KeyEvent.VK_SHIFT) && sprintTimer > 100 || en.getInput().isKeyPressed(KeyEvent.VK_SHIFT) && sprinting)
    {
      sprintTimer -= 3;
      sprintSpeed = 2;
      sprinting = sprintTimer > 0;
    }
    else
    {
      sprinting = false;
      sprintSpeed = 1;
      sprintTimer++;
      if (sprintTimer > 120)
      {
        sprintTimer = 120;
      }
    }
    if (en.getInput().isKeyPressed(KeyEvent.VK_W))
      yVel = -2 * sprintSpeed;
    else if (en.getInput().isKeyPressed(KeyEvent.VK_S))
      yVel = 2 * sprintSpeed;
    else
      yVel = 0;
    if (en.getInput().isKeyPressed(KeyEvent.VK_A))
      xVel = -2 * sprintSpeed;
    else if (en.getInput().isKeyPressed(KeyEvent.VK_D))
      xVel = 2 * sprintSpeed;
    else
      xVel = 0;
  }
}
