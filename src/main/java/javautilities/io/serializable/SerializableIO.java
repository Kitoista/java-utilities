package javautilities.io.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;

public class SerializableIO {

	public static Serializable load(String path) throws IOException, ClassNotFoundException {
		return load(new FileInputStream(new File(path)));
	}
	public static Serializable load(InputStream istream) throws IOException, ClassNotFoundException {
		try (
			ObjectInputStream in = new ObjectInputStream(istream);
		) {
			return (Serializable) in.readObject();
		}
	}
	
	public static void save(Serializable object, String path) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			Files.createFile(new File(path).toPath());
		}
		save(object, new FileOutputStream(file));
	}
	public static void save(Serializable object, OutputStream ostream) throws IOException {
		try (
			ObjectOutputStream out = new ObjectOutputStream(ostream);
		) {
			out.writeObject(object);
			out.flush();
		}
	}
	
}
