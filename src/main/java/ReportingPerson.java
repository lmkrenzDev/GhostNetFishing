import jakarta.inject.Named;


@Named
public class ReportingPerson extends Person {

	public ReportingPerson() {

	}

	public ReportingPerson(String surname, String name) {
		super(surname, name);
	}

}
