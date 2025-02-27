package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import graficos.UI;
import main.Game;

public class World {

    public static Tile[] tiles;
    public static int WIDTH,HEIGHT;
    public static final int TILE_SIZE = 16;

    public static int dia = 0;
    public static int noite = 1;
    public static int CICLO = Entity.rand.nextInt(2);


    public World(){

    }

    public void render(Graphics g){
        int xstart = Camera.x >> 4;
        int ystart = Camera.y >> 4;

        int xfinal = xstart + (Game.WIDTH >> 4);
        int yfinal = ystart + (Game.HEIGHT >> 4);

        for(int xx = xstart; xx <= xfinal; xx++) {
            for(int yy = ystart; yy <= yfinal; yy++) {
                if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
                    continue;
                Tile tile = tiles[xx + (yy*WIDTH)];
                tile.render(g);
            }
        }
    }

}
