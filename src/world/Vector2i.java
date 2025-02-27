package world;

//Sistema para aplicação e manipulação da posição das entidades
public class Vector2i {
    public int x, y;

    public Vector2i(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object object){
        Vector2i vec = (Vector2i) object;
        if(vec.x == this.x && vec.y == this.y){
            return true;
        }
        return false;
    }
}
