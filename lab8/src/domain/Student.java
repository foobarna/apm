/**
 * 
 */
package domain;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Vector;

/**
 * @author blink
 * 
 */
public class Student implements Externalizable {
	int				sid;
	String			first_name;
	String			last_name;
	String			phone;
	int				group;
	Vector<Grade>	grades	= new Vector<Grade>();

	public Student() {
		first_name = null;
		last_name = null;
		phone = null;
		sid = 0;
		group = 0;
	}

	public Student(int sid, String first_name, String last_name, String phone,
			int group) {
		this.sid = sid;
		this.first_name = first_name;
		this.last_name = last_name;
		this.phone = phone;
		this.group = group;
	}

	public void addGrade(Grade grade) {
		grades.add(grade);
	}

	public double getAverage() throws StudentException {
		double a;
		int s = 0;
		if (grades.size() == 0)
			throw new StudentException("No grades for average to be computed");
		else
			for (int i = 0; i < grades.size(); i++) {
				s += grades.get(i).getValue();
			}
		a = s / (double) grades.size();
		return a;
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
				+ ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + group;
		result = prime * result
				+ ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + sid;
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
		if (!(obj instanceof Student))
			return false;
		Student other = (Student) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (group != other.group)
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sid != other.sid)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + first_name + "," + last_name
				+ "," + phone + "," + sid + "," + group
				+ "," + grades +"]";
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the sid
	 */
	public int getSid() {
		return sid;
	}

	/**
	 * @return the group
	 */
	public int getGroup() {
		return group;
	}

	/**
	 * @return the grades
	 */
	public Vector<Grade> getGrades() {
		return grades;
	}

	/**
	 * @param grades
	 *            the grades to set
	 */
	public void setGrades(Vector<Grade> grades) {
		this.grades = grades;
	}

	/**
	 * @param first_name
	 *            the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @param last_name
	 *            the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param sid
	 *            the sid to set
	 */
	public void setSid(int sid) {
		this.sid = sid;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(int group) {
		this.group = group;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(sid);
		out.writeObject(first_name);
		out.writeObject(last_name);
		out.writeObject(phone);
		out.writeInt(group);
		out.writeObject(grades);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		sid = in.readInt();
		first_name = (String) in.readObject();
		last_name = (String) in.readObject();
		phone = (String) in.readObject();
		group = in.readInt();
		grades = (Vector<Grade>) in.readObject();
	}
}
