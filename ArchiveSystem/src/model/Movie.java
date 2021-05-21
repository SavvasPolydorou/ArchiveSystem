package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie implements Comparable<Movie>, Serializable{
	//fields
	private String title;
	private int duration; //in minutes
	private Genre genre;
	private String director;
	private String producer;
	private LocalDate releaseDate;

	//constructor
	public Movie(String title, int duration, Genre genre, String director, String producer, LocalDate releaseDate)
	{
		this.title = title;
		this.duration = duration;
		this.genre = genre;
		this.director = director;
		this.producer = producer;
		this.releaseDate = releaseDate;
	}

	//methods
	public String getTitle()
	{
		return title;
	}

	public int getDuration()
	{
		return duration;
	}

	public Genre getGenre()
	{		
		return genre;
	}

	public String getDirector()
	{
		return director;
	}

	public String getProducer()
	{
		return producer;
	}

	public LocalDate getReleaseDate()
	{
		return releaseDate;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public void setGenre(Genre genre)
	{		
		this.genre = genre;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public void setProducer(String producer)
	{
		this.producer = producer;
	}

	public void setReleaseDate(LocalDate releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString()
	{
		return title;
	}
	public String actualToString()
	{
		return  "Movie[Title: " + title + ", duration: " + duration 
				+ ", genre: " + genre + ", director: " + director 
				+ ", producer: " + producer + ", releaseDate:" + releaseDate +"]";
	}
	@Override
	public int compareTo(Movie other) 
	{
		int result = this.director.compareTo(other.director);

		if (result == 0)
		{	
			result = this.title.compareTo(other.title);

			if (result == 0)
			{
				result = Integer.compare(this.duration, other.duration);			
			}
		}

		return result;
	}
}