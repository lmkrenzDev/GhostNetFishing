import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class RegistrationController {
	private String username;
	private String password;

	@Inject
	private GhostNetManagement ghostNetManagement;

	public void register() {
		User newUser = new User(username, password);
		ghostNetManagement.addUser(newUser);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Erfolgreich registriert"));
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
}