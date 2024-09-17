public class RectangularPrism{

    private Point[] points; //8 points
    private LineSegment[] edges; // 12 edges

    private Vector pos;
    private Vector size;
    
    public RectangularPrism(Vector pos, Vector size){
        this.pos = pos.clone();
        this.size = size.clone();
        points = new Point[8];
        int i=0;
        for (float x = pos.x()-size.x()/2; x <= pos.x()+size.x()/2; x += size.x()) {
            for (float y = pos.y()-size.y()/2; y <= pos.y()+size.y()/2; y+=size.y()) {
                for (float z = pos.z()-size.z()/2; z <= pos.z()+size.z()/2; z+=size.z()) {
                    points[i] = new Point(new Vector(x, y, z));
                    i++;
                }
            }
        }

        edges = new LineSegment[12];
        int edgesI = 0;
        for(int i1=0;i1<points.length;i1++){
            for(int i2=i1+1;i2<points.length;i2++){
                Vector v = Vector.dif(points[i1].pos(), points[i2].pos());
                if(Math.abs(Vector.dot(v, new Vector(0,0,1))) == v.mag() || Math.abs(Vector.dot(v, new Vector(0, 1, 0))) == v.mag() || Math.abs(Vector.dot(v, new Vector(1,0,0))) == v.mag()){
                    System.out.println(v);
                    edges[edgesI] = new LineSegment(points[i1].pos(), points[i2].pos());
                    edgesI++;
                }
            }
        }
    }
}
