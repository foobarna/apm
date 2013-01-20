package repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import domain.Delivery;
import adt.Dictionary;
import impl.ConcreteDictionary;

public class DeliveryRepository implements Iterable<String>, Repository {
    Dictionary<String, ArrayList<Delivery>> memory;
    String filename = null;
    int size = 0;
    int items = 0;
    
    public DeliveryRepository() {
	memory = new ConcreteDictionary<String, ArrayList<Delivery>>();
    }
    
    public DeliveryRepository(String file) throws ClassNotFoundException, IOException {
	filename = file;
	loadFromFile();
    }
    
    public boolean add(Delivery d) {
	String name = d.getProduct();
	if ( memory.containsKey(name)) {
	    ArrayList<Delivery> deliveries = memory.getValueByKey(name);
	    deliveries.add(d);
	    memory.changeExistingEntry(name,deliveries);
	} else {
	    ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
	    deliveries.add(d);
	    memory.addNewEntry(name, deliveries);
	    items++;
	}
	size++;
	return true;
    }

    public int getSize() {
	return size;
    }

    public ArrayList<Delivery> get(String name) {
	return memory.getValueByKey(name);
    }
    
    public ArrayList<Delivery> getAll() {
	ArrayList<Delivery> all = new ArrayList<Delivery>(this.items);
	for(String product: memory) {
	    all.addAll(memory.getValueByKey(product));
	}
	return all;
    }
    
    public void setFilename(String file) {
	filename = file;
    }
    
    @Override
    public Iterator<String> iterator() {
	return memory.iterator();
    }
    
    @SuppressWarnings("unchecked")
    public void loadFromFile() throws IOException, ClassNotFoundException {
	InputStream inputStream = new FileInputStream(filename);
	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

	memory = (Dictionary<String, ArrayList<Delivery>>) objectInputStream.readObject();
	
	objectInputStream.close();
	inputStream.close();
    }
    
    public void saveToFile() throws IOException {
	OutputStream outputStream = new FileOutputStream(filename);
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

	objectOutputStream.writeObject(memory);


	objectOutputStream.flush();
	outputStream.flush();
	objectOutputStream.close();
	outputStream.close();
    }
}
