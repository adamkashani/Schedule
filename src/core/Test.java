package core;


public class Test {

	public static void main(String[] args) {
		
//		List<Object> list = new ArrayList<>();
//		
//		Object object1 = new Object();
//		Object object2 = new Object();
//		Object object3 = new Object();
//		Object object4 = new Object();
//		Object object5 = new Object();
//		Object object6 = new Object();
//		Object object7 = new Object();
//		list.add(object1);
//		list.add(object2);
//		list.add(object3);
//		list.add(object4);
//		
//		list.add(1, object5);
//		
//		
//		System.out.println(list);
		SortedGroup<Person> group = new SortedGroup<>();
		for (int i = 0; i < 10; i++) {
			
		group.add(new Person(i, 150+i*5));
		
		}
		Person p = new Person(0, 175);
		group.add(new Person(0, 150));
		
		System.out.println(group.getList());
		System.out.println(group.remove(new Person(0, 150)));
//		Object obj = new Object();
		System.out.println(GenericList.reduce(p, group).getList());
		
	}
}
