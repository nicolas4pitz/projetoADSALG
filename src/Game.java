import javax.swing.*;
import java.awt.*;

public class Game extends Canvas {
    public static JFrame jframe;
    private Thread thread;
    private boolean isRunning;


    public Game(){
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