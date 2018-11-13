package space.harbour.java.hw7;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import space.harbour.java.hw4.Actor;
import space.harbour.java.hw4.Director;
import space.harbour.java.hw4.Movie;

import java.util.ArrayList;
import java.util.List;

public class LambdaStreamsTest extends TestCase {
	LambdaStreams ls;
	List<Movie> movies;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		ls = new LambdaStreams();
		movies = new ArrayList<>();
		movies.add(new Movie().readFile("BladeRunner.json"));
		movies.add(new Movie().readFile("TheGraduate.json"));
		movies.add(new Movie().readFile("LostArk.json"));
	}

	@After
	public void tearDown() throws Exception {
		ls = null;
		movies = null;
		super.tearDown();
	}

	@Test
	public void testSortByReleaseYear() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		ls.sortByReleaseYear(movies);

		assertEquals("The Graduate", movies.get(0).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(1).getTitle());
		assertEquals("Blade Runner", movies.get(2).getTitle());
	}

	@Test
	public void testSortByRuntime() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		ls.sortByRuntime(movies);

		assertEquals("The Graduate", movies.get(0).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(1).getTitle());
		assertEquals("Blade Runner", movies.get(2).getTitle());
	}

	@Test
	public void testSortByRating() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		ls.sortByRating(movies);

		assertEquals("The Graduate", movies.get(0).getTitle());
		assertEquals("Blade Runner", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());
	}

	@Test
	public void testFilterByGenre() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		movies = ls.filterByGenre(movies, "Drama");

		assertEquals(1, movies.size());
		assertEquals("The Graduate", movies.get(0).getTitle());
	}

	@Test
	public void testFilterByDirector() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		Director director = new Director();
		director.setName("Steven Spielberg");
		movies = ls.filterByDirector(movies, director);

		assertEquals(1, movies.size());
		assertEquals("Raiders of the Lost Ark", movies.get(0).getTitle());
	}

	@Test
	public void testFilterByActor() {
		assertEquals("Blade Runner", movies.get(0).getTitle());
		assertEquals("The Graduate", movies.get(1).getTitle());
		assertEquals("Raiders of the Lost Ark", movies.get(2).getTitle());

		Actor actor = new Actor();
		actor.setName("Harrison Ford");
		movies = ls.filterByActor(movies, actor);
		ls.sortByReleaseYear(movies);

		assertEquals(2, movies.size());
		assertEquals("Raiders of the Lost Ark", movies.get(0).getTitle());
		assertEquals("Blade Runner", movies.get(1).getTitle());
	}
}
