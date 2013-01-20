package repository;

import java.io.*;

import java.util.ArrayList;

import domain.Student;
import domain.StudentValidator;

public class StudentsRepo implements Externalizable{
    private String filename = null;
    private ArrayList<Student> repo;
    private StudentValidator v = new StudentValidator();
    
    public StudentsRepo() {
	repo = new ArrayList<Student>();
    }
    
    public StudentsRepo(String filename) throws IOException, FileNotFoundException, ClassNotFoundException{
	this.filename = filename;
	this.loadFromFile();
    }
    
    
    public void addStudent(Student s) throws RuntimeException{
// throws validatorException
    	v.validate(s);
    	for(int i=0; i<repo.size(); i++) {
    		if ((repo.get(i).getId() == s.getId()) || (repo.get(i).getNo() == s.getNo()) || (repo.get(i).getPin() == s.getPin()) ) throw new RuntimeException("Student exists!");
    	}
    	repo.add(s);
    }    
    
    public boolean save() throws IOException{
	return writeToFile();
    }
    
    @SuppressWarnings("unchecked")
    private boolean loadFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
	InputStream inputStream = new FileInputStream(filename);
	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	
	this.repo = (ArrayList<Student>) objectInputStream.readObject();
	
	objectInputStream.close();
	inputStream.close();
	return true;
    }
    
    
    private boolean writeToFile() throws IOException {
	OutputStream outputStream = new FileOutputStream(filename);
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

	objectOutputStream.writeObject(repo);


	objectOutputStream.flush();
	outputStream.flush();
	objectOutputStream.close();
	outputStream.close();
	return true;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	while(true) {
	    Student s = (Student) in.readObject();
	    if (s == null) break;
	    repo.add(s);
	}
	
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	for (Student s: repo) {
	    out.writeObject(s);
	}
	out.writeObject(null);
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return the repo
     */
    public ArrayList<Student> getRepo() {
        return repo;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @param repo the repo to set
     */
    public void setRepo(ArrayList<Student> repo) {
        this.repo = repo;
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
	result = prime * result + ((repo == null) ? 0 : repo.hashCode());
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
	if (!(obj instanceof StudentsRepo))
	    return false;
	StudentsRepo other = (StudentsRepo) obj;
	if (filename == null) {
	    if (other.filename != null)
		return false;
	} else if (!filename.equals(other.filename))
	    return false;
	if (repo == null) {
	    if (other.repo != null)
		return false;
	} else if (!repo.equals(other.repo))
	    return false;
	return true;
    }
}
