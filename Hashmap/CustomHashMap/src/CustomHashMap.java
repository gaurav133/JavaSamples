
/**
 * Implementation of a custom hashmap. Skips resizing and other issues. Supports
 * get, put, remove operations.
 * 
 * @author gaurav
 *
 */
public class CustomHashMap<K, V> {

	/**
	 * Default Constructor.
	 */
	public CustomHashMap() {
		table = new Entry[DEFAULT_CAPACITY];
	}

	private static final int DEFAULT_CAPACITY = 16;

	/** Array of entries. */
	private Entry[] table;

	/** Entry class. Holds the key, value, hash and the next pointer */
	static final class Entry<K, V> {
		final K key;
		V value;
		final int hash;
		Entry<K, V> next;

		Entry(K key, V value, int hash, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}

		/** Getter for key */
		public K getKey() {
			return this.key;
		}

		/** Getter for value */
		public V getValue() {
			return this.value;
		}
	}

	/** Get value corresponding to given key */
	public V get(K key) {
		V value = null;

		// First check for null key.
		if (key == null) {
			value = getForNullKey();
		} else {
			// Get key hashcode and calculate hash from it.
			int hash = hash(key);
			int index = hash & (table.length - 1);

			// Traverse this index.
			Entry<K, V> e = table[index];

			if (e != null) {
				while (e.next != null) {
					if (e.hash == hash && (e.key == key || e.key.equals(key))) {
						value = e.getValue();
						break;
					}
					e = e.next;
				}
			}
		}
		return value;
	}

	/**
	 * Put value corresponding to given key
	 * 
	 * @param key
	 *            Given key.
	 * @param value
	 *            Value to be inserted.
	 * @return Old value in case value is overridden, null otherwise.
	 */
	public V put(K key, V value) {
		V oldValue = null;

		// First check for null key.
		if (key == null) {
			oldValue = putForNullKey(value);
		} else {
			// Get key hashcode and calculate hash from it.
			int hash = hash(key);
			int index = hash & (table.length - 1);

			// Traverse this index.
			Entry<K, V> e = table[index];

			if (e != null) {
				while (e.next != null) {
					if (e.hash == hash && (e.key == key || e.key.equals(key))) {
						oldValue = e.getValue();
						e.value = value;
						break;
					}
					e = e.next;
				}
			}

			// If key not found, insert a new entry.
			e = table[index];
			
			Entry newEntry = null;
			if (e == null) {
				newEntry = addEntry(key, value, hash, null); 
				newEntry.next = null;
			} else {
				newEntry = addEntry(key, value, hash, e.next);
				newEntry.next = e;
			}
			
			table[index] = newEntry;
		}
		return oldValue;

	}

	private Entry addEntry(K key, V value, int hash, Entry next) {
		return new Entry(key, value, hash, next);
	}

	/** Fetch the value corresponding to null key */
	private V getForNullKey() {

		V value = null;

		/** hash for null key is 0 */
		Entry<K, V> e = table[0];

		if (e != null) {
			while (e.next != null) {

				// Compare the keys.
				if (e.getKey() == null) {
					value = e.getValue();
					break;
				}
				e = e.next;
			}
		}

		return value;
	}

	/** Insert the value corresponding to null key */
	private V putForNullKey(V val) {
		V oldValue = null;

		/** hash for null key is 0 */
		Entry<K, V> e = table[0];

		if (e != null) {
			while (e.next != null) {

				// Compare the keys.
				if (e.getKey() == null) {
					oldValue = e.getValue();
					e.value = val;
				}
				e = e.next;
			}
		}

		return oldValue;
	}

	/** Compute the hash of the key */
	private int hash(K key) {
		return (key.hashCode()) ^ (key.hashCode() >>> 20) ^ (key.hashCode() >>> 16) ^ (key.hashCode() >>> 12)
				^ (key.hashCode() >>> 2);
	}

	public void printMap() {
		// TODO Auto-generated method stub
		String str;

		Entry e;
		for (int i = 0; i < table.length; i++) {
			e = table[i];

			while (e != null) {
				System.out.println(" " + e.getKey() + " " + e.getValue());
				e = e.next;
			}
		}
	}
}

class TestMap {
	public static void main(String[] args) {
		CustomHashMap<String, Integer> map = new CustomHashMap<>();

		map.put("a", 1);
		map.put("b", 2);
		map.put("a", 3);
		map.put("c", 3);

		map.put("d", 3);

		map.put("abd", 3);

		map.put("ak", 3);

		map.printMap();
	}
}