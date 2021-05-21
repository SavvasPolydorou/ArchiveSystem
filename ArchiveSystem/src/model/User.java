package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable{
	//fields
	private Name name;
	private Gender gender;
	private Set<Movie> list;
	private LocalDate dob;
	private String email;
	private String password;

	//constructor
	public User()
	{
		name = new Name();
		gender = null;
		list =new TreeSet<Movie>();
		dob = null;
		email = "";
		password = "";
	}

	//methods
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public void setDoB(LocalDate dob)
	{
		this.dob = dob;
	}

	public LocalDate getDoB()
	{
		return dob;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
		return password;
	}
	public boolean addMovie(Movie movie)
	{
		return list.add(movie);
	}

	public boolean populateList(List<Movie> movies)
	{
		return list.addAll(movies);
	}
	public void clearList()
	{
		list.clear();
	}
	
	public void removeMovie(Movie movie)
	{
		list.remove(movie);
	}
	
	public String toString()
	{
		return "User[" + name + "\n"
				+ "Gender: " + gender + "\n"
				+ "Movies: " + list + "\n"
				+ "DoB: " + dob + "\n"
				+ "Email: " + email + "\n"
				+ "Password: " + password + "\n";
	}
}
