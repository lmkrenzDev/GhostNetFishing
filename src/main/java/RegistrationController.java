import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegistrationController implements Serializable {
	private String username;
	private String password;
	private String surname;
	private String name;
	private String phoneNumber;

	
	@Inject
	private UserDAO userDAO;

	public String signup() {

		FacesContext context = FacesContext.getCurrentInstance();

		User user = new User(username, password, surname, name, phoneNumber);
		userDAO.save(user);

		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Registrierung erfolgreich."));
		context.getPartialViewContext().getRenderIds().add("messages");

		return "index.xhtml";

	}

	// Getter und Setter für username und password

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}