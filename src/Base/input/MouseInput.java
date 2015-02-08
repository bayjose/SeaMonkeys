package Base.input;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Base.Handler;
import Entity.BasicEntity;
import Entity.Entity;
import Entity.Models;
import Entity.Packet;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author Bayjose
 */
public class MouseInput implements MouseListener{
    

    public static Rectangle Mouse= new Rectangle(0, 0, 1, 1);
    public static boolean IsPressed=false;
    public static boolean IsRightClick=false;
    public static int index = 9;
    
    public static Entity collision =  new BasicEntity(Models.generateQuad(new Vector3D(0,0,0), 32));
    
    public static EnumMouseState ems = EnumMouseState.EditHeight;
    
    //items 
    public static int numberOfItems=0;
    
    Handler handler;
            
    public MouseInput(Handler handler){
        this.handler = handler;
        handler.entities.add(collision);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        MouseInput.collision.getModel().offset.setVelX(e.getX());
        MouseInput.collision.getModel().offset.setVelY(e.getY());
        Mouse.x=(e.getX()/this.handler.cam.zoom);
        Mouse.y=(e.getY()/this.handler.cam.zoom);
        if(MouseInput.collision instanceof Packet){
            for(int i=0; i<handler.entities.size(); i++){
                this.handler.entities.get(i).checkForIntersection(MouseInput.collision); 
            }
            MouseInput.resetEntity();
        }
        
        
        for(int i=0; i<handler.entities.size(); i++){
            this.handler.entities.get(i).checkForIntersection(MouseInput.collision); 
        }

        

    
        IsPressed=true;
        IsRightClick=false;
//            
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            IsRightClick=true;
        }
        
        if(!IsRightClick){
            
        }else{
            Handler.cam.position = new Vector3D(e.getX(), e.getY(), Handler.cam.position.getZ());
        }
    }

    public void mouseReleased(MouseEvent e) {
        IsPressed=false;
        IsRightClick=false;
        MousePositionLocator.enableShake=false;
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }

    public int ItemCount(){
        return this.numberOfItems;
    }
    
    public static void resetEntity(){
       collision =  new BasicEntity(Models.generateQuad(new Vector3D(0,0,0), 32)); 
    }
}
