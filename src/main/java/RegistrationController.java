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

	@Inject
	private GhostNetManagement ghostNetManagement;

	public void register() {
		FacesContext context = FacesContext.getCurrentInstance();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername und Passwort dürfen nicht leer sein."));
            context.validationFailed();
            context.getPartialViewContext().getRenderIds().add("messages");
            return;
        }

        User user = new User(username, password);
        ghostNetManagement.addUser(user);

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Registrierung erfolgreich."));
        context.getPartialViewContext().getRenderIds().add("messages");
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