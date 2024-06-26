import jakarta.inject.Named;


@Named
public class SavingPerson extends Person {

	private String phoneNumber;
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	public SavingPerson() {
		
	}
	
	public SavingPerson(String surname, String name, String phoneNumber) {
		super(surname, name);
		this.phoneNumber = phoneNumber;
	}
}
