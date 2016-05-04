import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel {

    private Graphics g;
    
    private GamePanel gamePanel;

    public void setPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public GamePanel getPanel() {
        return this.gamePanel;
    }

    public void drawPiece(Piece piece) {

        ArrayList<Block> blocks = piece.blocks;

        ImageIcon imageIcon1 = new ImageIcon(this.getClass().getResource("/square.jpg"));

        Image image1 = imageIcon1.getImage();

        g = this.getGraphics();

        for(int i=0; i<blocks.size(); i++) {

            if(blocks.get(i) != null)
                g.drawImage(image1, blocks.get(i).x * 30, blocks.get(i).y * 30, 30, 30, null);
        
        }
        
    }

    public void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);

    }
}