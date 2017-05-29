/* Kieran Sean Scott
   UPI: ksco491
   ID: 439868967
   This is the MovingRectangle class for A1
*/



import java.awt.*;
class MovingRectangle extends MovingShape {
  private int area;
  public MovingRectangle() {
    super();
  }
  public MovingRectangle(int x, int y, int mw, int mh, Color c, Color fc, int pathType, int width, int height) {
    super(x, y, mw, mh, c, fc, pathType, width, height);
  }
  public boolean contains(Point p) {
    return(x <= p.x && p.x <= (x + width + 1) && y <= p.y && p.y <= (y + height + 1));
  }

  public void draw(Graphics g) {
    g.setColor(getFillColor());
    g.fillRect(getX(), getY(), getWidth(), getHeight());
    g.setColor(getBorderColor());
    g.drawRect(getX(), getY(), getWidth(), getHeight());
    drawHandles(g);
  }
  public int area() {
    area = getHeight() * getWidth();
    return area;
  }
}
