package controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import domain.Delivery;
import repository.Repository;

public class StorageController implements Iterable<String>, Controller{
    Repository repo;
    
    
    public StorageController(Repository r){
	repo = r;
    }
    
    public void add(String name, int qty) throws IOException {
	Delivery d = new Delivery(name, qty, new Date());
	repo.add(d);
	repo.saveToFile();
    }
    
    public void add(String name, int qty, Date date) {
	Delivery d = new Delivery(name, qty, date);
	repo.add(d);
    }
    
    public ArrayList<Delivery>	get(String name) {
	return repo.get(name);
    }
    
    public ArrayList<Delivery> getAll() {
	return repo.getAll();
    }
    
    public void save() throws IOException	{
	repo.saveToFile();
    }

    @Override
    public Iterator<String> iterator() {
	return repo.iterator();
    }
}
