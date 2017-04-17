
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
    	
    	if (arrowType == "lined"){
    		arrowHead = new Polyline();
    		((Polyline)arrowHead).getPoints().addAll(new Double[]{endX, endY-20, endX, endY, endX-20, endY}); 
    	}
    		
    	else {
    		arrowHead = new Polygon();
    		
    		if (arrowType == "openTri"){
    	    	((Polygon)arrowHead).getPoints().addAll(new Double[]{endX, endY-20, endX, endY, endX-20, endY});
    	    	arrowHead.setFill(Color.WHITE);
        	}
    		
    		else {
    			((Polygon)arrowHead).getPoints().addAll(new Double[]{endX, endY-20, endX, endY, endX-20, endY, endX-20, endY-20});
    			
    			if (arrowType == "openDia"){
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
    	
    }
}