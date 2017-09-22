package game.States;

import engine.Engine;
import engine.Renderer;
import game.Util.LevelGenerator;
import game.managers.ObjectManager;
import game.managers.State;

public class Play extends State
{
  public Play(float[] values, int width, int height, int levelHeight, int levelWidth)
  {
    manager = new ObjectManager();
//    manager.addObject(new PerlinNoiseDisplay(720, 720));
    LevelGenerator temp = new LevelGenerator();
    manager = temp.createLevel(values, width, height, levelHeight, levelWidth);
//    manager.addObject(new Player(0, 0));
//    manager.addObject(new CollisionChecker(manager.findObject("player"), manager.getObjects("wall")));
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
