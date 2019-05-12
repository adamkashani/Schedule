package core;

public class Person implements Comparable<Person> {

	private int id;
	private int heightCm;

	public Person() {
		super();
	}

	public Person(int id, int heightCm) {
		super();
		this.id = id;
		this.heightCm = heightCm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(int heightCm) {
		this.heightCm = heightCm;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", heightCm=" + heightCm + "]";
	}

	@Override
	public int compareTo(Person other) {
		if (this.heightCm == other.getHeightCm())
			return 0;
		else
			return this.heightCm > other.getHeightCm() ? 1 : -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heightCm;
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
		Person other = (Person) obj;
		if (heightCm != other.heightCm)
			return false;
		return true;
	}

}
