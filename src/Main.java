import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        JFrame gameFrame = new JFrame("2D RPG-style Scrolling Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
    }

}
