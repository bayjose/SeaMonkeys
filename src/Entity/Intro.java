/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.util.EnumGameState;
import Base.Game;
import Base.Handler;
import Base.SpriteBinder;
import Physics.Model;
import java.awt.Graphics;

/**
 *
 * @author Bailey
 */
public class Intro extends Entity{

    private Handler handler;

    private float speed = 0.5f;
    private float curTicks = 0;
    private final float maxTicks = 60;
    
    public Intro(Model model, Handler handler) {
        super(model);
         this.getModel().assignTexture("gear_0.png");
        model.offset.increaseVelX(Game.WIDTH/2);
        this.handler = handler;
    }

    public void update() {
        if(this.curTicks>=this.maxTicks){
            this.remove = true;
            handler.egs = EnumGameState.Main;
        }
        if(this.curTicks<this.maxTicks){
            this.curTicks+=this.speed;
            if(this.curTicks%9<3){
                this.getModel().assignTexture("gear_0.png");
            }else if(this.curTicks%9>3&&this.curTicks%10<6){
                this.getModel().assignTexture("gear_1.png");
            }else{
                this.getModel().assignTexture("gear_2.png");
            }
        }
    }
    
    protected void render(Graphics g) {
        
    }

    public void dead() {
        
    }
    
}
