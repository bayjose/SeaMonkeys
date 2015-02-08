/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Lid extends Entity{
    
    private Tank tank;
    
    private final Vector3D translation = new Vector3D(400, 400, 0);
    private final float desireTicks = 90;
    private final float desiredRotation = 75;
    private final float curTicks = 0;
    
    public Lid(Tank tank) {
        super(Models.generateQuad(new Vector3D(tank.getModel().offset.getX()-4, tank.getModel().offset.getY() - 323, tank.getModel().offset.getZ()), 525, 122));
        super.getModel().assignTexture("table/lid.png");
        this.tank = tank;
    }

    @Override
    public void update() {
        
    }

    @Override
    protected void render(Graphics g) {
        
    }

    @Override
    public void dead() {
        
    }

    @Override
    public void checkForIntersection(Entity Mouse) {
        if(Mouse.getModel().intersects(this.getModel())){

            if(Mouse instanceof BasicEntity){
                loop:{
                    if(this.tank.open==false){
                        this.tank.open = true;
                        this.getModel().offset.addVector(this.translation);
                        this.getModel().RotateYOnlyPoints(75);
                        break loop;
                    }
                    if(this.tank.open==true){
                        this.tank.open = false;
                        this.getModel().offset.addVector(this.translation.inverse());
                        this.getModel().RotateYOnlyPoints(-75);
                        break loop;
                    }
                }
            }
        }
    }
    
}
