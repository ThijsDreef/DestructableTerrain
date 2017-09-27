package game.GameObjects;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

import java.util.ArrayList;


public class CollisionChecker extends GameObject
{
  ArrayList<GameObject> destructableObjects;
  boolean[] walls;
  int levelHeight, levelWidth;
  public CollisionChecker(ArrayList<GameObject> destructableObjects, boolean[] walls, int levelHeight, int levelWidth)
  {
    this.destructableObjects = destructableObjects;
    this.walls = walls;
    this.levelHeight = levelHeight;
    this.levelWidth = levelWidth;
  }
  @Override
  public void update(Engine en, float dt)
  {
    destructableObjects = en.getGame().peek().getManager().getObjects("destruct");
    checkCollision();
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    for (int x = en.getCamera().getTransx(); x < en.getWidth() + en.getCamera().getTransx(); x++)
    {
      for (int y = en.getCamera().getTransy(); y < en.getHeight() + en.getCamera().getTransy(); y++)
      {
        if (outOfBounds(x, y))
          continue;
        else if (walls[x + y * levelWidth])
          r.setPixels(x, y, 0xff505050, ShadowType.HALF);
      }
    }
  }

  private boolean outOfBounds(int x, int y)
  {
    return (x < 0 || y < 0 || x + y * levelWidth >= walls.length || y > levelHeight || x > levelWidth);
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {

  }

  @Override
  public void dispose()
  {

  }

  private void checkCollision()
  {
    for (int i = 0; i < destructableObjects.size(); i++)
    {
      boolean hit = false;
      for (int y = 0; y < destructableObjects.get(i).getW(); y++)
      {
        for (int x = 0; x < destructableObjects.get(i).getH(); x++)
        {
          if (outOfBounds(((int)destructableObjects.get(i).getX() + x), (int)destructableObjects.get(i).getY() + y))
          {
            if (destructableObjects.get(i).destruct)
            {
              destructableObjects.get(i).setDead(true);
            }
            else
              resolve(destructableObjects.get(i));
            continue;
          }
          if (walls[((int)destructableObjects.get(i).getX() + x) + ((int)destructableObjects.get(i).getY() + y) * levelWidth])
          {
            hit = true;
            if (destructableObjects.get(i).destruct)
              walls[((int) destructableObjects.get(i).getX() + x) + ((int) destructableObjects.get(i).getY() + y) * levelWidth] = false;
            else
            {
              resolve(destructableObjects.get(i));
              x = 0;
              y = 0;
            }
          }
        }
      }
      if (hit)
        destructableObjects.get(i).componentEvent("collisionChecker", this, "");
    }
  }
  private void resolve(GameObject objectToResolve)
  {
    objectToResolve.setX(objectToResolve.getX() - objectToResolve.xVel / 2);
    objectToResolve.setY(objectToResolve.getY() - objectToResolve.yVel / 2);
  }
}
