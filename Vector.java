public class Vector {
    private float x,y,z;

    public Vector(float... components){
        x = 0;
        y = 0;
        z = 0;
        if(components.length > 0){
            x = components[0];
        }
        if(components.length > 1){
            y = components[1];
        }
        if(components.length > 2){
            z = components[2];
        }
    }
    public Vector(double... components){
        x = 0;
        y = 0;
        z = 0;
        if (components.length > 0) {
            x = (float)components[0];
        }
        if (components.length > 1) {
            y = (float)components[1];
        }
        if (components.length > 2) {
            z = (float)components[2];
        }
    }

    public float x(){
        return x;
    }
    public float y(){
        return y;
    }
    public float z(){
        return z;
    }
    public Vector clone(){
        return new Vector(x ,y, z);
    }
    public Vector normalized(){
        if(mag() == 0){
            return new Vector(0,0,0);
        }
        return mult(this, 1/mag());
    }
    public float mag(){
        return (float)Math.sqrt(x*x+y*y+z*z);
    }


    public static Vector dif(Vector v1, Vector v2){
        return new Vector(v1.x()-v2.x(), v1.y()-v2.y(), v1.z()-v2.z());
    }
    public static Vector sum(Vector v1, Vector v2) {
        return new Vector(v1.x() + v2.x(), v1.y() + v2.y(), v1.z() + v2.z());
    }
    public static float dot(Vector v1, Vector v2){
        return v1.x()*v2.x()+v1.y()*v2.y()+v1.z()*v2.z();
    }
    public static Vector mult(Vector v, float f){
        return new Vector(v.x()*f, v.y()*f, v.z()*f);
    }
    public static float dist(Vector v1, Vector v2){
        return Vector.dif(v1, v2).mag();
    }

    @Override
    public String toString(){
        return String.format("<%s, %s, %s>", x, y, z);
    }
}
