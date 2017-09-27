package game.GameObjects.Weapons.Projectiles;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

public class Bomb extends GameObject
{
  int strength, diffuseTimer, explosions;

  public Bomb(int x, int y, int w, int h, int strength, int diffuseTimer, int explosions)
  {
    this.x = x - w / 2;
    this.y = y - h / 2;
    this.w = w;
    this.h = h;
    this.strength = strength;
    this.diffuseTimer = diffuseTimer;
    this.explosions = (explosions <= 0) ? 1 : explosions;
  }

  @Override
  public void update(Engine en, float dt)
  {
    if (diffuseTimer < 0)
      explode(en);
    else
      diffuseTimer--;
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int) x, (int) y, (int) w, (int) h, 0xff800080, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {

  }

  @Override
  public void dispose()
  {

  }

  private void explode(Engine en)
  {
    for (int i = 0; i < strength * 5000; i++)
      en.getGame().peek().getManager().addObject(new ParticleBullet((int) x, (int) y, ((float) Math.random() * 2 - 1) * 3, ((float) Math.random() * 2 - 1) * 3));
    explosions--;
    if (explosions <= 0)
      setDead(true);
    diffuseTimer = 5;
  }
}

