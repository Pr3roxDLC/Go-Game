import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;


public class Gui extends JFrame implements Runnable {

    public static int WIDTH = 1920, HEIGHT = 1080;

    public static Image dbImage;
    public static Graphics dbg;

    public static BufferedImage board = FileUtils.loadImage("/Go_board.png");

    public static int boardBeginningX, boardBeginningY = 0;

    public Gui() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        init();
        while (true) {
            onTick();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void onTick() {
        repaint();
    }


    @Override
    public void paint(Graphics g) {
        //Double Buffered Graphics Init
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        dbg.setColor(Color.RED);
        dbg.fillRect(0, 0, WIDTH, HEIGHT);

        dbg.drawImage(board, (int) ((getWidth() / 2) - (board.getWidth() / 2)), (int) ((getHeight() / 2) - (board.getHeight() / 2)), null);

        dbg.setColor(Color.BLACK);
        for (int x = 0; x < Main.stones.length; x++) {
            for (int y = 0; y < Main.stones[x].length; y++) {
                if (Main.stones[x][y] == null) continue;
                if (Main.stones[x][y].getColor() == Stone.Color.BLACK) {
                    dbg.setColor(Color.BLACK);
                    dbg.fillOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                } else {
                    dbg.setColor(Color.WHITE);
                    dbg.fillOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                    dbg.setColor(Color.BLACK);
                    dbg.drawOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                }
//                if (Main.stones[x][y].isCounted()) {
//                    dbg.setColor(Color.GREEN);
//                    dbg.drawOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
//                }
            }
        }

        //Draw Score
        dbg.setColor(Color.BLACK);
        dbg.setFont(new Font(dbg.getFont().getName(), 0, 50));
        dbg.drawString("Player One: " + Main.playerOne.points, 100, 100);
        dbg.setColor(Color.white);
        dbg.drawString("Player Two: " + Main.playerTwo.points, (1920 - dbg.getFontMetrics().stringWidth("Player Two: " + Main.playerTwo.points)) - 100, 100);

        //Swap Buffer
        g.drawImage(dbImage, 0, 0, null);

    }

    private void init() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        boardBeginningX = ((getWidth() / 2) - (board.getWidth() / 2)) + 42;
        boardBeginningY = ((getHeight() / 2) - (board.getHeight() / 2)) + 42;

        addMouseListener(ListenerFactory.getMouseListener());

        for (int x = 0; x < Main.stones.length; x++) {
            for (int y = 0; y < Main.stones[x].length; y++) {
                Main.points[x][y] = new Point(x * 30 + boardBeginningX, y * 30 + boardBeginningY);
            }
        }
        Main.board = new Rectangle(boardBeginningX, boardBeginningY, board.getWidth(), board.getHeight());
    }
}
