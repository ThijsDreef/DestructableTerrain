package game.Util;

import com.sun.javafx.geom.Vec2d;
import game.GameObjects.CollisionChecker;
import game.GameObjects.Crates.AmmoCrate;
import game.GameObjects.Enemies.Boss;
import game.GameObjects.Enemies.BoxEnemy;
import game.GameObjects.Enemies.ChargeEnemy;
import game.GameObjects.Player.Player;
import game.GameObjects.Wall;
import game.managers.GameObject;
import game.managers.ObjectManager;

import java.util.ArrayList;

public class LevelGenerator
{
  public ObjectManager createLevel(float[] values, int width, int height, int levelHeight, int levelWidth, GameObject player)
  {
    //setting up boandaries walls
    ObjectManager manager = new ObjectManager();
    manager.addObject(new Wall(-50, -50, levelWidth + 50, 50));
    manager.addObject(new Wall(-50, -50, 50, levelHeight + 50));
    manager.addObject(new Wall(levelWidth, -50, 50, levelHeight + 50));
    manager.addObject(new Wall(-50, levelHeight, levelWidth + 100, 50));
    //creating terrain
    boolean terrainMap[] = new boolean[levelHeight * levelWidth];
    for (int y = 0; y < height; y ++)
    {
      for (int x = 0; x < width; x++)
      {
        if (values[x + y * width] > -0.1f && values[x + y * width] < 0.1f)
        {
          for (int i = 0; i < levelWidth / width; i++)
            for (int j = 0; j < levelHeight / height; j++)
              terrainMap[i + x * levelWidth / width + (j + y * levelHeight / height) * levelWidth] = true;
        }
      }
    }
    //spawn location for player
    ArrayList<Vec2d> spawn = new ArrayList<>();
    for (int x = 32; x < levelWidth - 32; x += 32)
    {
      for (int y = 32; y < levelHeight - 32; y += 32)
      {
        if (!terrainMap[x + y * levelWidth])
        {
          boolean correct = true;
          for (int i = 0; i < 32; i ++)
            for (int j = 0; j < 32; j++)
              if (terrainMap[x + i + (y + j) * levelWidth])
              {
                correct = false;
                break;
              }
          if (correct)
            spawn.add(new Vec2d((double)x, (double)y));

        }
      }
    }
    manager.addObject(new CollisionChecker(manager.getObjects("destruct"), terrainMap, levelHeight, levelWidth));
    int index = (int)(Math.random() * spawn.size());
    if (player == null)
      player = new Player((int)spawn.get(index).x, (int)spawn.get(index).y);
    else
    {
      player.setX((int)spawn.get(index).x);
      player.setY((int)spawn.get(index).y);
    }
    for (int i = 0; i < 15; i++)
    {
      index = (int) (Math.random() * spawn.size());
      manager.addObject(new BoxEnemy((int) spawn.get(index).x, (int) spawn.get(index).y, player));
    }
    spawn.remove(index);
    for (int i = 0; i < 7; i++)
    {
      index = (int) (Math.random() * spawn.size());
      manager.addObject(new AmmoCrate((int) spawn.get(index).x, (int) spawn.get(index).y, (int) (Math.random() * 250) + 50));
      spawn.remove(index);
    }
    for (int i = 0; i < 10; i++)
    {
      index = (int) (Math.random() * spawn.size());
      manager.addObject(new ChargeEnemy((int) spawn.get(index).x, (int) spawn.get(index).y, player));
      spawn.remove(index);
    }
    index = (int) (Math.random() * spawn.size());

    manager.addObject(new Boss((int)spawn.get(index).x, (int)spawn.get(index).y, 64, 64, 10000, player));
    manager.addObject(player);
    return manager;
  }
}
