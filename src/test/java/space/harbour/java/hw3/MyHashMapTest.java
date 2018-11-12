package space.harbour.java.hw3;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyHashMapTest extends TestCase {
	private MyHashMap<String, String> map;

	public MyHashMapTest() {
		super(String.valueOf(MyHashMap.class));
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		map = new MyHashMap<>();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSize_empty() {
		assertEquals(0, map.size());
	}

	@Test
	public void testSize_notEmpty() {
		map.put("name", "bob");

		assertEquals(1, map.size());
	}

	@Test
	public void testSize_withCollisions() {
		map.put("name", "bob");
		map.put("name", "vagene");

		assertEquals(2, map.size());
	}

	@Test
	public void testSize_noCollisions() {
		map.put("name", "bob");
		map.put("hello", "world");

		assertEquals(2, map.size());
	}

	@Test
	public void testIsEmpty_empty() {
		assertTrue(map.isEmpty());
	}

	@Test
	public void testIsEmpty_noCollision() {
		map.put("name", "bob");
		map.put("hello", "world");

		assertFalse(map.isEmpty());
	}

	@Test
	public void testIsEmpty_withCollision() {
		map.put("name", "bob");
		map.put("name", "vagene");

		assertFalse(map.isEmpty());
	}

	@Test
	public void testContainsKey_keyNotFound() {
		assertFalse(map.containsKey("name"));
	}

	@Test
	public void testContainsKey_singleKey() {
		map.put("name", "bob");

		assertTrue(map.containsKey("name"));
	}

	@Test
	public void testContainsKey_multipleSameKeys() {
		map.put("name", "bob");
		map.put("name", "vagene");

		assertTrue(map.containsKey("name"));
	}

	@Test
	public void testContainsValue_valueNotFound() {
		assertFalse(map.containsValue("name"));
	}

	@Test
	public void testContainsValue_singleValue() {
		map.put("name", "bob");

		assertTrue(map.containsValue("bob"));
	}

	@Test
	public void testContainsValue_multipleValues() {
		map.put("name", "bob");
		map.put("other name", "bob");

		assertTrue(map.containsValue("bob"));
	}

	@Test
	public void testGet_nonexistentKey() {
		assertNull(map.get("name"));
	}

	@Test
	public void testGet_singleKey() {
		map.put("name", "bob");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	// Should return first instance of key
	@Test
	public void testGet_sameKey() {
		map.put("name", "bob");
		map.put("name", "vagene");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	@Test
	public void testGet_noCollision() {
		map.put("name", "bob");
		map.put("hello", "world");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	@Test
	public void testGet_withCollision() {
		map.put("name", "bob");
		map.put("name2", "vagene");

		assertNotNull(map.get("name2"));
		assertEquals("vagene", map.get("name2"));
	}

	@Test
	public void testPut_singlePair() {
		assertTrue(map.isEmpty());

		map.put("name", "bob");

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertTrue(map.containsValue("bob"));
		assertEquals(1, map.size());
	}

	@Test
	public void testPut_nullValue() {
		assertTrue(map.isEmpty());

		map.put("name", null);

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertNull(map.get("name"));
		assertEquals(1, map.size());
	}

	@Test
	public void testPut_noCollisions() {
		assertTrue(map.isEmpty());

		map.put("name", "bob");
		map.put("hello", "world");

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertTrue(map.containsKey("hello"));
		assertTrue(map.containsValue("bob"));
		assertTrue(map.containsValue("world"));
		assertEquals(2, map.size());
	}

	@Test
	public void testPut_withCollisions() {
		assertTrue(map.isEmpty());

		map.put("name", "bob");
		map.put("name2", "vagene");

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertTrue(map.containsKey("name2"));
		assertTrue(map.containsValue("bob"));
		assertTrue(map.containsValue("vagene"));
		assertEquals(2, map.size());
	}

	@Test
	public void testRemove_emptyMap() {
		assertNull(map.remove("hello"));
	}

	@Test
	public void testRemove_keyNotFound() {
		map.put("name", "bob");

		assertNull(map.remove("hello"));
	}

	@Test
	public void testRemove_noCollisions() {
		map.put("name", "bob");
		map.put("hello", "world");

		assertEquals(2, map.size());
		assertEquals("world", map.remove("hello"));
		assertEquals(1, map.size());
	}

	// Should remove first instance of key
	@Test
	public void testRemove_sameKey() {
		map.put("name", "bob");
		map.put("name", "vagene");

		assertEquals(2, map.size());
		assertEquals("bob", map.remove("name"));
		assertEquals(1, map.size());
	}

	@Test
	public void testRemove_withCollisions() {
		map.put("name", "bob");
		map.put("name2", "vagene");

		assertEquals(2, map.size());
		assertEquals("vagene", map.remove("name2"));
		assertEquals(1, map.size());
	}

	@Test
	public void testClear() {
		assertTrue(map.isEmpty());

		map.put("name", "bob");
		map.put("name", "vagene");

		assertFalse(map.isEmpty());

		map.clear();

		assertTrue(map.isEmpty());
	}
}