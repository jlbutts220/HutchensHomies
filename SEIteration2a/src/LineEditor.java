
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author Matt Hahn, Matt Rumpf, Jess Butts, Mike Dwyer, Jamie Thorpe
 *
 */

public class LineEditor {

	Stage lineEditor = new Stage();
	GridPane grid = new GridPane();
	Scene textScene = new Scene(grid, 400, 375);
	
	MyLine line;
	String arrowType = "lined";
	String lineFont = "dotted";
	
	
	/**
 	*
 	* Creates a window that can be used to edit the text of a classBox
	*
	* @param dc is the classBox whose text is to be edited
	*
 	**/

	public LineEditor(MyLine line) {

		this.line = line;
		
	}
	
	public void createEditor() {
		
		lineEditor.setTitle("Line Editor");
		grid.setAlignment(Pos.CENTER);
		lineEditor.setScene(textScene);

		Label name = new Label("Select Line Type:");
		grid.add(name, 0, 0);
		
		
		Button depend = new Button("Dependence");
		grid.add(depend, 1, 1);
		
		depend.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setTypes("lined", "dotted");	
			}
		});
		
		Button assoc = new Button("Association");
		grid.add(assoc, 1, 2);
		
		assoc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setTypes("lined", "solid");
			}
		});
		
		Button aggr = new Button("Aggregation");
		grid.add(aggr, 1, 3);
		
		aggr.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setTypes("openDia", "solid");
			}
		});
		
		Button comp = new Button("Composition");
		grid.add(comp, 1, 4);
		
		comp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setTypes("closedDia", "solid");
			}
		});
		
		Button gener = new Button("Generalization");
		grid.add(gener, 1, 5);
		
		gener.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setTypes("openTri", "solid");
			}
		});

		Label addText = new Label("Add Line Label:");
		grid.add(addText, 0, 7);
		
		TextField labelField = new TextField("");
		grid.add(labelField, 1, 7);
		
		Label addSC = new Label("Add Source Cardinality:");
		grid.add(addSC, 0, 8);
		
		TextField srcCard = new TextField("");
		grid.add(srcCard, 1, 8);
		
		Label addDC = new Label("Add Destination Cardinality:");
		grid.add(addDC, 0, 9);
		
		TextField destCard = new TextField("");
		grid.add(destCard, 1, 9);

		Button submit = new Button("Submit");
		grid.add(submit, 1, 10);

		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				line.setPreferences(arrowType, lineFont, labelField.getText(), srcCard.getText(), destCard.getText());
				closeWindow();
			}
		});

		lineEditor.show();
	}
	
	public void setTypes(String arrowT, String lineT){
		arrowType = arrowT;
		lineFont = lineT;
	}

	/**
 	*
 	* closes the textEditor window
	*
 	**/
	
	public void closeWindow() {
		lineEditor.close();
	}

}