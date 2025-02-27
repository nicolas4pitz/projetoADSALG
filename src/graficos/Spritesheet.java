package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Sistema de criação de spritesheet, usado para todas as entidades
public class Spritesheet {

    private BufferedImage spritesheet;

    public Spritesheet(String path){
        try {
            spritesheet = ImageIO.read(Spritesheet.class.getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height){
        return spritesheet.getSubimage(x, y, width, height);
    }
}
