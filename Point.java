import java.awt.Graphics;

public class Point extends Drawable{
    private float radius = .1f;

    public Point(Vector pos){
        super(pos);
    }

    public void drawMe(Graphics g){
        Vector camToPoint = Vector.dif(pos(), Screen.cam.pos());
        float distToPointPlane = Vector.dot(camToPoint, Screen.cam.rot()[2]);
        float ratioPointToCam = distToPointPlane/Screen.cam.focDist();

        if(ratioPointToCam < 0){
            return;
        }

        Vector pointPlaneCamToPoint = Vector.dif(camToPoint, Vector.mult(Screen.cam.rot()[2], distToPointPlane));
        Vector pointOnScreen = Vector.mult(pointPlaneCamToPoint, 1/ratioPointToCam);//vector from center of screen to point projection

        Vector pointOnScreenRealtiveToCam = new Vector(Vector.dot(Screen.cam.rot()[0], pointOnScreen), Vector.dot(Screen.cam.rot()[1], pointOnScreen), 0);

        float screenRadius = radius/ratioPointToCam;

        Vector drawPoint = Screen.getScreenCoords(Vector.sum(pointOnScreenRealtiveToCam, new Vector(-screenRadius, screenRadius, 0)));
        g.fillOval((int)drawPoint.x(), (int)drawPoint.y(), Screen.toPixels(screenRadius*2), Screen.toPixels(screenRadius*2));
    }
    public static Vector getScreenCoords(Vector point){
        Vector camToPoint = Vector.dif(point, Screen.cam.pos());
        float distToPointPlane = Vector.dot(camToPoint, Screen.cam.rot()[2]);
        float ratioPointToCam = distToPointPlane/Screen.cam.focDist();

        if(ratioPointToCam < 0){
            return new Vector(Float.NaN);
        }

        Vector pointPlaneCamToPoint = Vector.dif(camToPoint, Vector.mult(Screen.cam.rot()[2], distToPointPlane));
        Vector pointOnScreen = Vector.mult(pointPlaneCamToPoint, 1/ratioPointToCam);//vector from center of screen to point projection

        Vector pointOnScreenRealtiveToCam = new Vector(Vector.dot(Screen.cam.rot()[0], pointOnScreen), Vector.dot(Screen.cam.rot()[1], pointOnScreen), 0);

        return Screen.getScreenCoords(pointOnScreenRealtiveToCam);
    }
}
