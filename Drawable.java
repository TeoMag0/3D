import java.util.ArrayList;
import java.awt.Graphics;

public abstract class Drawable {
    private static final ArrayList<Drawable> objects = new ArrayList<>();
    private Vector pos;

    public Drawable(Vector pos){
        this.pos = pos.clone();
        objects.add(this);
    }

    public static void draw(Graphics g){
        for(Drawable each : objects){
            each.drawMe(g);
        }
    }
    public Vector pos(){
        return pos.clone();
    }
    public void move(Vector v){
        pos = Vector.sum(pos, v);
    }

    public abstract void drawMe(Graphics g);
}
