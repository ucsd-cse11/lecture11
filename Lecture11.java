import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

void main() {
  BufferedImage img = new BufferedImage(40, 30, BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = img.createGraphics();
  g.setColor(Color.BLUE);
  g.fillRect(0, 0, 30, 30);
  saveImage(img, "out.png");
}

void saveImage(BufferedImage img, String filename) {
  try {
    ImageIO.write(img, "png", new File(filename));
  }
  catch(IOException e) {
    throw new UncheckedIOException("saveImage failed", e);
  }
}