import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

enum Direction { UP, DOWN, LEFT, RIGHT }

public class GamePanel extends JPanel implements KeyListener {

    public TileMap map = new TileMap();
    public int cameraX;
    public int cameraY;
    public TileManager tileManager;
    int targetCameraX, targetCameraY;

    boolean upPressed, downPressed, leftPressed, rightPressed;

    BufferedImage[] walkDown, walkUp, walkLeft, walkRight;
    Direction playerDir = Direction.DOWN;
    int animFrame = 0;
    long lastFrameTime = 0;
    int frameDuration = 150;

    int playerScreenX, playerScreenY;

    Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        cameraX = 50 * GameSettings.TILE_SIZE - GameSettings.SCREEN_WIDTH / 2;
        cameraY = 50 * GameSettings.TILE_SIZE - GameSettings.SCREEN_HEIGHT / 2;
        targetCameraX = cameraX;
        targetCameraY = cameraY;

        tileManager = new TileManager();
        this.addKeyListener(this);

        try {
            walkDown = loadSpritesheet("src/img/character/Seperate Spritesheets/Walk Down.png");
            walkUp = loadSpritesheet("src/img/character/Seperate Spritesheets/Walk Up.png");
            walkLeft = loadSpritesheet("src/img/character/Seperate Spritesheets/Walk Left.png");
            walkRight = loadSpritesheet("src/img/character/Seperate Spritesheets/Walk Right.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (walkDown != null && walkDown.length > 0) {
            playerScreenX = GameSettings.SCREEN_WIDTH / 2 - walkDown[0].getWidth() / 2;
            playerScreenY = GameSettings.SCREEN_HEIGHT / 2 - walkDown[0].getHeight() / 2;
        }

        timer = new Timer(15, e -> {
            int speed = 5;

            if (upPressed) targetCameraY -= speed;
            if (downPressed) targetCameraY += speed;
            if (leftPressed) targetCameraX -= speed;
            if (rightPressed) targetCameraX += speed;

            if (cameraX < targetCameraX) cameraX = Math.min(cameraX + speed, targetCameraX);
            else if (cameraX > targetCameraX) cameraX = Math.max(cameraX - speed, targetCameraX);

            if (cameraY < targetCameraY) cameraY = Math.min(cameraY + speed, targetCameraY);
            else if (cameraY > targetCameraY) cameraY = Math.max(cameraY - speed, targetCameraY);

            long currentTime = System.currentTimeMillis();
            if (upPressed || downPressed || leftPressed || rightPressed) {
                if (currentTime - lastFrameTime > frameDuration) {
                    animFrame = (animFrame + 1) % walkDown.length;
                    lastFrameTime = currentTime;
                }
            } else {
                animFrame = 0;
            }

            repaint();
        });
        timer.start();
    }

    private BufferedImage[] loadSpritesheet(String path) throws IOException {
        BufferedImage sheet = ImageIO.read(new File(path));
        int frameCount = 12;
        int frameWidth = sheet.getWidth() / frameCount;
        int frameHeight = sheet.getHeight();
        BufferedImage[] frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = sheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
        return frames;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2, map, cameraX, cameraY, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);

        BufferedImage[] currentAnim;
        switch (playerDir) {
            case UP: currentAnim = walkUp; break;
            case DOWN: currentAnim = walkDown; break;
            case LEFT: currentAnim = walkLeft; break;
            case RIGHT: currentAnim = walkRight; break;
            default: currentAnim = walkDown;
        }

        if (currentAnim != null && currentAnim.length > 0) {
            int scale = 3;
            int width = currentAnim[animFrame].getWidth() * scale;
            int height = currentAnim[animFrame].getHeight() * scale;
            g2.drawImage(currentAnim[animFrame], playerScreenX, playerScreenY, width, height, null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
            playerDir = Direction.UP;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
            playerDir = Direction.DOWN;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            playerDir = Direction.LEFT;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            playerDir = Direction.RIGHT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) upPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) leftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;
    }
}
