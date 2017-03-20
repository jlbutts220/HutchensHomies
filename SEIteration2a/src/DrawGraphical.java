//http://stackoverflow.com/questions/28566860/javafx-how-to-group-shapes-for-dragging

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package hutchenshomies;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mmhahn1
 */
public class DrawGraphical {
   
	Stage stage = new Stage();
    Group box = new Group();
    Scene scene = new Scene(box, 1250, 800);
     
    Text togglebox;
    ArrayList<ClassBox> list = new ArrayList<ClassBox>(4);
	
    private boolean toDrawLine = false;
    private ClassBox source;
    private ClassBox destination;
    
    public DrawGraphical(Text togglebox) {
    	
    	this.togglebox = togglebox;
    	
    	stage.setTitle("Graphical View");
        stage.setScene(scene);
        stage.show();
    }
    
    public void drawClassBox() { 
    	// automatically calculates start points based on number of boxes already present
	// Draws empty class box with three sections in it
    	ClassBox newbox = new ClassBox(list.size(), 0.75);
    	
    	box.getChildren().add(newbox.baseRec);
    	box.getChildren().add(newbox.sep1);
    	box.getChildren().add(newbox.sep2);
    	
    	box.getChildren().add(newbox.t1);
    	box.getChildren().add(newbox.t2);
    	box.getChildren().add(newbox.t3);
    	
    	TextEditor te = new TextEditor(newbox);
    	newbox.setEditor(te);
    	
    	list.add(newbox); 
    	
    	newbox.baseRec.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(toDrawLine){
                	if(source == null){
                		source = newbox;
                	}
                	else{
                		destination = newbox;
                		drawNewLine(source, destination);
                		source = null;
                		destination = null;
                		toDrawLine = false;
                		togglebox.setText("Ready");
                	}
                }else{
                	System.out.println("mouse click detected! " + newbox.name);
                }
            }
        });
    }
    
    public void toggleLineDraw() {
    	if(list.size() >= 2){
    		if (!toDrawLine){
    			toDrawLine = true;
    			togglebox.setText("Drawing");
    			System.out.println("Toggle true");
    		}
    		else{
    			toDrawLine = false;
    			source = null;
    			togglebox.setText("Ready");
    			System.out.println("Toggle false");
    		}
 
    	}
    }
    
    public void drawNewLine(ClassBox source, ClassBox dest){
    	if(list.size() < 2){
    		return;
    	}
        
        MyLine newline = new MyLine(source, dest);
		
        box.getChildren().add(newline.line);
       
	}

} 
