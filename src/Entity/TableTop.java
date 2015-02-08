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
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class TableTop extends Entity{

    private Handler handler;
    
    public TableTop(Handler handler) {
        super(Models.generateQuad(new Vector3D(Game.WIDTH/2,0,Handler.cam.optimalRender), Game.WIDTH, Game.HEIGHT));
        super.getModel().assignTexture("tableTop.png");
        super.getModel().offset.increaseVelY(Game.HEIGHT/2);
        //add the packets to the table top
        this.handler = handler;
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
        
    }
    
}
