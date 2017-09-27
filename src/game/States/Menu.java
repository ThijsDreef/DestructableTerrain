package game.States;

import engine.Engine;
import engine.Renderer;
import game.GameObjects.Menu.Button;
import game.managers.State;

/**
 * Created by Thijs Dreef on 26/09/2017.
 */
public class Menu extends State
{
  Button start;
  public Menu()
  {
    start = new Button("START", 0xff000000, 0xffffffff, 0, 90);
  }
  @Override
  public void update(Engine en, float dt)
  {
    en.getCamera().setTransx(0);
    en.getCamera().setTransy(0);
    start.update(en, dt);
    if (start.isClicked(en) && en.getInput().mouseButtonPressed(1))
      en.getGame().changeToState(new Play(720, 720, 720 * 4, 720 * 4, null));
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    start.render(en, r);
  }

  @Override
  public void dispose()
  {

  }
}
