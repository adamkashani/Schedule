package core;

import java.util.ArrayList;
import java.util.List;

public class SortedGroup<T extends Comparable<T>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<T> list;

	public SortedGroup() {
		super();
		this.list = new ArrayList<>();
	}

	public void add(T object) {
		for (int i = 0; i < list.size(); i++) {
			// if the object the smallest in the array
			if (list.get(i).compareTo(object) < 0) {
				continue;
				// if 2 object(list , new) equals
			} else if (list.get(i).compareTo(object) == 0) {
				list.add(i, object);
				return;
				// list object big from new object
			} else if (list.get(i).compareTo(object) > 0) {
				list.add(i, object);
				return;
			}
		}
		// only if the size = 0
		list.add(object);
	}

	public int remove(T object) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(object)) {
				this.list.remove(i);
				count++;
				i--;
			}
		}
		return count;
	}

	public List<T> getList() {
		return list;
	}

}
