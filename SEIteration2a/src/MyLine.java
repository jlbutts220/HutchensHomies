
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hutchenshomies;

/**
*
* @author mmhahn1
*/

public class MyLine {
    
    ClassBox source;
    ClassBox dest;
    
    double startX;
    double startY;
    double endX;
    double endY;
    
    DrawGraphical dg;
    Line line = new Line();
    Shape arrowHead;
    String arrow = "lined";
    
    public MyLine (double startX, double startY, double endX, double endY, DrawGraphical dg) {
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        
        this.dg = dg;
       
	}
    
    public void setPreferences (String arrowType, String lineType, String label){
    	
    	// check different arrow head types
    	this.arrow = arrowType;
    	Double[] points;
    	
    	if (arrow == "lined"){
    		arrowHead = new Polyline();
    		points = new Double[]{endX-15, endY-10, endX, endY, endX-15, endY+10};
    		((Polyline)arrowHead).getPoints().addAll(points); 
    	}
    		
    	else {
    		arrowHead = new Polygon();
    		arrowHead.setStroke(Color.BLACK);
    		
    		if (arrow == "openTri"){
    			points = new Double[]{endX-15, endY-10, endX, endY, endX-15, endY+10};
    	    	((Polygon)arrowHead).getPoints().addAll(points);
    	    	arrowHead.setFill(Color.WHITE);
        	}
    		
    		else {
    			points = new Double[]{endX-20, endY, endX-10, endY-10, endX, endY, endX-10, endY+10};
    			((Polygon)arrowHead).getPoints().addAll(points);
    			
    			if (arrow == "openDia"){
    				arrowHead.setFill(Color.WHITE);
        		}
        		else {
        			arrowHead.setFill(Color.BLACK);
        		}
    		}
    	}
    	
    	if (lineType == "dotted"){
    		line.getStrokeDashArray().addAll(new Double[]{25d, 20d});
    	}
    	
    	// if it has a label, do we want the label attached to line or DrawGraphical?
    	// how are we handling cardinality?
    	
    	dg.drawMyLine(this);
    }
    
    public void redrawStart(double x, double y){
    	startX+=x;
    	startY+=y;
    	line.setStartX(startX);
    	line.setStartY(startY);
    	
    }
    
    public void redrawEnd(double x, double y){
    	endX+=x;
    	endY+=y;
    	line.setEndX(endX);
    	line.setEndY(endY);
    	
    	Double[] points;
    	
    	if (arrow == "lined"){
    		
    		points = new Double[]{endX-15, endY-10, endX, endY, endX-15, endY+10}; 
    		for(int i = 0; i < ((Polyline)arrowHead).getPoints().size(); i ++){
        		((Polyline)arrowHead).getPoints().set(i, points[i]);
        	}
    	}
    		
    	else {
    		
    		if (arrow == "openTri"){
    			points = new Double[]{endX-15, endY-10, endX, endY, endX-15, endY+10};
    		}
    		
    		else {
    			points = new Double[]{endX-20, endY, endX-10, endY-10, endX, endY, endX-10, endY+10};
    		}
    		
    		for(int i = 0; i < ((Polygon)arrowHead).getPoints().size(); i ++){
        		((Polygon)arrowHead).getPoints().set(i, points[i]);
        	}
   
    	}
    	
    }
}