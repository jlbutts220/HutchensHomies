import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import javafx.application.Application;
import javafx.application.Platform;

import java.util.ArrayList;

public class TestSuite {
	
	@BeforeClass
	/**
	* Allows the code in the test to use graphical parts.
	*
	*@Author Michael Ellis
		from http://stackoverflow.com/questions/11385604/how-do-you-unit-test-a-javafx-controller-with-junit
	*@throws InterruptedException
	*/
	public static void setUpClass() throws InterruptedException {
	    // Initialise Java FX

	    System.out.printf("About to launch FX App\n");
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(ControlPanel.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	    System.out.printf("FX App thread started\n");
	    Thread.sleep(500);
	}

	/**
	* Tests that the default text values are correct
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void checkTextInNewBox() {
		System.out.println("Test the default values in the three textbox sections of a new ClassBox");
		ClassBox newbox1 = new ClassBox(0, 1.0);
		ArrayList<String> myText1 = newbox1.getText();
		
		assertEquals("Default text in textbox 1 is 'a'", "a", myText1.get(0));
		assertEquals("Default text in textbox 2 is 'b'", "b", myText1.get(1));
		assertEquals("Default text in textbox 3 is 'c'", "c", myText1.get(2));	
	}
	
	/**
	* Tests that the text in the class boxes is changed successfully to new strings
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void textEditorChangingBoxText() {
		System.out.println("Test whether the text editor successfully changes the text in the ClassBox");
		ClassBox newbox2 = new ClassBox(0, 1.0);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	TextEditor edit2 = new TextEditor(newbox2);
	    		newbox2.setEditor(edit2);
	    		ArrayList<String> origText2 = newbox2.getText();
	    		
	    		assertEquals("Default text in textbox 1 is 'a'", "a", origText2.get(0));
	    		assertEquals("Default text in textbox 2 is 'b'", "b", origText2.get(1));
	    		assertEquals("Default text in textbox 3 is 'c'", "c", origText2.get(2));	
	    		
	    		edit2.resetText("New name", "New att", "New ops");
	    		ArrayList<String> newText2 = newbox2.getText();
	    		
	    		assertEquals("Edited text in textbox 1 is 'New name'", "New name", newText2.get(0));
	    		assertEquals("Edited text in textbox 2 is 'New att'", "New att", newText2.get(1));
	    		assertEquals("Edited text in textbox 3 is 'New ops'", "New ops", newText2.get(2));
	        }
	   });		
	}
	
	/**
	* Tests that text in the class box can be changed to empty strings i.e. that the text can be deleted
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void submitEmptyTextInEditor() {
		System.out.println("Test whether the text editor can change the textboxes to empty in a ClassBox");
		ClassBox newbox3 = new ClassBox(0, 1.0);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	TextEditor edit3 = new TextEditor(newbox3);
	    		newbox3.setEditor(edit3);
	    		ArrayList<String> origText3 = newbox3.getText();
	    		
	    		assertEquals("Default text in textbox 1 is 'a'", "a", origText3.get(0));
	    		assertEquals("Default text in textbox 2 is 'b'", "b", origText3.get(1));
	    		assertEquals("Default text in textbox 3 is 'c'", "c", origText3.get(2));	
	    		
	    		edit3.resetText("", "", "");
	    		ArrayList<String> newText3 = newbox3.getText();
	    		
	    		assertEquals("Textbox 1 is now empty", "", newText3.get(0));
	    		assertEquals("Textbox 2 is now empty", "", newText3.get(1));
	    		assertEquals("Textbox 3 is now empty", "", newText3.get(2));
	        }
	   });
		
	}
	
	/**
	* Tests that the anchor points are added to the class box after it is created
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test 
	public void testAnchorPoints() {
		System.out.println("Test whether the text editor can change the textboxes to empty in a ClassBox");
		ClassBox anchorTest = new ClassBox(0, 1.0);
		
		assertEquals("Test whether the anchor points have been added to the array list in the class box", 16, anchorTest.anchorPoints.size(), 0);

	}
	
	/**
	* Tests that the class boxes are added to the Array List object in DrawGraphical
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void testAddingClassBoxes() {
		System.out.println("Test whether class boxes are added to the array list:");

		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	DrawGraphical dg = new DrawGraphical(null);
			dg.drawClassBox();
			
			assertEquals("Test whether the size of the array list is 1", 1, dg.list.size(), 0);
			
			dg.drawClassBox();
			dg.drawClassBox();
			dg.drawClassBox();
			assertEquals("Test whether the size of the array list is 4", 4, dg.list.size(), 0);
	        }
	   });
		
	}
	
	/**
	* Tests that text in the class box can be changed to empty strings i.e. that the text can be deleted
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void checkLineChangeStart() {
		System.out.println("Check that the start of a line changes position correctly");
		MyLine newline = new MyLine(0, 0, 1, 1);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    		
	    		assertEquals("Line starts at (0,0,1,1) start X is at 0", 0, newline.startX, 0);
	    		assertEquals("Line starts at (0,0,1,1) start Y is at 0", 0, newline.startY, 0);
	    		
	    		newline.redrawStart(5, 5);
	    		
	    		assertEquals("Line now at (5,5,1,1) start X is at 5", 0, newline.startX, 0);
	    		assertEquals("Line now at (5,5,1,1) start Y is at 5", 0, newline.startY, 0);
	    		
	        }
	   });
		
	}
	
	/**
	* Tests that text in the class box can be changed to empty strings i.e. that the text can be deleted
	*
	*@Author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
	*/
	@Test
	public void checkLineChangeEnd() {
		System.out.println("Check that the end of a line changes position correctly");
		MyLine newline = new MyLine(0, 0, 1, 1);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    		
	        	assertEquals("Line starts at (0,0,1,1) end X is at 1", 1, newline.endX, 0);
	    		assertEquals("Line starts at (0,0,1,1) end Y is at 1", 1, newline.endY, 0);
	    		
	    		newline.redrawEnd(5, 5);
	    		
	    		assertEquals("Line now at (0,0,6,6) end X is at 6", 6, newline.endX, 0);
	    		assertEquals("Line now at (0,0,6,6) end Y is at 6", 6, newline.endY, 0);
	    		
	        }
	   });
		
	}
}
