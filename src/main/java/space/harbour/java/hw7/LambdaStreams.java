package space.harbour.java.hw7;

import space.harbour.java.hw4.Actor;
import space.harbour.java.hw4.Director;
import space.harbour.java.hw4.Movie;
import space.harbour.java.hw4.Rating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaStreams {
	public void sortByReleaseYear(List<Movie> movies) {
		movies.sort(Comparator.comparingInt(Movie::getYear));
	}

	public void sortByRuntime(List<Movie> movies) {
		movies.sort(Comparator.comparingInt(Movie::getRuntime));
	}

	public void sortByRating(List<Movie> movies) {
		movies.sort(Comparator.comparing(movie ->
				Arrays.stream(movie.getRatings())
						.filter(rating -> "Internet Movie Database".equals(rating.getSource())).findAny().get().getValue()));
	}

	public List<Movie> filterByDirector(List<Movie> movies, Director director) {
		return movies.stream()
				.filter(movie -> movie.getDirector().getName().equals(director.getName()))
				.collect(Collectors.toList());
	}

	public List<Movie> filterByActor(List<Movie> movies, Actor actor) {
		return movies.stream()
				.filter(movie -> Arrays.stream(movie.getActors()).anyMatch(a ->
						actor.getName().equals(a.getName())))
				.collect(Collectors.toList());
	}

	public List<Movie> filterByGenre(List<Movie> movies, String genre) {
		return movies.stream()
				.filter(movie -> Arrays.stream(movie.getGenres()).anyMatch(genre::equals))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie().readFile("BladeRunner.json"));
		movies.add(new Movie().readFile("TheGraduate.json"));
		movies.add(new Movie().readFile("LostArk.json"));

		LambdaStreams ls = new LambdaStreams();
		Director hf = new Director();
		hf.setName("Steven Spielberg");
		ls.sortByRating(movies);
		movies.forEach(m -> System.out.println(m.toJsonString()));
	}
}
