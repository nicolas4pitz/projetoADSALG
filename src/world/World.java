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

    public static int WIDTH,HEIGHT;
    public static final int TILE_SIZE = 16;


    public World(){

    }

    public void render(Graphics g){
        int xstart = Camera.x >> 4;
        int ystart = Camera.y >> 4;

        int xfinal = xstart + (Game.WIDTH >> 4);
        int yfinal = ystart + (Game.HEIGHT >> 4);


    }

}
