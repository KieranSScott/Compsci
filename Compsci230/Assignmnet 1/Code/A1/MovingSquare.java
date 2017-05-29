/* Kieran Sean Scott
   UPI: ksco491
   ID: 439868967
   This is the MovingShape class for A1
*/

import java.awt.*;

class MovingSquare extends MovingRectangle {
  private int area;
  public MovingSquare() {
    super();
  }
  public MovingSquare(int x, int y, int mw, int mh, Color c, Color fc, int pathType, int width, int height) {
        super(x, y, mw, mh, c, fc, pathType, width, height);
  }
  public void setWidth(int width) {
    this.width = width;
    this.height = width;
  }

  public void setHeight(int height) {
    this.height = height;
    this.width = height;
  }
}
