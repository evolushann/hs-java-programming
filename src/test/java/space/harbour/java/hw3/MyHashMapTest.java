package space.harbour.java.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {
	private MyHashMap<String, String> map;

	@Test
	void testSize_empty() {
		map = new MyHashMap<>();

		assertEquals(0, map.size());
	}

	@Test
	void testSize_notEmpty() {
		map = new MyHashMap<>();
		map.put("name", "bob");

		assertEquals(1, map.size());
	}

	@Test
	void testSize_withCollisions() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name", "vagene");

		assertEquals(2, map.size());
	}

	@Test
	void testSize_noCollisions() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("hello", "world");

		assertEquals(2, map.size());
	}

	@Test
	void testIsEmpty_empty() {
		map = new MyHashMap<>();

		assertTrue(map.isEmpty());
	}

	@Test
	void testIsEmpty_noCollision() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("hello", "world");

		assertFalse(map.isEmpty());
	}

	@Test
	void testIsEmpty_withCollision() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name", "vagene");

		assertFalse(map.isEmpty());
	}

	@Test
	void testContainsKey_keyNotFound() {
		map = new MyHashMap<>();

		assertFalse(map.containsKey("name"));
	}

	@Test
	void testContainsKey_singleKey() {
		map = new MyHashMap<>();
		map.put("name", "bob");

		assertTrue(map.containsKey("name"));
	}

	@Test
	void testContainsKey_multipleSameKeys() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name", "vagene");

		assertTrue(map.containsKey("name"));
	}

	@Test
	void testContainsValue_valueNotFound() {
		map = new MyHashMap<>();

		assertFalse(map.containsValue("name"));
	}

	@Test
	void testContainsValue_singleValue() {
		map = new MyHashMap<>();
		map.put("name", "bob");

		assertTrue(map.containsValue("bob"));
	}

	@Test
	void testContainsValue_multipleValues() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("other name", "bob");

		assertTrue(map.containsValue("bob"));
	}

	@Test
	void testGet_nonexistentKey() {
		map = new MyHashMap<>();

		assertNull(map.get("name"));
	}

	@Test
	void testGet_singleKey() {
		map = new MyHashMap<>();
		map.put("name", "bob");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	// Should return first instance of key
	@Test
	void testGet_sameKey() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name", "vagene");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	@Test
	void testGet_noCollision() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("hello", "world");

		assertNotNull(map.get("name"));
		assertEquals("bob", map.get("name"));
	}

	@Test
	void testGet_withCollision() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name2", "vagene");

		assertNotNull(map.get("name2"));
		assertEquals("vagene", map.get("name2"));
	}

	@Test
	void testPut_singlePair() {
		map = new MyHashMap<>();

		assertTrue(map.isEmpty());

		map.put("name", "bob");

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertTrue(map.containsValue("bob"));
		assertEquals(1, map.size());
	}

	@Test
	void testPut_nullKey() {
		map = new MyHashMap<>();

		assertTrue(map.isEmpty());

		Executable putNull = () -> map.put(null, "bob");

		assertThrows(NullPointerException.class, putNull);
	}

	@Test
	void testPut_nullValue() {
		map = new MyHashMap<>();

		assertTrue(map.isEmpty());

		map.put("name", null);

		assertFalse(map.isEmpty());
		assertTrue(map.containsKey("name"));
		assertNull(map.get("name"));
		assertEquals(1, map.size());
	}

	@Test
	void testPut_noCollisions() {
		map = new MyHashMap<>();

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
	void testPut_withCollisions() {
		map = new MyHashMap<>();

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
	void testRemove_emptyMap() {
		map = new MyHashMap<>();

		assertNull(map.remove("hello"));
	}

	@Test
	void testRemove_keyNotFound() {
		map = new MyHashMap<>();
		map.put("name", "bob");

		assertNull(map.remove("hello"));
	}

	@Test
	void testRemove_noCollisions() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("hello", "world");

		assertEquals(2, map.size());
		assertEquals("world", map.remove("hello"));
		assertEquals(1, map.size());
	}

	// Should remove first instance of key
	@Test
	void testRemove_sameKey() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name", "vagene");

		assertEquals(2, map.size());
		assertEquals("bob", map.remove("name"));
		assertEquals(1, map.size());
	}

	@Test
	void testRemove_withCollisions() {
		map = new MyHashMap<>();
		map.put("name", "bob");
		map.put("name2", "vagene");

		assertEquals(2, map.size());
		assertEquals("vagene", map.remove("name2"));
		assertEquals(1, map.size());
	}

	@Test
	void testClear() {
		map = new MyHashMap<>();

		assertTrue(map.isEmpty());

		map.put("name", "bob");
		map.put("name", "vagene");

		assertFalse(map.isEmpty());

		map.clear();

		assertTrue(map.isEmpty());
	}
}