package space.harbour.java.hw10;

import com.mongodb.BasicDBObject;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import space.harbour.java.hw4.Movie;

public class MovieDBExecutorTest extends TestCase {
	MovieDBExecutor executor;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		executor = new MovieDBExecutor();
	}

	@After
	public void tearDown() throws Exception {
		executor.execDeleteAll();
		executor.close();
		super.tearDown();
	}

	public void testInsertSingle() {
		executor.execInsert(new Movie().readFile("BladeRunner.json"));

		assertEquals(1, executor.execQuery(new BasicDBObject()).size());
	}

	public void testInsertMultiple() {
		executor.execInsert(new Movie().readFile("BladeRunner.json"));
		executor.execInsert(new Movie().readFile("LostArk.json"));
		executor.execInsert(new Movie().readFile("TheGraduate.json"));

		assertEquals(3, executor.execQuery(new BasicDBObject()).size());
	}

	public void testQuerySingle() {
		executor.execInsert(new Movie().readFile("BladeRunner.json"));
		executor.execInsert(new Movie().readFile("LostArk.json"));
		executor.execInsert(new Movie().readFile("TheGraduate.json"));

		BasicDBObject query = new BasicDBObject();
		query.put("Title", "Blade Runner");

		assertEquals(1, executor.execQuery(query).size());
		assertEquals("Blade Runner", executor.execQuery(query).get(0).getTitle());
	}

	public void testQueryMultiple() {
		executor.execInsert(new Movie().readFile("BladeRunner.json"));
		executor.execInsert(new Movie().readFile("LostArk.json"));
		executor.execInsert(new Movie().readFile("TheGraduate.json"));

		BasicDBObject query = new BasicDBObject();
		query.put("Year", new BasicDBObject("$gt", 1980));

		assertEquals(2, executor.execQuery(query).size());
	}
}
