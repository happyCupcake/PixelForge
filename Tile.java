import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {
    private int x; // X-coordinate of the tile
    private int y; // Y-coordinate of the tile
    public BufferedImage image; // Image of the tile
    public boolean collision = false;

    /*public Tile(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }*/

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
}
