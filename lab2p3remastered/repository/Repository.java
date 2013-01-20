/**
 * 
 */
package repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import domain.Delivery;

/**
 * @author blink
 *
 */
public interface Repository {
    	
    	/**
    	 * 
    	 * @param d
    	 * @return
    	 */
    	boolean add(Delivery d);
    	
    	/**
    	 * 
    	 * @return
    	 */
    	int getSize();
    	
    	/**
    	 * 
    	 * @param name
    	 * @return
    	 */
    	ArrayList<Delivery> get(String name);
    	
    	/**
    	 * it gets all the data from memory
    	 * @return ArrayList<Delivery> with all data
    	 */
    	ArrayList<Delivery> getAll();
    	
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
    	
    	/**
    	 * 
    	 * @return
    	 */
    	Iterator<String> iterator();
    	
}
