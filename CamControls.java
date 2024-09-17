import java.awt.event.*;
import java.awt.*;

public class CamControls implements KeyListener, MouseMotionListener, Runnable{

    private boolean a,w,d,s,e,q;
    private final float sensitivity = .05f;

    private Robot robot;

    public CamControls(){
        Screen.Singleton.addKeyListener(this);
        Screen.Singleton.addMouseMotionListener(this);

        try{
            robot = new Robot();
        }catch(AWTException e){
            e.printStackTrace();
        }
    }

    public void mouseMoved(MouseEvent e){
        Vector mouseCoords = Screen.getWorldCoords(new Vector(e.getX(), e.getY()));

        Screen.cam.rotate(new Vector(0, sensitivity*-mouseCoords.x()));
        Screen.cam.rotateLocal(new Vector(sensitivity*mouseCoords.y()));

        robot.mouseMove(648, 391);
    }
    public void mouseDragged(MouseEvent e){

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            a = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            w = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            d = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            s = true;
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            this.e = true;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            q = true;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            a = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            w = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            d = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            s = false;
        } else if (e.getKeyCode() == KeyEvent.VK_E) {
            this.e = false;
        } else if (e.getKeyCode() == KeyEvent.VK_Q) {
            q = false;
        }
    }

    public void run(){
        while(true){
            //movement
            Vector moveVector = new Vector(0, 0, 0);
            if(a){
                moveVector = Vector.sum(moveVector, new Vector(-1, 0, 0));
            }if(w){
                moveVector = Vector.sum(moveVector, new Vector(0, 0, 1));
            }if (d) {
                moveVector = Vector.sum(moveVector, new Vector(1, 0, 0));
            }if (s) {
                moveVector = Vector.sum(moveVector, new Vector(0, 0, -1));
            }if(e){
                moveVector = Vector.sum(moveVector, new Vector(0, 1, 0));
            }if(q){
                moveVector = Vector.sum(moveVector, new Vector(0, -1, 0));
            }
            moveVector = moveVector.normalized();
            Screen.cam.moveLocal(Vector.mult(moveVector, 0.1f));


            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}