/**
 * 
 */
package repository;

import java.io.IOException;
import java.util.Vector;

import domain.Student;

/**
 * @author blink
 *
 */
public interface RepositoryInterface {
	boolean add(Student s);
	Vector<Student> getAll();
	int getSize();
	
	String getFilename();
	
	/**
	 * sets a given file
	 * @param file
	 */
	void setFilename(String file);
	
	/**
	 * loads the data from file
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void loadFromFile() throws IOException, ClassNotFoundException;
	
	/**
	 * saves data to file
	 * @throws IOException
	 */
	void saveToFile() throws IOException;
	
}
