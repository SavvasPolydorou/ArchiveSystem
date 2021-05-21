
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonPane extends HBox {
	//fields
	private Button add, remove, showDetails;
	//constructor
	public ButtonPane()
	{
		this.setPadding(new Insets(15, 10, 15, 10));
		this.setSpacing(13);

		add = new Button("Add");
		add.setPrefSize(45, 20);
		remove = new Button("Remove");
		remove.setPrefSize(70, 20);
		showDetails = new Button("Show Details");
		showDetails.setPrefSize(100,  20);

		this.getChildren().addAll(add, remove, showDetails);
	}

	//these methods allow handlers to be externally attached to this view by the controller
	public void addAddHandler(EventHandler<ActionEvent> handler)
	{
		add.setOnAction(handler);
	}

	public void addRemoveHandler(EventHandler<ActionEvent> handler)
	{
		remove.setOnAction(handler);
	}
	
	public void addShowDetailsHandler(EventHandler<ActionEvent> handler)
	{
		showDetails.setOnAction(handler);
	}
}