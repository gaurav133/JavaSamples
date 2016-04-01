import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class to clear concepts regarding hashmap.
 * @author gaurav
 *
 */
public class HashmapOps {
	
	public static void main(String[] args) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("bcd", 5);
		map.put("abc", 8);
		map.put("gvc", 11);
		
		HashmapOps obj = new HashmapOps();
		
		// One method is to put map entries to tree map, it will sort it according to values.
		TreeMap<String, Integer> treemap = new TreeMap<>(map);
		//sobj.printMap(treemap);
		
		SortedSet<Map.Entry<String, Integer>> set = obj.sortEntriesByValue(map);
		obj.printValues(set);
		
		obj.sortByKey(map);
	}
	
	/** Class to sort map according to it's key/value (using Collections.sort) */
	private final void sortByKey(HashMap<String, Integer> map) {
		
		// Get a list of map keys.
		Set<Entry<String, Integer>> set = map.entrySet();
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(set);
		Collections.sort(list, new KeySortComparator());
	}
	
	/** Class to sort map according to it's keys. */
	public static final class KeySortComparator implements Comparator<Map.Entry<String, Integer>> {
		@Override
		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			return o1.getKey().compareTo(o2.getKey());
		}
	}
	
	/** Class to sort map according to it's value */
	public static final class ValueSortComparator implements Comparator<Map.Entry<String, Integer>> {
		@Override
		public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			return o1.getValue() - o2.getValue();
		}
	}
	
	/** Prints a map in key/value format */
	private void printMap(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
	}
	
	/** Prints values from a set of Map.Entry<> */
	private void printValues(Set<Map.Entry<String, Integer>> set) {
		
		for(Map.Entry<String, Integer> entry : set) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
	}
	
	/** Sort the map using TreeMap/TreeSet according to key/values. */
	private SortedSet<Map.Entry<String, Integer>> sortEntriesByValue(Map<String, Integer> map) {
		
		SortedMap<Map.Entry<String, Integer>, Integer> sortedMap = new TreeMap<Map.Entry<String, Integer>, Integer>(new ValueSortComparator());
		SortedSet<Map.Entry<String, Integer>> set = new TreeSet<Map.Entry<String, Integer>>(new ValueSortComparator());
		set.addAll(map.entrySet());
		return set;
	}
}
