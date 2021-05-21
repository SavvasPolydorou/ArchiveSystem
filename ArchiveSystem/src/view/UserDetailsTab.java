package view;


import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import model.Gender;
import model.Name;
import model.User;

public class UserDetailsTab extends GridPane {

	private ComboBox<Gender> cboGender;
	private TextField txtFirstName, txtSurname, txtEmail, txtPassword;
	private DatePicker txtDate;
	private PasswordField pw;
	private Button btnConfirm, btnShowPW;

	public UserDetailsTab() {
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
		Label lblGender = new Label("Gender: ");
		Label lblFirstName = new Label("First name: ");
		Label lblSurname = new Label("Surname: ");
		Label lblEmail = new Label("Email: ");
		Label lblDate = new Label("Date of Birth: ");
		Label lblPassword = new Label("Password: ");

		//initialise combobox
		cboGender = new ComboBox<Gender>(); //this is populated via method towards end of class

		//setup text fields
		txtFirstName = new TextField();
		txtSurname = new TextField();
		txtEmail = new TextField();
		txtPassword = new TextField();
		txtDate = new DatePicker();
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);
						if (item.isAfter(LocalDate.now()))
							setDisable(true);
					}
				};
			}
		};
		txtDate.setDayCellFactory(dayCellFactory);

		pw = new PasswordField();
		//initialise create profile button
		btnConfirm = new Button("Confirm");
		btnShowPW = new Button("Show Password");
		//add controls and labels to container
		this.add(lblGender, 0, 0);
		this.add(cboGender, 1, 0);

		this.add(lblEmail, 0, 1);
		this.add(txtEmail, 1, 1);
		
		this.add(lblPassword, 0, 2);
		this.add(pw, 1, 2);
		
		this.add(lblFirstName, 0, 3);
		this.add(txtFirstName, 1, 3);

		this.add(lblSurname, 0, 4);
		this.add(txtSurname, 1, 4);

		this.add(lblDate, 0, 5);
		this.add(txtDate, 1, 5);
		
		HBox hbox = new HBox(5);
		hbox.getChildren().addAll(btnConfirm, btnShowPW);
		hbox.setAlignment(Pos.CENTER);
		
		this.add(hbox, 1, 6);
	}

	//method to allow the controller to add genders to the combobox
	public void addGendersToComboBox(Gender[] gender) {
		cboGender.getItems().addAll(gender);
		cboGender.getSelectionModel().select(0); //select first course by default	
	}

	//methods to retrieve the form selection/input
	public Gender getSelectedGender() {
		return cboGender.getSelectionModel().getSelectedItem();
	}

	public Name getUserName() {
		return new Name(txtFirstName.getText(), txtSurname.getText());
	}

	public String getUserEmail() {
		return txtEmail.getText();
	}

	public LocalDate getDoB() {
		return txtDate.getValue();
	}
    
	public void setEmail(String email)
	{
    	txtEmail.setText(email);
	}
	
	public void setPassword(String password)
	{
    	pw.setText(password);
	}
	
	public void convertPassword()
	{
		
		pw.setVisible(false);
		txtPassword = new TextField(pw.getText());
		this.add(txtPassword, 1, 2);
		btnShowPW.setDisable(true);
	}
	
	public void setPasswordShown(String password)
	{
		txtPassword.setText(password);
	}
    // populate the fields with the given student details for the load handler
    public void setUserDetails(User user) 
    {
    	cboGender.getSelectionModel().select(user.getGender());
    	txtFirstName.setText(user.getName().getFirstName());
    	txtSurname.setText(user.getName().getFamilyName());
    	txtEmail.setText(user.getEmail());
    	txtDate.setValue(user.getDoB());
    	pw.setText(user.getPassword());
    }
	//method to attach the create student profile button event handler
	public void addConfirmHandler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
	public void addShowPasswordHandler(EventHandler<ActionEvent> handler) {
		btnShowPW.setOnAction(handler);
	}
}
