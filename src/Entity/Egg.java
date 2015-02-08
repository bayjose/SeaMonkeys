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
public class Egg extends Entity{

    //ten min = 36000
    public int countdown = 600;
    public Handler handler;
    
    public Egg(Vector3D startingPosition, Handler handler) {
        super(Models.generateQuad(startingPosition, 2+(int)(Math.random()*2)));
        int randIndex = (int)(Math.random()*3)+1;
        super.getModel().assignTexture("table/egg"+randIndex+".png");
        this.handler = handler;
    }

    public void update() {
        if(this.getModel().offset.getY()<600){
            this.vecForward = new Vector3D((float)Math.random()-(float)Math.random(), (float)Math.random()+(float)Math.random(), (float)Math.random()-(float)Math.random());
        }
        if(this.getModel().offset.getY()>680){
            this.vecForward = new Vector3D(0,0,0);
        }
        if(this.countdown>0){
            this.countdown--;
        }else{
            this.remove = true;
            handler.entities.add(new SeaMonkey(this, this.handler));
        }
    }

    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }

    public void checkForIntersection(Entity entity) {
        
    }
    
}
