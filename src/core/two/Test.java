package core.two;

import java.util.Date;
import java.util.HashMap;

import core.two.bean.MyDate;
import core.two.file.FileService;

public class Test {

	public static void main(String[] args) {

		HashMap<MyDate, String> map = new HashMap<>();
		Date date = new Date();
		MyDate myDate = new MyDate();
		myDate.setDate(date);
		MyDate myDate2 = new MyDate();
		myDate2.setDate(new Date((date.getTime()) - 1000 * 60 * 60 * 24));
		MyDate myDate3 = new MyDate();
		myDate3.setDate(new Date((date.getTime()) - 1000 * 60 * 60 * 48));
		map.put(myDate, "test one ");
		map.put(myDate2, "test 5 ");
		map.put(myDate3, "test three ");

//		System.out.println(map.get(myDate));
//		System.out.println(map.get(myDate2));

// file test		
		FileService myFile = new FileService("F:\\test\\mapFile.txt");

		System.out.println(myFile.readMap());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		myFile.saveMap(map , true);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myFile.readMap());
	}
}
