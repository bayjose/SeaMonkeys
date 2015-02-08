/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.input.MouseInput;
import Base.input.MousePositionLocator;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Packet extends Entity{
    
    public int id = 0;
    public final Vector3D initialPos;
    public boolean rotate = false;
    public boolean open = false;
    public boolean left = false;
    
    public boolean empty = false;
    public int countDown = 60;
    
    private Tank tank;
    
    public Packet(Vector3D position, String path, int id, Tank tank) {
        super(Models.generateQuad(position, 140, 180));
        super.getModel().assignTexture(path);
        int rotation = (int)(Math.random()*20)-10;
        super.RotateYOnlyPoints(rotation);
        initialPos = position;
        if(rotation <0){
            this.left = true;
        }
        this.id = id;
        this.tank = tank;
    }

    public void update() {
        if(this.rotate){
            if(left){
                if(this.getModel().AbsoluteAnlgeY>-140){
                    this.getModel().RotateYOnlyPoints(-3);
                }else{
                    empty = true;
                }
            }else{
                if(this.getModel().AbsoluteAnlgeY<140){
                    this.getModel().RotateYOnlyPoints(3);
                }else{
                    empty = true;
                }
            }
        }
        if(this.empty){
            if(this.countDown<=0){
                this.remove = true;
            }
            this.countDown--;
        }
    }

    protected void render(Graphics g) {

    }

    public void dead() {

    }

    @Override
    public void checkForIntersection(Entity Mouse) {
       if(tank.full){
           if(this.id!=3){
                if(Mouse.getModel().intersects(this.getModel())){
                    MouseInput.collision = this;
                }
           }
       }
    }

    
}
