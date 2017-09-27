package game.States;

import engine.Engine;
import engine.Renderer;
import game.GameObjects.PerlinNoiseDisplay;
import game.Util.LevelGenerator;
import game.managers.GameObject;
import game.managers.ObjectManager;
import game.managers.State;

public class Play extends State
{
  public Play(int width, int height, int levelHeight, int levelWidth, GameObject player)
  {
    PerlinNoiseDisplay dis = new PerlinNoiseDisplay(width, height, (int)(Math.random() * 1024));
    dis.setColor();
    manager = new ObjectManager();
    LevelGenerator temp = new LevelGenerator();
    manager = temp.createLevel(dis.color, width, height, levelHeight, levelWidth, player);
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
