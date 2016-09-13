package ninja.models;

import javax.persistence.Table;

@javax.persistence.Entity(name="Client")
@Table(name="Client")
public class Client extends Entity {

	private String cpfCnpj;
	private String socialName;
	private String phone;
	private String email;

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public String getSocialName() {
		return socialName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
