/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Base.input.MouseInput;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class Tank extends Entity{

    private Handler handler;   
    public boolean open = false;
    public boolean filling = false;
    public boolean full = false;
    public float desiredFillTime = 180;
    public float desiredRoation = -90;
    private final float fillPerTick = (desiredRoation/desiredFillTime);
    public float curTime = 0;
    private Model Water ;
    
    private float time = 0;
    
    public static int SeaMonkeys = 0;
    
    
    public Tank(Handler handler) {
        super(Models.generateQuad(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, Handler.cam.optimalRender), 600, 800));
        super.getModel().assignTexture("table/tank_blue.png");
        Vector3D waterOffset = new Vector3D(super.getModel().offset.getX(), super.getModel().offset.getY(), super.getModel().offset.getZ());
        Water = Models.generateQuadCenterdAtBottom(waterOffset, 500, 555);  
        Water.offset.increaseVelY(260);
        Water.assignTexture("table/water.png");
        Water.RotateZOnlyPoints(90);
        super.models.add(Water);
        this.handler = handler;
    }

    @Override
    public void update() {
        time++;
        if(this.filling&&!full){
            if(this.curTime<this.desiredFillTime){
                this.Water.RotateZOnlyPoints(fillPerTick);
                this.curTime++;
            }else{
                this.filling = false;
                this.full = true;
            }
        }
    }

    @Override
    protected void render(Graphics g) {
        g.setColor(Color.white);
        g.drawString("Sea Monkeys:"+Tank.SeaMonkeys, 100, 600);
        g.drawString("Time:"+(int)(Math.floor(time/(60*60*60)))%24+":"+(int)(Math.floor(time/(60*60)))%60+":"+(int)(Math.floor(time/(60)))%60, 100, 614);
    }

    @Override
    public void dead() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkForIntersection(Entity Mouse) {
       if(Mouse.getModel().intersects(this.getModel())){
//           if(this.open){
                if(Mouse instanceof Packet){
                    MouseInput.resetEntity();
                    ((Packet)Mouse).rotate = true;
                    if(((Packet)Mouse).id == 2){
                        for(int i=0; i<(36+((int)(Math.random()*16))); i++){
                            handler.entities.add(new Egg(new Vector3D(Mouse.getModel().offset.getX(), Mouse.getModel().offset.getY(), Mouse.getModel().offset.getZ()), this.handler));
                        }
                    }
                }
//           }
       }
    }
    
}
