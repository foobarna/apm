package repository;

import java.io.*;
import java.util.ArrayList;
import domain.Grade;
import domain.GradeValidator;

public class GradesRepo implements Externalizable {
    private String filename = null;
    private ArrayList<Grade> repo;
    private GradeValidator v = new GradeValidator();

    public GradesRepo() {
	repo = new ArrayList<Grade>();
    }

    public GradesRepo(String filename) throws IOException,
	    FileNotFoundException, ClassNotFoundException {
	this.filename = filename;
	this.loadFromFile();
    }

    public void addGrade(Grade g) {
	// throws validatorException
	v.validate(g);
	repo.add(g);
    }

    
    public ArrayList<Grade> getGrades() {
    	return repo;
    }
    
    public ArrayList<Grade> getGradesForId(int id) {
    	ArrayList<Grade> tmp = new ArrayList<Grade>();
    	for (int i=0; i< repo.size(); i++) {
    		if (repo.get(i).getNo() == id) tmp.add(repo.get(i));
    	}
    	return tmp;
    }
    
    public ArrayList<Grade> getGradesForIdAndDisc(int id, String disc) {
    	ArrayList<Grade> tmp = new ArrayList<Grade>();
    	for (int i=0; i< repo.size(); i++) {
    		if ((repo.get(i).getNo() == id) && (repo.get(i).getDiscipline() == disc)) tmp.add(repo.get(i));
    	}
    	return tmp;   	
    }
    
    public float getAverageForId(int id) {
    	float s = 0;
    	int count = 0;
    	for (int i=0; i< repo.size(); i++) {
    		if (repo.get(i).getNo() == id) {
    			s = s + repo.get(i).getGrade();
    			count++;
    		}
    	}
    	return s/count;
    }
    
    public boolean save() throws IOException {
    	return writeToFile();
    }

    @SuppressWarnings("unchecked")
    private boolean loadFromFile() throws IOException, FileNotFoundException,
	    ClassNotFoundException {
	InputStream inputStream = new FileInputStream(filename);
	ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

	this.repo = (ArrayList<Grade>) objectInputStream.readObject();

	objectInputStream.close();
	inputStream.close();
	return true;
    }

    private boolean writeToFile() throws IOException {
	OutputStream outputStream = new FileOutputStream(filename);
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(
		outputStream);

	objectOutputStream.writeObject(repo);

	objectOutputStream.flush();
	outputStream.flush();
	objectOutputStream.close();
	outputStream.close();
	return true;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
	    ClassNotFoundException {
	while (true) {
	    Grade s = (Grade) in.readObject();
	    if (s == null)
		break;
	    repo.add(s);
	}

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	for (Grade s : repo) {
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
    public ArrayList<Grade> getRepo() {
	return repo;
    }

    /**
     * @param filename
     *            the filename to set
     */
    public void setFilename(String filename) {
	this.filename = filename;
    }

    /**
     * @param repo
     *            the repo to set
     */
    public void setRepo(ArrayList<Grade> repo) {
	this.repo = repo;
    }

    /*
     * (non-Javadoc)
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof GradesRepo))
	    return false;
	GradesRepo other = (GradesRepo) obj;
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
