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
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream out = null;
		try {
			if (append == true) {
				HashMap<MyDate, String> readMap = readMap();
				if (readMap == null) {
					readMap = new HashMap<>();
				}
				readMap.putAll(map);
				fileOutputStream = new FileOutputStream(file, false);
				out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(readMap);
				out.flush();
			} else {
				fileOutputStream = new FileOutputStream(file, append);
				out = new ObjectOutputStream(fileOutputStream);
				out.writeObject(map);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(out);
			close(fileOutputStream);
		}
	}

	/**
	 * this method get map and save it on file first clear file and write the object
	 * 
	 * @param map map to save on file
	 */
	public void saveMap(HashMap<MyDate, String> map) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream out = null;
		try {
			fileOutputStream = new FileOutputStream(file, false);
			out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(map);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(out);
			close(fileOutputStream);
		}
	}

	public HashMap<MyDate, String> readMap() throws RuntimeException {
		if (checkFileCanRead(this.file)) {
			FileInputStream fileInputStream = null;
			ObjectInputStream inputStream = null;
			try {
				fileInputStream = new FileInputStream(file);
				inputStream = new ObjectInputStream(fileInputStream);
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
			} finally {
				close(inputStream);
				close(fileInputStream);
			}
		}
		return null;
	}

	public void close(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
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