import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
//SessionScoped damit loggedInUser weiter verwendet werden kann
@SessionScoped
public class LoginController implements Serializable {
	private String username;
	private String password;
	private User currentUser;
	private User loggedInUser;

	@Inject
	private GhostNetManagement ghostNetManagement;

	public String login() {
		// keine weitere Überprüfung notwenig, da Validierung durch Post Process
		loggedInUser = currentUser;
		return "index?faces-redirect=true";
	}

	public void postValidateUser(ComponentSystemEvent ev) {
		UIInput temp = (UIInput) ev.getComponent();
		this.username = (String) temp.getValue();
	}

	public void validateUsername(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		currentUser = ghostNetManagement.validateUserName(username);
		if (currentUser == null) {
			throw new ValidatorException(new FacesMessage("User wurde nicht gefunden"));
		}

	}

	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String password = (String) value;
		boolean passwordValid = ghostNetManagement.validateUsernameAndPassword(currentUser, username, password);
		if (!passwordValid) {
			throw new ValidatorException(new FacesMessage("Passwort falsch!"));
		}
	}

	public String logout() {
		loggedInUser = null;
		currentUser = null;
		return "index?faces-redirect=true";
	}

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

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
}
