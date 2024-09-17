public class Camera {
    private Vector pos = new Vector(0, 0, 0);
    private Vector[] rot;
    private float fov; //vertical fov
    private float focDist;

    public Camera(Vector pos, Vector[] rot, float fov, float camHeight){
        this.pos = pos.clone();
        this.rot = rot.clone();
        this.fov = fov;
        this.focDist = camHeight/(2*(float)Math.tan(fov/2));
    }

    public void move(Vector v){
        pos = Vector.sum(pos, v);
    }
    public void moveLocal(Vector v){
        Vector deltaX = Vector.mult(rot[0], v.x());
        Vector deltaY = Vector.mult(rot[1], v.y());
        Vector deltaZ = Vector.mult(rot[2], v.z());
        Vector deltaPos = Vector.sum(Vector.sum(deltaX, deltaY), deltaZ);
        move(deltaPos);
    }
    public void rotate(Vector v){
        for(int i=0;i<rot.length;i++){
            // about z
            rot[i] = new Vector(rot[i].x() * Math.cos(-v.z()) - rot[i].y() * Math.sin(-v.z()), rot[i].y()*Math.cos(-v.z())+rot[i].x()*Math.sin(-v.z()), rot[i].z());

            //about y
            rot[i] = new Vector(rot[i].x() * Math.cos(v.y()) - rot[i].z() * Math.sin(v.y()), rot[i].y(), rot[i].z()*Math.cos(v.y())+rot[i].x()*Math.sin(v.y()));

            //about x
            rot[i] = new Vector(rot[i].x(), rot[i].y()*Math.cos(v.x())+rot[i].z()*Math.sin(v.x()), rot[i].z() * Math.cos(v.x()) - rot[i].y() * Math.sin(v.x()));
        }
    }
    public void rotateLocal(Vector localV){
        Vector deltaX = Vector.mult(rot[0], localV.x());
        Vector deltaY = Vector.mult(rot[1], localV.y());
        Vector deltaZ = Vector.mult(rot[2], localV.z());
        Vector deltaRot = Vector.sum(Vector.sum(deltaX, deltaY), deltaZ);
        rotate(deltaRot);
    }


    public Vector pos() {
        return pos.clone();
    }
    public Vector[] rot() {
        return rot.clone();
    }
    public float fov(){
        return fov;
    }
    public float focDist(){
        return focDist;
    }
}
