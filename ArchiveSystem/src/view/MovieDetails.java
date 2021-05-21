package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Movie;

public class MovieDetails extends GridPane {
	//fields
	private TextField txtTitle, txtDirector, txtProducer, txtGenre, txtDuration, txtReleaseDate;
	private Button btnBack;
	//constructor
	
	public MovieDetails()
	{
		btnBack = new Button("Back");
		Label lblGenre = new Label("Genre: ");
		Label lblDirector = new Label("Director: ");
		Label lblTitle = new Label("Title: ");
		Label lblDuration = new Label("Duration: ");
		Label lblProducer = new Label("Producer: ");
		Label lblReleaseDate = new Label("Release date: ");
		
		txtTitle = new TextField();
		txtTitle.setPromptText("Please select a movie first");
		txtDuration = new TextField();
		txtDuration.setPromptText("Please select a movie first");
		txtDirector = new TextField();
		txtDirector.setPromptText("Please select a movie first");
		txtProducer = new TextField();
		txtProducer.setPromptText("Please select a movie first");
		txtReleaseDate = new TextField();
		txtReleaseDate.setPromptText("Please select a movie first");
		txtGenre = new TextField();
		txtGenre.setPromptText("Please select a movie first");
		
		txtTitle.setEditable(false);
		txtDuration.setEditable(false);
		txtDirector.setEditable(false);
		txtProducer.setEditable(false);
		txtReleaseDate.setEditable(false);
		txtGenre.setEditable(false);
		
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
		this.getStyleClass().addAll("ViewStyling-Pane", "root");
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.RIGHT);

		this.add(lblGenre, 0, 0);
		this.add(txtGenre, 1, 0);

		this.add(lblDirector, 0, 1);
		this.add(txtDirector, 1, 1);

		this.add(lblTitle, 0, 2);
		this.add(txtTitle, 1, 2);

		this.add(lblDuration, 0, 3);
		this.add(txtDuration, 1, 3);

		this.add(lblProducer, 0, 4);
		this.add(txtProducer, 1, 4);

		this.add(lblReleaseDate, 0, 5);
		this.add(txtReleaseDate, 1, 5);

		this.add(new HBox(btnBack), 1, 6);


	}
	public MovieDetails(Movie movie)
	{
		
		txtTitle = new TextField(movie.getTitle());
		txtDuration = new TextField(String.valueOf(movie.getDuration()));
		txtDirector = new TextField(movie.getDirector());
		txtProducer = new TextField(movie.getProducer());
		txtReleaseDate = new TextField(movie.getReleaseDate().toString());
		txtGenre = new TextField(movie.getGenre().toString());

		
	}

	//methods
	public void setMovieDetails(Movie movie)
	{
		txtTitle.setText((movie.getTitle()));
		txtDuration.setText(String.valueOf(movie.getDuration()));
		txtDirector.setText(movie.getDirector());
		txtProducer.setText(movie.getProducer());
		txtReleaseDate.setText(movie.getReleaseDate().toString());
		txtGenre.setText(movie.getGenre().toString());
	}
	//method to attach the create student profile button event handler
	public void addBackHandler(EventHandler<ActionEvent> handler) {
		btnBack.setOnAction(handler);
	}
}
