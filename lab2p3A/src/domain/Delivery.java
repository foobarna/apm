package domain;

import java.util.Date;

public class Delivery implements Cloneable {
	private Date date = new Date();
	private String product = "";
	private int qty = 0;

	public Delivery() {
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

	@Override
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
}
