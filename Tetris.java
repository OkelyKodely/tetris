import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Component;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;
import java.util.logging.*;
import java.util.Arrays;
import java.net.URL;

public class Tetris implements KeyListener {

    public Tetris() {

        gameFrame.setLayout(null);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        int width = dimension.width;

        gameFrame.setLocation(width/2-225, 0);

        gameFrame.setSize(600, 790);

        gamePanel.setLocation(150, 0);

        gamePanel.setSize(450, 790);

        gameFrame.add(gamePanel);

        panel.setLocation(0, 0);

        panel.setBackground(Color.white);

        panel.setSize(150, 790);

        gameFrame.add(panel);

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.setResizable(false);

        gameFrame.addKeyListener(this);

        gameFrame.setVisible(true);

        linesLbl.setLocation(10, 70);

        linesLbl.setSize(150, 20);

        JLabel fruits1 = new JLabel("SUPER TETRIS");

        JLabel fruits2 = new JLabel("do do do dodo do do do!");

        fruits1.setLocation(10, 100);

        fruits2.setLocation(10, 120);
        
        fruits1.setFont(new Font("arial", Font.ITALIC, 12));

        fruits2.setFont(new Font("arial", Font.ITALIC, 10));

        panel.add(fruits1);

        panel.add(fruits2);

        panel.add(linesLbl);
    }

    private int lines = 0;

    private JLabel linesLbl = new JLabel("Lines: " + lines);

    private int board[][] = new int[17][10];

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    private Piece piece;

    private int delay = 2000;

    private JFrame gameFrame = new JFrame("SUPER TETRIS");

    private GamePanel gamePanel = new GamePanel();

    private JPanel panel = new JPanel();

    private boolean isNotDown(Piece piece) {
       if(piece.blocks.get(0).y == 16
                ||
                piece.blocks.get(1).y == 16
                ||
                piece.blocks.get(2).y == 16
                ||
                piece.blocks.get(3).y == 16) {
           return false;
        }
        return true;
    }

    public boolean juxtaposedLeftSideways(ArrayList<Piece> pieces) {
        for(Piece piece : pieces) {
            if(piece.setType.equals("current")) {
                for(int i=0; i<piece.blocks.size(); i++) {
                    for(Piece p : pieces) {
                        if(p.setType.equals("")) {
                            for(int j=0; j<p.blocks.size(); j++) {
                                if((piece.blocks.get(i).x + 1 == p.blocks.get(j).x && piece.blocks.get(i).y == p.blocks.get(j).y)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean juxtaposedRightSideways(ArrayList<Piece> pieces) {
        for(Piece piece : pieces) {
            if(piece.setType.equals("current")) {
                for(int i=0; i<piece.blocks.size(); i++) {
                    for(Piece p : pieces) {
                        if(p.setType.equals("")) {
                            for(int j=0; j<p.blocks.size(); j++) {
                                if((piece.blocks.get(i).x == p.blocks.get(j).x + 1 && piece.blocks.get(i).y == p.blocks.get(j).y)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean juxtaposedTopways(ArrayList<Piece> pieces) {
        for(Piece piece : pieces) {
            if(piece.setType.equals("current")) {
                for(int i=0; i<piece.blocks.size(); i++) {
                    for(Piece p : pieces) {
                        if(p.setType.equals("")) {
                            for(int j=0; j<p.blocks.size(); j++) {
                                if((piece.blocks.get(i).y + 1 == p.blocks.get(j).y && piece.blocks.get(i).x == p.blocks.get(j).x) || (piece.blocks.get(i).y == p.blocks.get(j).y + 1 && piece.blocks.get(i).x == p.blocks.get(j).x)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void play() {

        linesLbl.setText("Lines: " + lines);

        for(int i=0; i<this.board.length; i++) {
            for(int j=0; j<this.board[i].length; j++) {
                this.board[i][j] = 0;
            }
        }

        String type = null;

        Random rnd = new Random();

        int val = rnd.nextInt(7);

        switch(val) {

            case 0:

                type = "line";

                break;

            case 1:

                type = "square";

                break;
            case 2:

                type = "LArm";

                break;
            case 3:

                type = "RArm";

                break;
            case 4:

                type = "Hat";

                break;
            case 5:

                type = "LShoulder";

                break;
            case 6:

                type = "RShoulder";

                break;

        }


        piece = new Piece( "" + type );

        piece.setLocation(4, 0);

        piece.setType("current");

        gamePanel.drawPiece(piece);

        pieces.add(piece);
        
        
        while(true) {

            try {

                Thread.sleep(delay);

            } catch(Exception e) {}


            if(isNotDown(piece) && !juxtaposedTopways(pieces)) {

                piece.moveDown();

            }

            if(!isNotDown(piece)

                    ||

                    juxtaposedTopways(pieces) || reachedTopThePiece()) {


                if(reachedTopThePiece()) {
                    delay = 2000;
                    lines = 0;
                    for(int i=0; i<this.board.length; i++) {
                        for(int j=0; j<this.board[i].length; j++) {
                            this.board[i][j] = 0;
                        }
                    }
                    pieces.removeAll(pieces);
                    pieces.trimToSize();
                }


                String _type = null;


                Random _rnd = new Random();

                int _val = _rnd.nextInt(7);


                switch(_val) {

                    case 0:

                        _type = "line";

                        break;

                    case 1:

                        _type = "square";

                        break;
                    case 2:

                        _type = "LArm";

                        break;
                    case 3:

                        _type = "RArm";

                        break;
                    case 4:

                        _type = "Hat";

                        break;
                    case 5:

                        _type = "LShoulder";

                        break;
                    case 6:

                        _type = "RShoulder";

                        break;


                }

                this.board[piece.blocks.get(0).y][piece.blocks.get(0).x] = 1;

                this.board[piece.blocks.get(1).y][piece.blocks.get(1).x] = 1;

                this.board[piece.blocks.get(2).y][piece.blocks.get(2).x] = 1;

                this.board[piece.blocks.get(3).y][piece.blocks.get(3).x] = 1;

                piece.setType("");

                piece = new Piece( "" + _type );

                piece.setLocation(4, 0);

                piece.setType("current");

                pieces.add(piece);

            }

            clearBlocksWhenBlocksAreALine();

            linesLbl.setText("Lines: " + lines);

            this.redrawBlocks();

        }
    }

    private boolean reachedTopThePiece() {
        for(int i=0; i<piece.blocks.size(); i++) {
            for(int j=0; j<pieces.size(); j++) {
                for(int k=0; k<pieces.get(j).blocks.size(); k++) {
                    if(piece.blocks.get(0).y < 5 && pieces.get(j).setType.equals("") && pieces.get(j).blocks.get(k).y == piece.blocks.get(i).y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void clearBlocksWhenBlocksAreALine() {
        try {
            boolean needToClearLine = true;
            ArrayList<Integer> linesToClear = new ArrayList<Integer>();

            for(int y=0; y<17; y++) {
                for(int x=0; x<10; x++) {

                    if(this.board[y][x] != 1) {
                        needToClearLine = false;
                        break;
                    } else
                        needToClearLine = true;

                }
                if(needToClearLine == true) {

                    for(int i=0; i<linesToClear.size(); i++) {
                        if(linesToClear.get(i) == y) {
                            break;
                        }
                        else if(i == linesToClear.size() - 1) {
                            linesToClear.add(y);
                            lines++;
                            if(lines % 2 == 0)delay=delay-10;
                            if(delay < 600)delay=600;
                        }
                    }

                    if(linesToClear.isEmpty()) {

                        linesToClear.add(y);
                        lines++;
                        if(lines % 2 == 0)delay=delay-10;
                        if(delay < 600)delay = 600;
                    }

                }
            }

            for(int h=0; h<linesToClear.size(); h++) {
                for(int i=0; i<pieces.size(); i++) {

                    for(int j=0; j<pieces.get(i).blocks.size(); j++) {
                        if(pieces.get(i).blocks.get(j).y == linesToClear.get(h)) {

                            pieces.get(i).blocks.get(j).y = 1000;

                        }
                    }
                }
                for(int i=0; i<pieces.size(); i++) {

                    for(int j=0; j<pieces.get(i).blocks.size(); j++) {
                        if(pieces.get(i).blocks.get(j).y < linesToClear.get(h)) {

                            pieces.get(i).blocks.get(j).y++;

                        }
                    }

                }
            }

            for(int i=0; i<17; i++) {
                for(int j=0; j<10; j++) {
                    this.board[i][j] = 0;
                }
            }
            for(int i=0; i<pieces.size(); i++) {
                for(int j=0; j<pieces.get(i).blocks.size(); j++) {
                    if(pieces.get(i).blocks.get(j).y != 1000) {
                        this.board[pieces.get(i).blocks.get(j).y][pieces.get(i).blocks.get(j).x] = 1;
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException aioobe) {
        }
    }

    public void redrawBlocks() {

        gamePanel.paintComponent(gamePanel.getGraphics());

        gamePanel.setPanel(gamePanel);

        ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/tetris.jpg"));

        Image image = imageIcon.getImage();

        Graphics g = gamePanel.getGraphics();

        g.drawImage(image, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

        for(int i=0; i<pieces.size(); i++) {

            gamePanel.drawPiece(pieces.get(i));
        }
    }

    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            case KeyEvent.VK_LEFT :

                if(!juxtaposedRightSideways(pieces) && piece.blocks.get(0).x > 0 && piece.blocks.get(1).x > 0 && piece.blocks.get(2).x > 0 && piece.blocks.get(3).x > 0) {
                    piece.moveLeft();
                    this.redrawBlocks();
                }

                break;

            case KeyEvent.VK_RIGHT :

                if(!juxtaposedLeftSideways(pieces) && piece.blocks.get(0).x < 9 && piece.blocks.get(1).x < 9 && piece.blocks.get(2).x < 9 && piece.blocks.get(3).x < 9) {
                    piece.moveRight();
                    this.redrawBlocks();
                }

                break;

            case KeyEvent.VK_SPACE :

                while(isNotDown(piece) && !juxtaposedTopways(pieces)) {
                    piece.moveDown();
                }
                this.redrawBlocks();

                break;

            case KeyEvent.VK_DOWN :

                if(isNotDown(piece)) {

                    if(!juxtaposedTopways(pieces)) {

                        piece.moveDown();
                        this.redrawBlocks();
                    
                    }
                
                }

                break;

            case KeyEvent.VK_UP :

                piece.flip();
                this.redrawBlocks();

                break;

            case KeyEvent.VK_ESCAPE :

                System.exit(0);
                
                break;
                
        }

    }
    public static void main(String args[]) {
        Tetris tetris = new Tetris();
        tetris.play();
    }
    public void keyTyped(KeyEvent e){}public void keyReleased(KeyEvent e){}
}