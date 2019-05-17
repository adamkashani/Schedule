package core.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import core.bean.MyDate;

public class FileService {

	private File file;

	public FileService(String pathToFile) {
		this.file = new File(pathToFile);
	}

	public FileService(File file) {
		this.file = file;
	}

	public void saveMap(HashMap<MyDate, String> map, boolean append) {
		try {
			if (append == true) {
				HashMap<MyDate, String> readMap = readMap();
				if(readMap == null) {
					readMap = new HashMap<>();
				}
				readMap.putAll(map);
				FileOutputStream fileOutputStream = new FileOutputStream(file, false);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(readMap);
				out.flush();
				close(out);
				close(fileOutputStream);
			} else {
				FileOutputStream fileOutputStream = new FileOutputStream(file, append);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(map);
				out.flush();
				close(out);
				close(fileOutputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * this method get map and save it on file first clear file and write the object 
	 * @param map map to save on file
	 */
	public void saveMap(HashMap<MyDate, String> map) {
		try {
				FileOutputStream fileOutputStream = new FileOutputStream(file, false);
				ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(map);
				out.flush();
				close(out);
				close(fileOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<MyDate, String> readMap() throws RuntimeException {
		if (checkFileCanRead(this.file)) {
			try (FileInputStream fileInputStream = new FileInputStream(file);
					ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);) {
				Object object = inputStream.readObject();

				if (object instanceof HashMap<?, ?>) {
					@SuppressWarnings("unchecked")
					HashMap<MyDate, String> hashMap = (HashMap<MyDate, String>) object;
					return hashMap;
				} else {
					throw new RuntimeException("the file can't be read");
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void close(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean checkFileCanRead(File file) {
		try {
			FileReader fileReader = new FileReader(file.getAbsolutePath());
			int result = fileReader.read();
			fileReader.close();
			if (result == -1) {
				System.out.println(("no object to read file empty"));
				return false;
			}
		} catch (Exception e) {
			System.out.println(
					("Exception when checking if file could be read with message:" + e.getMessage() + ", " + e));
			return false;
		}
		return true;
	}

}