package ninja.models;

@javax.persistence.Entity
public class Product extends Entity {

	private Integer code;
	private String description;
	private Integer quantity;
	private Double unitValue;

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public Double getUnitValue() {
		return unitValue;
	}

}
