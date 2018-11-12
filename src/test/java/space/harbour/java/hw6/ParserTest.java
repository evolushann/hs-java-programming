package space.harbour.java.hw6;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class ParserTest extends TestCase {
	URLParser parser;
	Queue<URL> list;

	public ParserTest() { super(String.valueOf(URLParser.class)); }

	@Before
	public void setUp() throws Exception {
		super.setUp();
		list = new LinkedList<>();
		parser = new URLParser();
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
		list = null;
		super.tearDown();
	}

	public void testParser() throws MalformedURLException {
		list.add(new URL("https://vasart.github.io/harbour-java-course/"));
		parser.setQueue(list);
		parser.runParser();

		if(parser.getExecutor().isTerminated()) {
			assertTrue(parser.getVisited().contains("https://vasart.github.io/harbour-java-course/"));
			assertTrue(parser.getVisited().contains("https://vasart.github.io/harbour-java-course/page1.html"));
			assertTrue(parser.getVisited().contains("https://vasart.github.io/harbour-java-course/page2.html"));
			assertTrue(parser.getVisited().contains("https://vasart.github.io/harbour-java-course/page3.html"));
			assertEquals(4, parser.getVisited().size());
		}
	}
}
