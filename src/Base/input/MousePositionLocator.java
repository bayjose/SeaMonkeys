/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base.input;

import Base.Handler;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Bayjose
 */
public class MousePositionLocator implements MouseMotionListener{
    
    public static Rectangle MouseLocation = new Rectangle(0, 0, 3, 3);
    public static boolean enableShake=false;
   
    
    Handler handler;
    public MousePositionLocator(Handler h){
        handler=h;
    }
    
    public void mouseDragged(MouseEvent e) {
        MouseLocation.x=(e.getX()/this.handler.cam.zoom);
        MouseLocation.y=(e.getY()/this.handler.cam.zoom);
    }

    
    public void mouseMoved(MouseEvent e) {
        if(this.handler.cam!=null){
            MouseLocation.x=(e.getX()/this.handler.cam.zoom);
            MouseLocation.y=(e.getY()/this.handler.cam.zoom);
        }
        MouseInput.collision.getModel().offset.setVelX(e.getX());
        MouseInput.collision.getModel().offset.setVelY(e.getY());
//        model.point.setY(MouseLocation.y-(Handler.cam.WindowSize.height/2));
//        model.update();
    }
    
    
}
