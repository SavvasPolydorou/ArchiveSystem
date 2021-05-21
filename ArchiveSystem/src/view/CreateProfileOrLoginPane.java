package view;


import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.User;


public class CreateProfileOrLoginPane extends GridPane {

	private TextField txtEmail;
	private PasswordField txtPassword;
	private Button btnCreateProfile, btnLoadUser;
	public CreateProfileOrLoginPane() {
		//styling
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
		this.getStyleClass().addAll("ViewStyling-Pane", "root");
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.getColumnConstraints().addAll(column0);

		//create labels
		Label lblEmail = new Label("Input email: ");
		Label lblPassword = new Label("Input password: ");


		txtEmail = new TextField();
		txtPassword = new PasswordField();

		//initialise create profile button
		btnCreateProfile = new Button("Create Profile");
		btnLoadUser = new Button("Load User");
		//add controls and labels to container
		this.add(lblEmail, 0, 0);
		this.add(txtEmail, 1, 0);

		this.add(lblPassword, 0, 1);
		this.add(txtPassword, 1, 1);

		HBox hbox = new HBox();
		hbox.setSpacing(15);
		hbox.getChildren().addAll(btnCreateProfile, btnLoadUser);
		this.add(hbox, 1, 2);

	}


	public String getEmail() {
		return txtEmail.getText();
	}


	public String getPassword() {
		return txtPassword.getText();
	}


	// populate the fields with the given student details for the load handler
	public void setUserDetails(User user) 
	{
		txtEmail.setText(user.getEmail());
		txtPassword.setText(user.getPassword());
	}
	//method to attach the button event handler
	public void addCreateProfileHandler(EventHandler<ActionEvent> handler) {
		btnCreateProfile.setOnAction(handler);
	}

	public void addLoadUserHandler(EventHandler<ActionEvent> handler) {
		btnLoadUser.setOnAction(handler);
	}
}
