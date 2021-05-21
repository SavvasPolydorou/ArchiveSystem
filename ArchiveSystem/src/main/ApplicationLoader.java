package main;

import controller.ArchiveSystemController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import view.ArchiveSystemToolRootPane;


public class ApplicationLoader extends Application{
	private ArchiveSystemToolRootPane view;
	@Override
	public void init()
	{
		//create view and model and pass their references to the controller
		view = new ArchiveSystemToolRootPane();
		User model = new User();
		new ArchiveSystemController(view, model);	

	}
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(530); 
		stage.setMinHeight(500);
		stage.setTitle("Archive System");
		stage.setScene(new Scene(view));
		stage.show();

	}

}
