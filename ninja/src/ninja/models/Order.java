package ninja.models;

import java.util.Date;

@javax.persistence.Entity
public class Order extends Entity {

	private Integer number;
	private Date emissionDate;
	private Integer client;
	private Double totalValue;
	private String products;

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	public Date getEmissionDate() {
		return emissionDate;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public Integer getClient() {
		return client;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}
}
