package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Archive<T extends Comparable<? super T>>{
	Map<Integer,T> map;
	static Integer keyGenerator;
	
	public Archive(){
		keyGenerator = 0;
		this.map = new HashMap<>();
	}
	
	/**
	 * add a new element
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean addElement(T element) {
		keyGenerator++;
		this.map.put(keyGenerator,element);
		return this.map.containsKey(keyGenerator);
	}
	
	/**
	 * return key associated to an element
	 * 
	 * @param element
	 * @return integer representing a key
	 */
	public int getElementKey(T element) {
		for(Map.Entry<Integer, T> entry : map.entrySet()) {
			if(entry.getValue().equals(element)) {
				return entry.getKey();
			}
		}
		return -1;
	}
	
	/**
	 * remove a element
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean removeElement(T element) {
		int oldKey = this.getElementKey(element);
		this.map.remove(element);
		return this.map.containsKey(oldKey);
	}

	/**
	 * get all items ordered
	 * @return List<T>
	 */
	public List<T> getAll() {
		List<T> list = new ArrayList<T>(this.map.values());
		Collections.sort(list);
		return list;
	}
}
