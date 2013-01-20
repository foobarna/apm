package domain;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Student implements Externalizable{
    String name;						// name of student
    String pin;							// cnp, 13 digits
    int id;							// id matricol
    int no;							// no = position in classbook
    
    /**
     * @param name
     * @param pin
     * @param id
     * @param no
     */
    public Student(String name, String pin, int id, int no) {
	this.name = name;
	this.pin = pin;
	this.id = id;
	this.no = no;
    }

    /**
     * default constructor
     */
    public Student() {
	this(null, null,0,0);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Student [name=" + name + ", id=" + id + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + no;
	result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
	if (!(obj instanceof Student))
	    return false;
	Student other = (Student) obj;
	if (id != other.id)
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (no != other.no)
	    return false;
	if (pin == null) {
	    if (other.pin != null)
		return false;
	} else if (!pin.equals(other.pin))
	    return false;
	return true;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	name = (String) in.readObject();
	pin = (String) in.readObject();
	id = in.readInt();
	no = in.readInt();
	
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	out.writeObject(name);
	out.writeObject(pin);
	out.writeInt(id);
	out.writeInt(no);
    }
}
