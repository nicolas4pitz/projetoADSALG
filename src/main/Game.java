package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entities.Entity;
import entities.Player;
import graficos.Spritesheet;
import graficos.UI;
import world.World;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener{

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning;
    public static final int WIDTH = 140;
    public static final int HEIGHT = 160;
    public static final int SCALE = 4;

    private BufferedImage image;

    public static List<Entity> entities;
    public static Spritesheet spritesheet;
    public static Player player;

    public EnemySpawn enemySpawn;

    public UI ui;

    public BufferedImage GameBackground;
    public BufferedImage GameBackground2;

    public int backY = 0;
    public int backY2 = 160;
    public int backspd = 1;

    public int xx, yy;

    public static int entrada = 1;
    public static int comecar = 2;
    public static int jogando = 3;
    public static int estado_cena = entrada;
    public int timeCena = 0, maxTimeCena = 60*3;

    public boolean saveGame = false;

    public static World world;

    public static int score=0;
    public static double life = 100;


    public Game() {
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        spritesheet = new Spritesheet("spritesheet.png");
        entities = new ArrayList<Entity>();
        player = new Player((Game.WIDTH/2)-8, Game.HEIGHT-16, 16,  16, 1, spritesheet.getSprite(0, 0, 16, 16));
        world =  new World();
        ui = new UI();
        enemySpawn = new EnemySpawn();
        try {
            GameBackground = ImageIO.read(getClass().getResource("gb_game7.png"));
            GameBackground2 = ImageIO.read(getClass().getResource("gb_game7.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        entities.add(player);
    }

    public void initFrame() {
        frame = new JFrame("Space invaders");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        requestFocus();
    }

    public synchronized void start(){
        thread = new Thread(this);
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

    public static void main(String args[]){
        Game game = new Game();
        game.start();

    }

    public void tick(){
        for (int i = 0; i < entities.size(); i++){
            Entity e = entities.get(i);
            e.tick();
        }

        ui.tick();
        enemySpawn.tick();

        backY-=backspd;
        if(backY+160 <= 0){
            backY = 160;
        }

        backY2-=backspd;
        if(backY2+160 <= 0){
            backY2 = 160;
        }
    }


    public void render(){
        // Obtenha a estratégia de buffer para o canvas
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            // Crie uma nova estratégia de buffer com 3 buffers se não houver uma estratégia existente
            this.createBufferStrategy(3);
            return;
        }
        // Obtenha o objeto gráfico para a estratégia de buffer
        Graphics g = image.getGraphics();
        // Defina a cor de fundo como preto
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(GameBackground, 0, backY, null);
        g.drawImage(GameBackground2, 0, backY2, null);

        // Sort the entities list by Z-index
        world.render(g);
        Collections.sort(entities, Entity.nodeSorter);
        // Loop through each entity in the entities list
        for (int i = 0; i < entities.size(); i++){
            // Get the entity
            Entity e = entities.get(i);
            // Render the entity
            e.render(g);
        }
        //applyLight();


        g.dispose();

        // Get the world graphics object
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
        ui.render(g);

        bs.show();

    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                render();
                frames++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                System.out.println("FPS" + frames);
                frames = 0;
                timer += 1000;
            }
        }

        stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_Q | e.getKeyCode() == KeyEvent.VK_SPACE){
            player.isShotting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.left = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

}
