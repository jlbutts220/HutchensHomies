
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

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
    
    Text label = new Text("");
    Rectangle labelBack = new Rectangle();
    Text sCard = new Text("");
    Rectangle scardBack = new Rectangle();
    Text dCard = new Text("");
    Rectangle dcardBack = new Rectangle();
    
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
    
    public void setPreferences (String arrowType, String lineType, String labelText, String srcCard, String destCard){
    	
    	label.setText(labelText);
    	label.setX((startX+endX)/2);
    	label.setY((startY+endY-14)/2);
    	label.setVisible(true);
    	
    	label.applyCss();
    	double backWidth = Math.ceil(label.getLayoutBounds().getWidth());
    	labelBack.setX((startX+endX)/2);
    	labelBack.setY((startY+endY-44)/2);
    	labelBack.setHeight(20);
    	labelBack.setWidth(backWidth);
    	labelBack.setStroke(Color.WHITE);
    	labelBack.setFill(Color.WHITE);
    	
    	sCard.setText(srcCard);
    	sCard.setX(startX);
    	sCard.setY(startY);
    	sCard.setVisible(true);
    	
    	sCard.applyCss();
    	double sWidth = Math.ceil(sCard.getLayoutBounds().getWidth());
    	scardBack.setX(startX);
    	scardBack.setY(startY-15);
    	scardBack.setHeight(20);
    	scardBack.setWidth(sWidth);
    	scardBack.setStroke(Color.WHITE);
    	scardBack.setFill(Color.WHITE);
    	
    	dCard.setText(destCard);
    	dCard.setX(endX);
    	dCard.setY(endY);
    	dCard.setVisible(true);
    	
    	dCard.applyCss();
    	double dWidth = Math.ceil(dCard.getLayoutBounds().getWidth());
    	dcardBack.setX(endX);
    	dcardBack.setY(endY-15);
    	dcardBack.setHeight(20);
    	dcardBack.setWidth(dWidth);
    	dcardBack.setStroke(Color.WHITE);
    	dcardBack.setFill(Color.WHITE);
    	
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
    	
    	labelBack.setX((startX+endX)/2);
    	labelBack.setY((startY+endY-44)/2);
    	
    	label.setX((startX+endX)/2);
    	label.setY((startY+endY-14)/2);
    	
    	sCard.setX(startX);
    	sCard.setY(startY);
    
    	scardBack.setX(startX);
    	scardBack.setY(startY-15);
    	
    	dCard.setX(endX);
    	dCard.setY(endY);
    	
    	dcardBack.setX(endX);
    	dcardBack.setY(endY-15);
    	
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
    	
    	labelBack.setX((startX+endX)/2);
    	labelBack.setY((startY+endY-44)/2);
    	
    	sCard.setX(startX);
    	sCard.setY(startY);
    
    	scardBack.setX(startX);
    	scardBack.setY(startY-15);
    	
    	dCard.setX(endX);
    	dCard.setY(endY);
    	
    	dcardBack.setX(endX);
    	dcardBack.setY(endY-15);
    	
    	label.setX((startX+endX)/2);
    	label.setY((startY+endY-14)/2);
    	
    }
}