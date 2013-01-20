package repository;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Vector;

//import observer.Observer;
//import observer.Subject;

import domain.*;

public class StudentRepository extends Observable implements RepositoryInterface {
	Vector<Student>	memory = new Vector<Student>(10);
	String filename = null;
	
	public StudentRepository() {
		
	}
	
	public StudentRepository(String filename) throws ClassNotFoundException, IOException,EOFException,ClassCastException {
		this.filename = filename;
		loadFromFile();
	}
	
	
	@Override
	public boolean add(Student s) {
		memory.add(s);
		setChanged();
		notifyObservers();
		return true;
	}
	
	public boolean addGrade(int sid, Grade g) throws RepositoryException {
		for(int i = 0; i < memory.size(); i++) {
			if(memory.get(i).getSid() == sid) {
				memory.get(i).addGrade(g);
				setChanged();
				notifyObservers();
				return true;
			}				
		}
		throw new RepositoryException("Student dosent exists!");
	}

	public boolean changeGroup(int sid, int group) throws RepositoryException {
		for(int i = 0; i < memory.size(); i++) {
			if(memory.get(i).getSid() == sid) {
				memory.get(i).setGroup(group);
				setChanged();
				notifyObservers();
				return true;
			}				
		}
		throw new RepositoryException("Student dosent exists!");
	}
	
	public Student getStudentBySID(int sid) throws RepositoryException {
		for (int i = 0; i < memory.size(); i++) {
			if (memory.get(i).getSid() == sid) return memory.get(i);
		}
		throw new RepositoryException("Student dosen't exists!");
	}
	
	@Override
	public Vector<Student> getAll() {
		// TODO Auto-generated method stub
		return memory;
	}

	public Student getAt(int index) {
		// TODO Auto-generated method stub
		return memory.get(index);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return memory.size();
	}

	@Override
	public void setFilename(String file) {
		// TODO Auto-generated method stub
		this.filename = file;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadFromFile() throws EOFException, IOException, ClassNotFoundException, ClassCastException {
		// TODO Auto-generated method stub
		InputStream inputStream = new FileInputStream(filename);
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		memory = (Vector<Student>) objectInputStream.readObject();
		
		objectInputStream.close();
		inputStream.close();
	}

	@Override
	public void saveToFile() throws IOException {
		// TODO Auto-generated method stub
		OutputStream outputStream = new FileOutputStream(filename);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		objectOutputStream.writeObject(memory);


		objectOutputStream.flush();
		outputStream.flush();
		objectOutputStream.close();
		outputStream.close();
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return this.filename;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((memory == null) ? 0 : memory.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentRepository))
			return false;
		StudentRepository other = (StudentRepository) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (memory == null) {
			if (other.memory != null)
				return false;
		} else if (!memory.equals(other.memory))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentRepository [memory=" + memory + "]";
	}
}
