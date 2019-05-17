package core.two.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class MyDate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int yers;
	private int month;
	private int day;

	public void setDate(Date date) {
//		System.out.println("the date befor format");
		Calendar calendar =	Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		yers = calendar.get(Calendar.YEAR);
	}
	public void setDate(int yers , int month , int day) {
		this.month = month;
		this.day = day;
		this.yers = yers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + yers;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyDate other = (MyDate) obj;
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (yers != other.yers)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MyDate [yers=" + yers + ", month=" + month + ", day=" + day + "]";
	}
	
}
