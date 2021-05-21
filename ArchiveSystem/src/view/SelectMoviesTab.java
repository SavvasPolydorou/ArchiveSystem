package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Gender;
import model.Genre;


public class SelectMoviesTab extends VBox {
	//fields
	private TextField txtTitle, txtDuration, txtDirector, txtProducer;
	private DatePicker releaseDate;
	private ComboBox<Genre> cboGenre;
	private SelectMoviesList selectedMovies;
	private ButtonPane bp;
	//constructor
	public SelectMoviesTab()
	{
		//styling
		this.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
		this.getStyleClass().addAll("ViewStyling-Pane", "root");

		// -- Field initialization -- 
		selectedMovies = new SelectMoviesList("Archived Movies");
		selectedMovies.enableExpansion();
		selectedMovies.setListPrefSize(10, 100);		
		//create labels
		Label lblGenre = new Label("Genre: ");
		Label lblDirector = new Label("Director: ");
		Label lblTitle = new Label("Title: ");
		Label lblDuration = new Label("Duration: ");
		Label lblProducer = new Label("Producer: ");
		Label lblReleaseDate = new Label("Release date: ");
		txtTitle = new TextField();
		txtDuration = new TextField();
		txtDirector = new TextField();
		txtProducer = new TextField();
		releaseDate = new DatePicker();
		cboGenre = new ComboBox<Genre>(); //this is populated via method towards end of class

		bp = new ButtonPane();

		GridPane input = new GridPane();
		input.setHgap(15);
		input.setVgap(10);
		input.setAlignment(Pos.CENTER);

		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		input.getColumnConstraints().addAll(column0);
		GridPane.setConstraints(lblGenre, 0, 0);
		GridPane.setConstraints(cboGenre, 1, 0);

		GridPane.setConstraints(lblDirector, 0, 1);
		GridPane.setConstraints(txtDirector, 1, 1);

		GridPane.setConstraints(lblTitle, 0, 2);
		GridPane.setConstraints(txtTitle, 1, 2);

		GridPane.setConstraints(lblDuration, 0, 3);
		GridPane.setConstraints(txtDuration, 1, 3);

		GridPane.setConstraints(lblProducer, 0, 4);
		GridPane.setConstraints(txtProducer, 1, 4);

		GridPane.setConstraints(lblReleaseDate, 0, 5);
		GridPane.setConstraints(releaseDate, 1, 5);
		input.getChildren().addAll(lblGenre, cboGenre, 
				lblDirector, txtDirector,lblTitle, txtTitle
				, lblDuration, txtDuration, lblProducer, txtProducer
				, lblReleaseDate, releaseDate);

		this.getChildren().add(input);

		//		 -- Creating Selected movies --
		VBox movies = new VBox(3);
		movies.setAlignment(Pos.CENTER_LEFT);
		movies.getChildren().add(selectedMovies);
		//enable the box to grow horizontally and vertically
		VBox.setVgrow(movies, Priority.ALWAYS);	
		HBox.setHgrow(movies, Priority.ALWAYS);

		// -- Adding button pane to the box --
		HBox bpBox = new HBox(10);		
		bpBox.setAlignment(Pos.CENTER);
		bpBox.setPadding(new Insets(0,5,0,0));
		bpBox.getChildren().addAll(bp);

		// -- combining the above boxes
		VBox container = new VBox(5);
		container.setPadding(new Insets(0,5,0,0));
		container.setAlignment(Pos.CENTER_LEFT);
		container.getChildren().addAll(movies, bpBox);
		//enable the box to grow horizontally and vertically
		VBox.setVgrow(container, Priority.ALWAYS);
		HBox.setHgrow(container, Priority.ALWAYS);


		this.getChildren().add(container);

	}

	//method to allow the controller to add genders to the combobox
	public void addGenreToComboBox(Genre[] genre) {
		cboGenre.getItems().addAll(genre);
		cboGenre.getSelectionModel().select(0); //select first course by default	
	}

	//methods

	public String getTitle()
	{
		return txtTitle.getText();
	}

	public String getDirector()
	{
		return txtDirector.getText();
	}

	public String getProducer()
	{
		return txtProducer.getText();
	}

	public String getDuration()
	{
		return txtDuration.getText();
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate.getValue();
	}

	public Genre getGenre()
	{
		return cboGenre.getValue();
	}
	//return the list objects
	public SelectMoviesList getSelectedMoviesList()
	{
		return selectedMovies;
	}	

	//clear all lists via the controller
	public void clearContentFromList()
	{
		selectedMovies.clearList();	
	}
	//event handlers to be accessible by the controller
	public void addAddHandler(EventHandler<ActionEvent> handler)
	{
		bp.addAddHandler(handler);
	}

	public void addRemoveHandler(EventHandler<ActionEvent> handler)
	{
		bp.addRemoveHandler(handler);
	}

	public void addShowDetailsHandler(EventHandler<ActionEvent> handler)
	{
		bp.addShowDetailsHandler(handler);
	}
	public void addMouseListenerSelectedTab(EventHandler<MouseEvent> event)
	{
		this.setOnMousePressed(event);
	}

	public void resetInputs() 
	{
		cboGenre.getSelectionModel().select(0);
		txtTitle.setText("");
		txtDirector.setText("");
		txtProducer.setText("");
		txtDuration.setText("");	
		releaseDate.setValue(null);

	}

}