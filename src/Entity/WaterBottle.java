/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class WaterBottle extends Entity{
    
    public Model Cap;
    public boolean pour = false;
    public boolean open = false;
    public int volume = 100;
    
    private final Vector3D translation = new Vector3D(-250, -300, 0);
    private final float maxRoation = -100;
    //60 ticks per second;
    private final float desiredTimeTicks = 90;
    private final Vector3D translationPerSecond = new Vector3D(translation.getX()/desiredTimeTicks, translation.getY()/desiredTimeTicks, translation.getZ()/desiredTimeTicks);
    private int curTicks = 0;
    private int capCountdown = 60;
    
    private Tank tank;
    
    public WaterBottle(Tank tank) {
        super(Models.generateQuad(new Vector3D(1300, 400, Handler.cam.optimalRender), 187, 512));
        super.getModel().assignTexture("table/waterbottle.png");
        
        Vector3D capOffset = new Vector3D(super.getModel().offset.getX(), super.getModel().offset.getY(), super.getModel().offset.getZ());
        
        Cap = Models.generateQuad(capOffset, 72, 30);
        Cap.assignTexture("table/cap.png");
        Cap.offset.increaseVelY(-235);
        super.models.add(Cap);
        this.tank = tank;
        
    }

    @Override
    public void update() {
        if(pour&&!this.tank.full){
            if(this.curTicks<this.desiredTimeTicks){
                this.getModel().offset.addVector(translationPerSecond);
                this.RotateYOnlyPoints(this.maxRoation/this.desiredTimeTicks);
                this.Cap.RotateYOnlyPoints(-this.maxRoation/this.desiredTimeTicks);
                this.curTicks++;
            }else{
                this.tank.filling = true;
            }
        }
        if(this.tank.full){
            if(this.curTicks>0){
                this.getModel().offset.addVector(translationPerSecond.inverse());
                this.RotateYOnlyPoints(-this.maxRoation/this.desiredTimeTicks);
                this.Cap.RotateYOnlyPoints(this.maxRoation/this.desiredTimeTicks);
                this.curTicks--;
            }else{
                this.remove = true;
            }
        }
        if(this.open){
            if(this.capCountdown>0){
                this.capCountdown--;
            }else{
                this.models.remove(this.Cap);
            }
        }
    }

    @Override
    protected void render(Graphics g) {
        
    }

    @Override
    public void dead() {
        
    }

    @Override
    public void checkForIntersection(Entity entity) {
        if(entity instanceof BasicEntity){
            if(this.open == false){
                if(entity.getModel().intersects(Cap)){
                    Cap.offset.addVector(new Vector3D(-130, 450, 0));
                    Cap.RotateYOnlyPoints(180);
                    this.open = true;
                    return;
                }
            }
            if(tank.open){
                if(this.open){
                    if(entity.getModel().intersects(this.getModel())){
                        if(this.pour == false){
                            this.pour = true;
                        }
                    }
                }
            }
        }
    }
    
}
