/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Game;
import Base.Handler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;

/**
 *
 * @author Bayjose
 */
public class RubberDuck extends Entity{

    private Handler handler;
    public RubberDuck(Handler handler) {
        super(Models.generateQuad(new Vector3D(Game.WIDTH/2, Game.HEIGHT/2, Handler.cam.optimalRender), 128));
        super.getModel().assignTexture("table/duck.png");
        this.handler = handler;
        
    }

    @Override
    public void update() {
        super.getModel().RotateYOnlyPoints(2);
        for(int i=0; i<this.handler.entities.size(); i++){
            this.checkForIntersection(handler.entities.get(i));
        }
//        super.getModel().RotateYOnlyPoints(1);
    }

    @Override
    protected void render(Graphics g) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dead() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkForIntersection(Entity entity) {
        if(entity instanceof SeaMonkey){
            if(entity.getModel().intersects(this.getModel())){
                entity.remove = true;
                Tank.SeaMonkeys--;
            }
        }
    }
    
}
