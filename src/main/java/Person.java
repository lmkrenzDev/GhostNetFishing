import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class Person {
	
	private long id;
	
	private String surname = "Li";
	private String name = "Kre";
	private String phoneNumber = "015236987";
	
	
	public Person(String surname, String name) {
		this.surname = surname;
		this.name = name;
	}
	
	public Person() {
		
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String nameString) {
		this.name = nameString;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	
	

}
