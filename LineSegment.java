import java.awt.Graphics;

public class LineSegment extends Drawable{
    private Vector[] points;

    public LineSegment(Vector p1, Vector p2){
        super(p1);

        points = new Vector[] {p1, p2};
    }

    public void drawMe(Graphics g){
        Vector p1Screen = Point.getScreenCoords(points[0]);
        Vector p2Screen = Point.getScreenCoords(points[1]);
        if(Float.isNaN(p1Screen.x()) || Float.isNaN(p2Screen.x())){
            return;
        }
        g.drawLine((int)p1Screen.x(), (int)p1Screen.y(), (int)p2Screen.x(), (int)p2Screen.y());
    }

    @Override
    public String toString(){
        return points[0]+", "+points[1];
    }
}
