package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.Gender;
import model.Genre;
import model.Movie;
import model.User;
import view.ArchiveSystemMenuBar;
import view.ArchiveSystemToolRootPane;
import view.CreateProfileOrLoginPane;
import view.MovieDetails;
import view.SelectMoviesList;
import view.SelectMoviesTab;
import view.UserDetailsTab;

public class ArchiveSystemController {
	//fields
	private ArchiveSystemToolRootPane view;
	private User model;
	private CreateProfileOrLoginPane cpolp;
	private UserDetailsTab udt;
	private SelectMoviesTab smt;
	private ArchiveSystemMenuBar asmb;
	private MovieDetails md;
	private SelectMoviesList list;
	//constructor
	public ArchiveSystemController(ArchiveSystemToolRootPane view, User model)
	{
		this.view = view;
		this.model = model;

		cpolp = view.getCreateProfileOrLoginPane();
		udt = view.getUserDetailsTab();
		smt = view.getSelectMoviesTab();
		list = smt.getSelectedMoviesList();
		asmb = view.getArchiveSystemMenuBar();
		md = view.getMovieDetails();
		
		udt.addGendersToComboBox(Gender.values());
		smt.addGenreToComboBox(Genre.values());
		
		this.attachEventHandlers();
	}

	private void attachEventHandlers()
	{
		cpolp.addCreateProfileHandler(new CreateProfileHandler());
		cpolp.addLoadUserHandler(new LoadHandlerMenu());
		udt.addConfirmHandler(new ConfirmHandler());
		udt.addShowPasswordHandler(new ShowPasswordHandler());

		smt.addAddHandler(new AddHandler());
		smt.addRemoveHandler(new RemoveHandler());
		smt.addShowDetailsHandler(new ShowDetailsHandler());

		md.addBackHandler(g -> {view.changeTab(2); list.clearSelection();});
		asmb.addSaveHandler(new SaveHandlerMenu());
		asmb.addLoadHandler(new LoadHandlerMenu());
		asmb.addExitHandler(new ExitHandler());
		asmb.addAboutHandler(e -> alertDialogBuilder(AlertType.INFORMATION,"Information Dialog", null,
				"Archive System GUI v1.1\n\nDeveloper: Savvas Polydorou"));
	}

	private class LoadHandlerMenu implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Load");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to load your previous state?");
			alert.getButtonTypes().set(0, ButtonType.YES);
			alert.getButtonTypes().set(1, ButtonType.NO);


			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES)
			{
				//no need to clear overview as the methods are setters, not .add()
				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("UserProfile.dat")))
				{
					//clear any pre-existing content on any tab 
					//clearEverythingWhenLoadButtonIsPressed();
					//load student profile tab and add data to the model
					model = (User) ois.readObject();
					cpolp.setUserDetails(model);
					udt.setUserDetails(model);
					list.clearList();
					//load select movies tab
					List<Movie> movieList = (List<Movie>) ois.readObject();
					list.populateList(movieList);  

					//no need to have a finally clause as the ois object is created in the try block
					ois.close();

					Alert alertSuccess = new Alert(AlertType.INFORMATION);
					alertSuccess.setTitle("File load");
					alertSuccess.setHeaderText("File loaded successfully");
					alertSuccess.setContentText("Welcome back " + model.getName().getFullName());
					//add a custom success tick icon and set it
					ImageView icon = new ImageView("success.png");
					icon.setFitHeight(60);
					icon.setFitWidth(48);
					alertSuccess.getDialogPane().setGraphic(icon);
					alertSuccess.showAndWait();
					//enable all tabs
					view.disableT2(false);
					view.disableT3(false);
					view.disableT4(false);
					smt.addMouseListenerSelectedTab(d -> list.clearSelection());
					System.out.println(model);

				} 
				catch (ClassNotFoundException exc)
				{
					alertDialogBuilder(AlertType.ERROR, "Error", "Missing files", "Error loading file. Please try saving first.");		
				} 
				catch (IOException exc) 
				{
					alertDialogBuilder(AlertType.ERROR, "Error", "File not found", "Error loading file. Please try saving first.");		
				}
			}
			//else the user decided not to load the file
			else
				alert.close();
		}
	}

	private class SaveHandlerMenu implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to save your current state?");
			alert.getButtonTypes().set(0, ButtonType.YES);
			alert.getButtonTypes().set(1, ButtonType.NO);


			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES)
			{
				ObjectOutputStream oos = null;
				try
				{
					//write everything in a binary file
					oos = new ObjectOutputStream(new FileOutputStream("UserProfile.dat"));
					//write all the properties
					//create profile
					//writes the model object to a file
					oos.writeObject(model);
					//select movie
					oos.writeObject(new ArrayList<Movie>(list.getList()));

					Alert alertSuccess = new Alert(AlertType.INFORMATION);
					alertSuccess.setTitle("Profile saved successfully");
					alertSuccess.setHeaderText("Success");
					alertSuccess.setContentText("Profile saved succesfully with the name UserProfile.dat");
					//add a custom success tick icon and set it
					ImageView icon = new ImageView("success.png");
					icon.setFitHeight(60);
					icon.setFitWidth(48);
					alertSuccess.getDialogPane().setGraphic(icon);
					alertSuccess.showAndWait();
					oos.flush();
					System.out.println(model);

				} 
				catch(IOException exc)
				{
					alertDialogBuilder(AlertType.ERROR, "Error", "Error saving", "Saving student profile failed. Please try again.");		
					exc.printStackTrace();
				}
				/*finally ensures that even if the save fails this section will
			always compile, in our case close the output stream */
				finally
				{
					try
					{
						oos.close();
					}
					catch(IOException exc)
					{
						alertDialogBuilder(AlertType.ERROR, "Error", "Error closing", "Error closing file. Please try again.");		

					}			
				}			
			}
			//if the user decided not to save the profile
			else
				alert.close();
		}
	}

	private class ShowDetailsHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			Movie selected = list.getSelectedMovie();
			if(selected == null)
				alertDialogBuilder(AlertType.ERROR, "Error", "Selection error", "Please select a movie to view it's details!");		
			else
			{
				view.changeTab(3);
				smt.addMouseListenerSelectedTab(d -> list.clearSelection());
				md.setMovieDetails(selected);

			}
		}
	}
	private class RemoveHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e)
		{
			//storing the selected item from the selected movies list
			Movie selected = list.getSelectedMovie();
			//if the list is empty
			if(list.getList().isEmpty() == true)		
				alertDialogBuilder(AlertType.ERROR, "Error", "The list is empty", "Please add a movie to the list first and then select one to remove!");		
			else if(selected == null)
				alertDialogBuilder(AlertType.ERROR, "Error", "Selection error", "Please select a movie to remove from the list!");		
			else
				list.removeSelectedMovie();
		}
	}
	private class AddHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{
			Boolean emptyTitle = smt.getTitle().isEmpty();
			Boolean emptyProducer = smt.getProducer().isEmpty();
			Boolean emptyDirector = smt.getDirector().isEmpty();
			Boolean emptyDuration = smt.getDuration().isEmpty();
			Boolean invalidNumber = false;
			for(int i = 0; i < smt.getDuration().length(); i++)
				if(!Character.isDigit(smt.getDuration().charAt(i)))
				{
					invalidNumber = true;
					break;
				}
			Boolean emptyExceptDate = emptyTitle || emptyProducer ||
					emptyDirector || emptyDuration;
			Boolean emptyDate = smt.getReleaseDate() == null;
			//if one or all fields are empty
			if(emptyExceptDate)
				alertDialogBuilder(AlertType.ERROR, "Error", "Empty fields error", "Please enter the movie's details for all fields!");
			//else if the date is empty, wrong formatting or an invalid data type was entered i.e string
			else if (emptyDate)
				alertDialogBuilder(AlertType.ERROR, "Error", "Invalid date error", "Please press the calendar button and select a date");
			else if (invalidNumber)
				alertDialogBuilder(AlertType.ERROR, "Error", "Invalid format error", "Please enter the duration of the movie in minutes");
			//else everything is entered correctly
			else
			{
				list.addMovie(new Movie(smt.getTitle(), 
						Integer.parseInt(smt.getDuration()), smt.getGenre(), smt.getDirector(),
						smt.getProducer(), smt.getReleaseDate()));

				model.populateList(list.getList());
				smt.resetInputs();
			}
		}
	}
	private class ConfirmHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{	
			model.setName(udt.getUserName());
			model.setEmail(udt.getUserEmail());
			model.setDoB(udt.getDoB());
			model.setGender(udt.getSelectedGender());

			Boolean emptyFirstName = model.getName().getFirstName().isEmpty();
			Boolean emptySurname = model.getName().getFamilyName().isEmpty();
			Boolean emptyEmail = model.getEmail().isEmpty();
			Boolean emptyPassword = model.getPassword().isEmpty();
			Boolean emptyDoB = model.getDoB() == null;

			Boolean emptyAllFieldsExceptDate =  emptyFirstName || 
					emptySurname || emptyEmail|| emptyPassword;
			//if one or all fields are empty
			if(emptyAllFieldsExceptDate)
				alertDialogBuilder(AlertType.ERROR, "Error", "Empty fields error", "Please enter your details for all fields!");
			//else if the date is empty, wrong formatting or an invalid data type was entered i.e string
			else if (emptyDoB)
				alertDialogBuilder(AlertType.ERROR, "Error", "Invalid date error", "Please press the calendar button and select a date");
			//else everything is entered correctly
			else
			{
				view.changeTab(2);
				smt.addMouseListenerSelectedTab(d -> list.clearSelection());
			}
		}
	}

	private class ShowPasswordHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{	
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to show your password?");
			alert.getButtonTypes().set(0, ButtonType.YES);
			alert.getButtonTypes().set(1, ButtonType.NO);


			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES)
				udt.convertPassword();


			alert.close();

		}
	}
	private class CreateProfileHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{	
			model.setEmail(cpolp.getEmail());
			model.setPassword(cpolp.getPassword());

			Boolean emptyEmail = model.getEmail().isEmpty();
			Boolean emptyPassword = model.getPassword().isEmpty();

			if(emptyEmail || emptyPassword)
				alertDialogBuilder(AlertType.ERROR, "Error", "Empty fields error", "Please enter your details for all fields!");
			//else if an en idi used to email
			else
			{
				view.changeTab(1);

				udt.setEmail(model.getEmail());
				udt.setPassword(model.getPassword());
				//in case the show password field was pressed
				udt.setPasswordShown(model.getPassword());
			}
		}
	}

	private class ExitHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent e) 
		{	
			//set an alert before exiting
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to exit?");
			alert.getButtonTypes().set(0, ButtonType.YES);
			alert.getButtonTypes().set(1, ButtonType.NO);


			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.YES)
				System.exit(0);
			else
				alert.close();
		}	
	}
	//helper method to build dialogs
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
