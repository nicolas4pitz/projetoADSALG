package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import main.Game;
import world.Camera;
import world.Node;
import world.Vector2i;
import world.World;

//Formato padrão de renderização de todas as entidades do jogo e com metodo proprio de renderização
public class Entity {

    protected List<Node> path;

    public double x;
    public double y;
    protected int z;
    protected int width;
    protected int height;
    protected double speed;

    public int depth;

    protected BufferedImage sprite;

    public static Random rand = new Random();

    public Entity(double x, double y, int width, int height, double speed, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.speed = speed;

    }

    public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {

        @Override
        public int compare(Entity n0,Entity n1) {
            if(n1.depth < n0.depth)
                return +1;
            if(n1.depth > n0.depth)
                return -1;
            return 0;
        }

    };


    public void setX(int newX){
        this.x = newX;
    }

    public void setY(int newY){
        this.y = newY;
    }

    public int getX(){
        return (int)this.x;
    }

    public int getY(){
        return (int)this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public void tick(){

    }

    public static boolean isColliding(Entity e1, Entity e2){
        Rectangle e1Mask = new Rectangle(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
        Rectangle e2Mask = new Rectangle(e2.getX(), e2.getY(), e2.getWidth(), e2.getHeight());

        return e1Mask.intersects(e2Mask);
    }


    public void render(Graphics g){
        g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y , null);
        //g.setColor(Color.red);
        //g.fillRect(this.getX() + maskx - Camera.x, this.getY() + masky - Camera.y, mwidth, height);
    }
}
