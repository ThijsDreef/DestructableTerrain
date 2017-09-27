package game.GameObjects.Crates;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import engine.physics.Collider;
import game.States.Play;
import game.managers.GameObject;

/**
 * Created by Thijs Dreef on 26/09/2017.
 */
public class NextLevelCrate extends GameObject
{
  private boolean nextLevel = false;
  private GameObject player;
  public NextLevelCrate(int x, int y, GameObject player)
  {
    this.player = player;
    this.x = x;
    this.y = y;
    this.w = 32;
    this.h = 32;
    tag = "destruct";
    destruct = true;
    addComponent(new Collider("nextLevel", this));
  }
  @Override
  public void update(Engine en, float dt)
  {
    updateComponents(en, dt);
    if (nextLevel)
      en.getGame().changeToState(new Play(720, 720, 720 * 4, 720 * 4, player));
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    r.drawRect((int)x, (int)y, (int)w, (int)h, 0xff303030, ShadowType.FADE);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {
    if (name.equals("player"))
      nextLevel = true;
  }

  @Override
  public void dispose()
  {

  }
}
