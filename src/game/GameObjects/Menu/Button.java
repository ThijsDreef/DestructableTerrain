package game.GameObjects.Menu;

import engine.Engine;
import engine.Fx.ShadowType;
import engine.Renderer;
import game.managers.GameObject;

public class Button extends GameObject
{
  public String text;
  int width;
  int color, highlitedColor;
  public Button(String text, int color, int highlitedColor, int x, int y)
  {
    this.x = x;
    this.y = y;
    this.text = text;
    this.color = color;
    this.highlitedColor = highlitedColor;
  }
  @Override
  public void update(Engine en, float dt)
  {

  }

  @Override
  public void render(Engine en, Renderer r)
  {
    center(en.getWidth());
    if (isClicked(en))
      width = r.drawLargeString(text, highlitedColor, (int)x, (int)y, ShadowType.FADE);
    else
      width = r.drawLargeString(text, color, (int)x, (int)y, ShadowType.FADE);

  }

  @Override
  public void componentEvent(String name, GameObject object, String axis)
  {

  }

  @Override
  public void dispose()
  {

  }
  public boolean isClicked(Engine en)
  {
    int mouseX = en.getInput().getMouseX();
    int mouseY = en.getInput().getMouseY();
    return (mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + 42);
  }
  private void center(int screenWidth)
  {
    x = screenWidth / 2 - width /2;
  }
}
