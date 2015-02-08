/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Base.SpriteBinder;
import Physics.GravityHandler;
import Physics.Model;
import Physics.Vector3D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import world.Size;

/**
 *
 * @author Bayjose
 */
public class ModelTester extends Entity{
    
    float rotX = (float) Math.random();
    float rotY = (float) Math.random();
    float rotZ = (float) Math.random();
    
    private Random r = new Random();
    private int col = r.nextInt(11);
    private int row = r.nextInt(5);
    
    private Handler handler;
    private int sin = 0;
    
    public ModelTester(Model model, Handler handler) {
        super(model);
        this.handler = handler;
    }

    public void update() {
        this.getModel().RotateXOnlyPoints(rotX);
        this.getModel().RotateYOnlyPoints(rotY);
        this.getModel().RotateZOnlyPoints(rotZ);
        sin++;
    }

    public void render(Graphics g) {

    }
    
    public void dead() {
        
    }

    @Override
    public void checkForIntersection(Entity Mouse) {
        
    }
    
}
