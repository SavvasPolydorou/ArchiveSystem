package view;

import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Movie;

public class SelectMoviesList extends VBox{
	//fields
	private ListView<Movie> lstList;
	private ObservableList<Movie> obsList;
	private Label lblList;
	//constructor
	public SelectMoviesList(String listType)
	{
		obsList = FXCollections.observableArrayList();	

		lstList = new ListView<>(obsList);
		lblList = new Label(listType);

		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(0,5,0,5));
		this.getChildren().addAll(lblList, lstList);	
	}

	//methods
	public ObservableList<Movie> getList()
	{
		return obsList;
	}

	public void setListPrefSize(double prefWidth, double prefHeight)
	{
		lstList.setPrefSize(prefWidth, prefHeight);
	}

	public void enableExpansion()
	{	
		//enable the list and this VBox to grow horizontally and vertically
		VBox.setVgrow(this, Priority.ALWAYS);
		VBox.setVgrow(lstList, Priority.ALWAYS);
		HBox.setHgrow(this, Priority.ALWAYS);
		HBox.setHgrow(lstList, Priority.ALWAYS);		
	}

	public void clearSelection()
	{
		lstList.getSelectionModel().clearSelection();
	}

	public Movie getSelectedMovie()
	{
		return lstList.getSelectionModel().getSelectedItem();
	}


	public void addMovie(Movie movie)
	{
		obsList.add(movie);		
	}

	public void removeSelectedMovie()
	{
		Movie movie = getSelectedMovie();
		obsList.remove(movie);
		lstList.getSelectionModel().clearSelection();

	}

	public void populateList(List<Movie> movies)
	{
		obsList.addAll(movies);
	}
	public void clearList()
	{
		obsList.clear();
	}

	public void sortList()
	{
		Collections.sort(obsList);	
	}

	public void addMouseListenerToList(EventHandler<MouseEvent> event)
	{
		lstList.setOnMousePressed(event);
	}
}
