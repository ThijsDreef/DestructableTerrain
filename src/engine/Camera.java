package engine;

/**
 * Created by Thijs Dreef on 18/01/2017.
 */
public class Camera
{
  private int newTransX = 0;
  private int newTransY = 0;
  private int transx = 0;
  private int transy = 0;
  public void update()
  {
    this.transx = newTransX;
    this.transy = newTransY;
  }
  public void addTransx(int addedX)
  {
    this.transx += addedX;
  }
  public void addTransy(int addedY)
  {
    this.transy += addedY;
  }
  public void subtractTransx(int subtractX)
  {
    this.transx -= subtractX;
  }
  public void subtractTransY(int subtractY)
  {
    this.transy -= subtractY;
  }
  public int getTransx() {
    return transx;
  }

  public void setTransx(int transx) {
    this.newTransX = transx;
  }

  public int getTransy() {
    return transy;
  }

  public void setTransy(int transy) {
    this.newTransY = transy;
  }
}
