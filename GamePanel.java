import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private Graphics g;
    public void drawPiece(Piece piece) {

        ArrayList<Block> blocks = piece.blocks;

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/square.jpg"));

        Image image = imageIcon.getImage();

        g = this.getGraphics();

        for(int i=0; i<blocks.size(); i++) {

            if(blocks.get(i) != null)
                g.drawImage(image, blocks.get(i).x * 30, blocks.get(i).y * 30, 30, 30, null);
        
        }
        
    }
    public void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
    }
}