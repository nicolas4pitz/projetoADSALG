import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class Game extends Canvas {
    public static JFrame jframe;
    private Thread thread;
    private boolean isRunning;

    private static int WIDTH = 300;
    private static int HEIGHT = 400;
    private static int SCALE = 2;

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frameInit();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void frameInit(){
        jframe = new JFrame("Space");
        jframe.add(this);
        jframe.setResizable(true);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jframe.setVisible(true);
    }

    public synchronized void start(){
        thread = new Thread();
        isRunning = true;
        thread.start();
    }

    public synchronized void stop(){
        isRunning = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}