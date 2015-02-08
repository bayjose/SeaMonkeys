/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Base.util.EnumGameState;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private int curTicks = 0;
    private final int maxTicks = 60;
    
    public Intro(Model model, Handler handler) {
        super(model);
        model.assignTexture("into.png");
        model.offset.increaseVelX(Game.WIDTH/2);
        model.offset.increaseVelY(Game.HEIGHT/2);
        Model gear = Models.generateQuad(new Vector3D(Game.WIDTH/2, 0, Handler.cam.optimalRender), 128);
        gear.assignTexture("Gear.png");
        gear.offset.increaseVelY(Game.HEIGHT/2);
        this.models.add(gear);
        this.handler = handler;
    }

    public void update() {
        this.models.get(1).RotateYOnlyPoints(0.25F);
        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
            Tank tank = new Tank(this.handler);
            handler.entities.add(new TableTop(this.handler));
            handler.entities.add(tank);
            handler.entities.add(new WaterBottle(tank));
            handler.entities.add(new Lid(tank));
            handler.entities.add(new Packet(new Vector3D(128, 128, Handler.cam.optimalRender), "table/step3.png", 3, tank));
            handler.entities.add(new Packet(new Vector3D(100, 128, Handler.cam.optimalRender), "table/step2.png", 2, tank));
            handler.entities.add(new Packet(new Vector3D(100, 128, Handler.cam.optimalRender), "table/step1.png", 1, tank));
            handler.entities.add(new RubberDuck(handler));
            
            
        }
        if(this.curTicks<this.maxTicks){
            this.curTicks++;
        }
//        System.out.println("Model 1: "+this.models.get(1).Distance+" Scale:"+this.models.get(1).Scale+" Position:"+this.models.get(1).offset.getZ());
    }
    
    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }

    @Override
    public void checkForIntersection(Entity Mouse) {
        
    }
    
}
