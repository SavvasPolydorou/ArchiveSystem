package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;


public class ArchiveSystemToolRootPane extends BorderPane{

	private CreateProfileOrLoginPane cpolp;
	private ArchiveSystemMenuBar asmb;
	private UserDetailsTab udt;
	private SelectMoviesTab smt;
	private MovieDetails md;
	private TabPane tp;
	private Tab t1,t2,t3,t4;
	public ArchiveSystemToolRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		//create panes
		cpolp = new CreateProfileOrLoginPane();
		asmb = new ArchiveSystemMenuBar();
		udt = new UserDetailsTab();
		smt = new SelectMoviesTab();
		md = new MovieDetails();
		//Initialise tabs with panes added
		t1 = new Tab("Create Profile", cpolp);
		t2 = new Tab("User Details", udt);
		t3 = new Tab("Archived Movies", smt);
		t4 = new Tab("Movie Details", md);
		//add tabs to tab pane
		tp.getTabs().addAll(t1,t2,t3,t4);
		//add menu bar and tab pane to this root pane
//		this.setTop(mstmb);
		this.setCenter(tp);
		this.setTop(asmb);
	}

	public CreateProfileOrLoginPane getCreateProfileOrLoginPane()
	{
		return cpolp;
	}
	
	public ArchiveSystemMenuBar getArchiveSystemMenuBar()
	{
		return asmb;
	}
	
	public UserDetailsTab getUserDetailsTab()
	{
		return udt;
	}
	
	public SelectMoviesTab getSelectMoviesTab()
	{
		return smt;
	}
	
	public MovieDetails getMovieDetails()
	{
		return md;
	}
	
	//method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
//	//method to disable tabs via the controller
	public void disableT2(boolean value)
	{
		t2.setDisable(value);
	}
	public void disableT3(boolean value)
	{
		t3.setDisable(value);
	}
	public void disableT4(boolean value)
	{
		t4.setDisable(value);
	}
}