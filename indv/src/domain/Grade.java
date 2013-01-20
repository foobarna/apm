package domain;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Grade implements Externalizable {
    String discipline;
    int grade;
    int id;
    
    public Grade() {}

    /**
     * @param discipline
     * @param grade
     * @param no
     */
    public Grade(String discipline, int grade, int no) {
	this.discipline = discipline;
	this.grade = grade;
	this.id = no;
    }

    /**
     * @return the discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * @return the grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return id;
    }

    /**
     * @param discipline the discipline to set
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.id = no;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((discipline == null) ? 0 : discipline.hashCode());
	result = prime * result + grade;
	result = prime * result + id;
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
	if (!(obj instanceof Grade))
	    return false;
	Grade other = (Grade) obj;
	if (discipline == null) {
	    if (other.discipline != null)
		return false;
	} else if (!discipline.equals(other.discipline))
	    return false;
	if (grade != other.grade)
	    return false;
	if (id != other.id)
	    return false;
	return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Grade [discipline=" + discipline + ", grade=" + grade + ", no=" + id + "]";
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	discipline = (String) in.readObject();
	grade = in.readInt();
	id = in.readInt();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	out.writeObject(discipline);
	out.writeInt(grade);
	out.writeInt(id);
    };
    
    
}
