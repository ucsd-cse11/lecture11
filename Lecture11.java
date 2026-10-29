import module java.desktop;

void main() throws Exception {
  var img = new BufferedImage(40, 30, BufferedImage.TYPE_INT_ARGB);
  Graphics2D g = img.createGraphics();
  g.setColor(Color.BLUE);
  g.fillRect(0, 0, 30, 30);
  ImageIO.write(img, "png", new File("out.png"));
}
