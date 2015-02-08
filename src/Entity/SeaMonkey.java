/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Base.Handler;
import Physics.Vector3D;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Bayjose
 */
public class SeaMonkey extends Entity{

    private final float speed = 60;
    
    public boolean initialMovement = false;
    public Rectangle rect;
    public final float friction = 0.01f;
    
    private boolean pregnent = false;
    private int PregnecyTimer = 0;
    private int currentTicsPrego = 0;
    private final boolean Male;
    private final boolean Female;
    
    private Handler handler;
    private final int MaxLifespan;
    
    public SeaMonkey(Egg egg, Handler handler) {
        super(Models.generateQuad(egg.getModel().offset, 4, 8));
        super.getModel().assignTexture("table/seaMonkey.png");
        if(Math.random()<=0.65f){
            this.Female = true;
            this.Male = false;
        }else{
           this.Female = false;
           this.Male = true; 
        }
        this.handler = handler;
         MaxLifespan = (int)(90000+((Math.random()-0.5f)*36000));
         Tank.SeaMonkeys++;
    }

    @Override
    public void update() {
        this.lifespan+=speed;
        rect = (this.getModel().faces[0].returnJavaPolygon().getBounds());
        rect.x+=this.getModel().offset.getX();
        rect.y+=this.getModel().offset.getY();
        if(this.initialMovement==false){
            this.vecForward = new Vector3D(0,0,0);
//            System.out.println("Position"+this.getModel().offset.getX()+" "+this.getModel().offset.getY()+" "+this.getModel().offset.getZ());

            if(rect.intersects(Handler.tankSize)){
                this.initialMovement = true;
            }else{
               this.vecForward.increaseVelY(-0.5f);
               if(rect.getX()<Handler.tankSize.getX()){
                   this.vecForward.increaseVelX(0.5f);
               }
               if(rect.getX()>Handler.tankSize.getX()+Handler.tankSize.width){
                   this.vecForward.increaseVelX(-0.5f);
               }
            }
        }else{
            if(this.vecForward.getX()>0){
                this.vecForward.increaseVelX(-friction);
                if(this.vecForward.getX()<0){
                    this.vecForward.setVelX(0);
                }
            }
            if(this.vecForward.getX()<0){
                this.vecForward.increaseVelX(+friction);
                if(this.vecForward.getX()>0){
                    this.vecForward.setVelX(0);
                }
            }
            if(this.vecForward.getY()>0){
                this.vecForward.increaseVelY(-friction);
                if(this.vecForward.getY()<0){
                    this.vecForward.setVelY(0);
                }
            }
            if(this.vecForward.getY()<0){
                this.vecForward.increaseVelY(+friction);
                if(this.vecForward.getY()>0){
                    this.vecForward.setVelY(0);
                }
            }
            if(this.vecForward.getZ()>0){
                this.vecForward.increaseVelZ(-friction);
                if(this.vecForward.getZ()<0){
                    this.vecForward.setVelZ(0);
                }
            }
            if(this.vecForward.getZ()<0){
                this.vecForward.increaseVelZ(+friction);
                if(this.vecForward.getZ()>0){
                    this.vecForward.setVelZ(0);
                }
            }
//            System.out.println(this.vecForward.getX()+" "+this.vecForward.getY() + " "+this.vecForward.getZ());
            if(this.vecForward.getX() == 0 && this.vecForward.getY() == 0 && this.vecForward.getZ() == 0){
                this.vecForward = new Vector3D(((float)(Math.random()-0.5f))*(this.getModel().extraScale+1), ((float)(Math.random()-0.5f))*(this.getModel().extraScale+1), ((float)(Math.random()-0.5f))*(this.getModel().extraScale+1));
//                this.vecForward.addVector(new Vector3D((-(this.getModel().extraScale+1)/2), (-(this.getModel().extraScale+1)/2), (-(this.getModel().extraScale+1)/2)));
            }
            
            if(new Rectangle((int)(this.rect.getX()+this.vecForward.getX())-(int)(1*(this.getModel().extraScale+1))/2, (int)(this.rect.getY()+this.vecForward.getY())-(int)(1*(this.getModel().extraScale+1))/2, (int)(1*(this.getModel().extraScale+1)), (int)(1*(this.getModel().extraScale+1))).intersects(Handler.tankSize)){
                
            }else{
                this.vecForward = new Vector3D(0,0,0);
            }
            
        }
            //grow
            if(this.getModel().extraScale<6){
                this.getModel().extraScale+=(0.00008333333333f*speed);
//                 this.getModel().extraScale+=1f;
            }
            
            if(this.lifespan>=this.MaxLifespan){
                this.remove = true;
                Tank.SeaMonkeys--;
            }
            
            //make pregnent if female
            if(this.Female&&!this.pregnent){
                if(this.lifespan>=54000){
                    for(int i=0; i<handler.entities.size(); i++){
                        if(handler.entities.get(i)instanceof SeaMonkey){
                            if(((SeaMonkey)handler.entities.get(i)).Male==true){
                                if(this.getModel().intersects(handler.entities.get(i).getModel())){
                                    this.pregnent = true;
                                    this.PregnecyTimer = (int)(7200+(Math.random()*3600));
                                    this.currentTicsPrego = 0;
                                    System.out.println("YO IM PREGGO");
                                }
                            }
                        }
                    }
                }
            }
            //if pregenent
            if(this.pregnent){
                if(this.currentTicsPrego<this.PregnecyTimer){
                    this.currentTicsPrego+=this.speed;
                }else{
                    Vector3D tempVect = new Vector3D(this.getModel().offset.getX(),this.getModel().offset.getY(),this.getModel().offset.getZ());
                    this.handler.entities.add(new Egg(tempVect, this.handler));
                    if(Math.random()<=0.21F){
                        this.remove = true;
                        Tank.SeaMonkeys--;
                    }
                    this.pregnent = false;
                }
            }
        
    }

    @Override
    protected void render(Graphics g) {
        if(this.pregnent){
            
        }
    }

    @Override
    public void dead() {

    }

    @Override
    public void checkForIntersection(Entity entity) {

    }
    
}
