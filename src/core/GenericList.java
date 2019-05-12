package core;

import java.util.List;

public class GenericList {
	
	public static <T extends Comparable<T>> SortedGroup<T> reduce(T x , SortedGroup<T> sGroup){
		
		SortedGroup<T> sortedGroup = new SortedGroup<>();
		List<T> newList =  sortedGroup.getList();
		List<T> list =  sGroup.getList();
		
		for (int i = 0; i < sGroup.getList().size(); i++) {
			
			if(list.get(i).compareTo(x)==1) {
				newList.add(list.get(i));
			}
		}
		return sortedGroup;
	}
}
