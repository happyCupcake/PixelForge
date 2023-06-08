import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CoinCollectingGame extends JFrame implements KeyListener, ActionListener {

    private static final int WIDTH = 800;  // Width of the game window
    private static final int HEIGHT = 600; // Height of the game window
    private static final int DELAY = 16; // Delay in milliseconds (60 FPS)

    private boolean upKeyPressed;
    private boolean downKeyPressed;
    private boolean leftKeyPressed;
    private boolean rightKeyPressed;
    private boolean spaceKeyPressed;

    private int playerX;
    private int playerY;
    private int speed = 5;

    private ArrayList<Coin> coins;

    private BufferedImage spaceshipImage;
    private BufferedImage coinImage;

    public CoinCollectingGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Coin Collector Game");
        setResizable(false);
        setLocationRelativeTo(null);

        // Add key listener to the game window
        addKeyListener(this);

        playerX = WIDTH / 2 - 10;
        playerY = HEIGHT / 2 - 10;

        //coins
        coins = new ArrayList<>();

        // Load image
        try {
            // Load the player image from the resources folder
            URL imageUrl = getClass().getResource("/res/player.png");
            spaceshipImage = ImageIO.read(imageUrl);

            // Load the coin image from the resources folder
            URL coinImageUrl = getClass().getResource("/res/coin.png");
            coinImage = ImageIO.read(coinImageUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up the game panel
        GamePanel gamePanel = new GamePanel();
        getContentPane().add(gamePanel);

        // Add key listener to the game panel
        gamePanel.addKeyListener(this);
        gamePanel.setFocusable(true);

        // Set up the game timer
        Timer timer = new Timer(DELAY, this);
        timer.start();

        setVisible(true);
    }

    private void updateGame() {
        // Update game logic here
        if (upKeyPressed) {
            playerY -= speed;
        }
        if (downKeyPressed) {
            playerY += speed;
        }
        if (leftKeyPressed) {
            playerX -= speed;
        }
        if (rightKeyPressed) {
            playerX += speed;
        }
        if (spaceKeyPressed) {
            // Handle shooting
        }
    }

    private void renderGame(Graphics2D g) {
        // Render game objects here
        // Clear the screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw spaceship
        g.drawImage(spaceshipImage, playerX, playerY, null);
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            renderGame(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                upKeyPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downKeyPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightKeyPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spaceKeyPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                upKeyPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downKeyPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftKeyPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightKeyPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spaceKeyPressed = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CoinCollectingGame::new);
    }
}
