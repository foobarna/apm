/**
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import domain.Delivery;

/**
 * @author blink
 *
 */
public interface Controller extends Iterable<String>{
    
    /**
     * adds a new data to storage
     * @param name
     * @param qty
     * @throws IOException
     */
    void add(String name, int qty) throws IOException;
    
    /**
     * returns data related to the given name
     * @param name
     * @return ArrayList<Delivery> that contains all deliveries for the given product name
     */
    ArrayList<Delivery>	get(String name);
    
    /**
     * 
     * @return ArrayList<Delivery> that contains all the deliveries objects
     */
    ArrayList<Delivery> getAll();
    
    /**
     * saves all data to file
     * 
     * @throws IOException
     */
    void save() throws IOException;
    
    /**
     * 
     * @return
     */
    Iterator<String> iterator();
}
