import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Gui extends JFrame implements Runnable {

    private static final int WIDTH = 1920, HEIGHT = 1080;

    private static Image dbImage;
    private static Graphics dbg;

    private static final BufferedImage board = FileUtils.loadImage("/Go_board.png");


    private static int boardBeginningX, boardBeginningY = 0;

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
        ((Graphics2D)dbg).setStroke(new BasicStroke(1));

        dbg.drawImage(board, (getWidth() / 2) - (board.getWidth() / 2), (getHeight() / 2) - (board.getHeight() / 2), null);

        dbg.setColor(Color.BLACK);
        for (int x = 0; x < Main.getStones().length; x++) {
            for (int y = 0; y < Main.getStones()[x].length; y++) {
                if (Main.getStones()[x][y] == null) continue;
                if (Main.getStones()[x][y].getColor() == Stone.Color.BLACK) {
                    dbg.setColor(Color.BLACK);
                    dbg.fillOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                } else {
                    dbg.setColor(Color.WHITE);
                    dbg.fillOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                    dbg.setColor(Color.BLACK);
                    dbg.drawOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                }
                if (Main.getStones()[x][y].isCounted()) {
                    dbg.setColor(Color.GREEN);
                    dbg.drawOval(x * 30 + boardBeginningX - 7, y * 30 + boardBeginningY - 7, 20, 20);
                }
            }

        }

        //Draw Score
        dbg.setColor(Color.BLACK);
        dbg.setFont(new Font(dbg.getFont().getName(), Font.PLAIN, 50));
        dbg.drawString("Player One: " + Main.PLAYER_ONE.points, 100, 100);
        dbg.setColor(Color.white);
        dbg.drawString("Player Two: " + Main.PLAYER_TWO.points, (1920 - dbg.getFontMetrics().stringWidth("Player Two: " + Main.PLAYER_TWO.points)) - 100, 100);

        //Draw Buttons
        dbg.setColor(Main.SKIP_BUTTON.getColor());
        dbg.fillRect(Main.SKIP_BUTTON.getRectangle().x, Main.SKIP_BUTTON.getRectangle().y, Main.SKIP_BUTTON.getRectangle().width, Main.SKIP_BUTTON.getRectangle().height);
        dbg.setColor(Main.SKIP_BUTTON.getTextColor());
        int length = dbg.getFontMetrics().stringWidth(Main.SKIP_BUTTON.getText());
        dbg.drawString(Main.SKIP_BUTTON.getText(), (int) (Main.SKIP_BUTTON.getRectangle().getMaxX() - Main.SKIP_BUTTON.getRectangle().width / 2 - length/2), Main.SKIP_BUTTON.getRectangle().y + Main.SKIP_BUTTON.getRectangle().height / 2 + 15);
        ((Graphics2D)dbg).setStroke(new BasicStroke(10));
        dbg.drawRect(Main.SKIP_BUTTON.getRectangle().x, Main.SKIP_BUTTON.getRectangle().y, Main.SKIP_BUTTON.getRectangle().width, Main.SKIP_BUTTON.getRectangle().height);


        if(Main.GAME_STATE!= Main.State.ON_GOING){
            String text = "";
            switch (Main.GAME_STATE){
                case DRAW:
                    text = "Draw";
                    break;
                case PLAYER_ONE_WON:
                    text = "Player One Wins";
                    break;
                case PLAYER_TWO_WON:
                    text = "Player Two Wins";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Main.GAME_STATE);
            }
            dbg.setColor(Color.BLACK);
            dbg.fillRect(WIDTH/2 - 400, HEIGHT/2 - 200, 800, 400);
            dbg.setColor(Color.WHITE);
            dbg.drawString(text, WIDTH/2 - dbg.getFontMetrics().stringWidth(text) / 2, HEIGHT/2);

            dbg.setColor(Main.REPLAY_BUTTON.getColor());
            dbg.fillRect(Main.REPLAY_BUTTON.getRectangle().x, Main.REPLAY_BUTTON.getRectangle().y, Main.REPLAY_BUTTON.getRectangle().width, Main.REPLAY_BUTTON.getRectangle().height);
            dbg.setColor(Main.REPLAY_BUTTON.getTextColor());
            int length2 = dbg.getFontMetrics().stringWidth(Main.REPLAY_BUTTON.getText());
            dbg.drawString(Main.REPLAY_BUTTON.getText(), (int) (Main.REPLAY_BUTTON.getRectangle().getMaxX() - Main.REPLAY_BUTTON.getRectangle().width / 2 - length2/2), Main.REPLAY_BUTTON.getRectangle().y + Main.REPLAY_BUTTON.getRectangle().height / 2 + 15);
            ((Graphics2D)dbg).setStroke(new BasicStroke(10));
            dbg.drawRect(Main.REPLAY_BUTTON.getRectangle().x, Main.REPLAY_BUTTON.getRectangle().y, Main.REPLAY_BUTTON.getRectangle().width, Main.REPLAY_BUTTON.getRectangle().height);



        }

        //Swap Buffer
        g.drawImage(dbImage, 0, 0, null);

    }

    private void init() {
        setSize(WIDTH, HEIGHT);
        setTitle("Go");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        boardBeginningX = ((getWidth() / 2) - (board.getWidth() / 2)) + 42;
        boardBeginningY = ((getHeight() / 2) - (board.getHeight() / 2)) + 42;

        addMouseListener(ListenerFactory.getMouseListener());

        for (int x = 0; x < Main.getStones().length; x++) {
            for (int y = 0; y < Main.getStones()[x].length; y++) {
                Main.getPoints()[x][y] = new Point(x * 30 + boardBeginningX, y * 30 + boardBeginningY);
            }
        }
        Main.board = new Rectangle(boardBeginningX, boardBeginningY, board.getWidth(), board.getHeight());

        Main.SKIP_BUTTON = new Button(new Rectangle(WIDTH/2 - 200, 900, 400, 100), Color.BLACK, "Skip", Main::skip);
        Main.REPLAY_BUTTON = new Button(new Rectangle(WIDTH/2 - 200, 600, 400, 100), Color.WHITE, "New Game", Main::restartGame);
        Main.REPLAY_BUTTON.setActive(false);
    }
}
