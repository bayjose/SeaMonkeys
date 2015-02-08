/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics;

import Base.util.DistanceCalculator;
import Base.Handler;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Line2D;

/**
 *
 * @author Bayjose
 */
public class Face {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    private final double rotX;
    private final double rotY;
    private final double rotZ;
    
    
    public Face(Point p1, Point p2, Point p3, double rotX, double rotY, double rotZ){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.rotX = rotX%360;
        this.rotY = rotY%360;
        this.rotZ = rotZ%360;
    }
    
    public Polygon returnJavaPolygon(){
        Point[] tempPts =new Point[]{p1,p2,p3};
        int[] xpts = new int[tempPts.length];
        int[] ypts = new int[tempPts.length];
        for(int i=0; i<tempPts.length; i++){
            xpts[i]=(int)(tempPts[i].getX());
            ypts[i]=(int)(tempPts[i].getY());
        }
        return new Polygon(xpts, ypts, tempPts.length);
    }
    
    public float getArea(){
        float answer1 = ((p1.getX()*p2.getY())+(p2.getX()+p3.getY()));
        float answer2 = ((p1.getY()*p2.getX())+(p2.getY()+p3.getX()));
        return answer1-answer2;
    }
    public float getAreaX(){
        float answer1 = ((p1.getX()*p2.getY())+(p2.getX()+p3.getY()));
        return answer1;
    }
    public float getAreaY(){
        float answer2 = ((p1.getY()*p2.getX())+(p2.getY()+p3.getX()));
        return answer2;
    }
    
    public float getX(){
        return this.p1.getX();
    }
    public float getY(){
        return this.p1.getY();
    }
    
    public float getWidth(){
        if(rotX>90&&rotX<270){
            return -DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }else{
            return DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }
    }
    public float getHeight(){
        if(rotZ>90&&rotZ<270){
            return -DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p3.getX(), p3.getY());
        }else{
            return DistanceCalculator.CalculateDistanceF(p1.getX(), p1.getY(), p3.getX(), p3.getY());
        }
    }
    
    public Point getCenter(){
        float xOffset = (this.p1.getX()+this.p2.getX()+this.p3.getX())/3;
        float yOffset = (this.p1.getY()+this.p2.getY()+this.p3.getY())/3;
        float zOffset = (this.p1.getZ()+this.p2.getZ()+this.p3.getZ())/3;
        return new Point3D(xOffset,yOffset,zOffset);
    }
    
    
    public Point getPoint1(){
        return this.p1;
    }
    public Point getPoint2(){
        return this.p2;
    }
    public Point getPoint3(){
        return this.p3;
    }

    public Point getPoints(int index) {
        if(index==0){
            return this.p1;
        }
        if(index==1){
            return this.p2;
        }
        if(index==2){
            return this.p3;
        }
        return null;
    }
    
}
