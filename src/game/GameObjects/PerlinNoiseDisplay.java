package game.GameObjects;

import engine.Engine;
import engine.Fx.Pixel;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.Util.Perlin;
import game.managers.GameObject;

import java.awt.event.KeyEvent;

public class PerlinNoiseDisplay extends GameObject
{
  public float color[];
  float offset = 0;
  int width, height;
  public PerlinNoiseDisplay(int width, int height, int offset)
  {
    this.width = width;
    this.height = height;
    color = new float[width * height];
  }
  @Override
  public void update(Engine en, float dt)
  {
    if (en.getInput().isKeyPressed(KeyEvent.VK_W))
      offset += 0.02f;
//    if (en.getInput().isKeyPressed(KeyEvent.VK_SPACE))
//      en.getGame().changeToState(new Play(color, width, height, width * 4, height * 4));
  }

  @Override
  public void render(Engine en, Renderer r)
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        color[x + y * width] = (float) Perlin.noise(0.0072 * x + offset, 0.0072 * y + offset, 0.1);
        if (color[x + y * width] < 0)
          r.setPixels(x, y, Pixel.getColor(1,0, 0,- color[x + y * width]), ShadowType.FADE);
        else
          r.setPixels(x, y, Pixel.getColor(1, color[x + y * width], 0, 0), ShadowType.FADE);
      }
    }
  }

  public void setColor()
  {
    for (int x = 0; x < width; x++)
    {
      for (int y = 0; y < height; y++)
      {
        color[x + y * width] = (float) Perlin.noise(0.0072 * x + offset, 0.0072 * y + offset, 0.1);
      }
    }
  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {

  }

  @Override
  public void dispose()
  {

  }
}
