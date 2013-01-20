package domain;

import java.util.Date;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Delivery implements Externalizable, Cloneable {
    private Date date = new Date();
    private String product = null;
    private int qty = 0;

    public Delivery() {
	product = "test";
	qty = 4;
    }

    public Delivery(String product, int qty, Date date) {
	this.date = (Date) date.clone();
	this.product = product;
	this.qty = qty;
    }

    public Date getDate() {
	return date;
    }

    public String getProduct() {
	return product;
    }

    public int getQty() {
	return qty;
    }

    public void setDate(Date date) {
	this.date = (Date) date.clone();
    }

    public void setProduct(String prod) {
	this.product = prod;
    }

    public void setQty(int qty) {
	this.qty = qty;
    }

    public String toString() {
	return product + " of quantity " + qty + " @ " + date.toString();
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
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((product == null) ? 0 : product.hashCode());
	result = prime * result + qty;
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
	if (!(obj instanceof Delivery))
	    return false;
	Delivery other = (Delivery) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (product == null) {
	    if (other.product != null)
		return false;
	} else if (!product.equals(other.product))
	    return false;
	if (qty != other.qty)
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
	// TODO Auto-generated method stub
	return super.clone();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
	out.writeObject(product);
	out.writeInt(qty);
	out.writeObject(date);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
	product = (String) in.readObject();
	qty = in.readInt();
	date = (Date) in.readObject();
    }
}
