import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

interface Pict {
  int width();
  int height();
  void draw(Graphics2D g, int x, int y);
  public default BufferedImage getImg() { // NOT a recursive method
    BufferedImage img = new BufferedImage(width(), height(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = img.createGraphics();
    draw(g, 0, 0);
    return img;
  }
}

record Beside(Pict left, Pict right) implements Pict {
  public int width() { return left.width() + right.width(); }
  public int height() { return Math.max(left.height(), right.height()); }
  public void draw(Graphics2D g, int x, int y) {
    int middle = y + (height() / 2);
    left.draw(g, x, middle - (left.height() / 2));
    right.draw(g, x + left.width(), middle - (right.height() / 2));
  }
}

void main() {
  Rect r = new Rect(15, 15, Color.RED);
  Rect r2 = new Rect(30, 50, Color.BLUE);
  Pict combo = new Beside(r, r2);
  Pict combo2 = new Beside(combo, new Rect(20, 20, Color.GREEN));
  BufferedImage img = combo2.getImg();
  saveImage(img, "out.png");
}


record Rect(int width, int height, Color color) implements Pict {
  // the record *automatically* made width and height
  public void draw(Graphics2D g, int x, int y) {
    g.setColor(color);
    g.fillRect(x, y, width, height);
  }
}













void saveImage(BufferedImage img, String filename) {
  try {
    ImageIO.write(img, "png", new File(filename));
  }
  catch(IOException e) {
    throw new UncheckedIOException("saveImage failed", e);
  }
}