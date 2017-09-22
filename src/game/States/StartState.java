package game.States;

import engine.Engine;
import engine.Renderer;
import game.GameObjects.PerlinNoiseDisplay;
import game.managers.ObjectManager;
import game.managers.State;

/**
 * Created by Thijs Dreef on 15/09/2017.
 */
public class StartState extends State
{
  public StartState()
  {
    manager = new ObjectManager();
    manager.addObject(new PerlinNoiseDisplay(720, 720));
  }
  @Override
  public void update(Engine en, float dt)
  {
    manager.updateObjects(en, dt);
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    manager.renderObjects(en, r);
  }

  @Override
  public void dispose()
  {

  }
}
