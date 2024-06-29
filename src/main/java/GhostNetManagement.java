import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("ghostNetManagement")
@ApplicationScoped
public class GhostNetManagement {

	private final List<Ghostnet> ghostnets = new ArrayList<Ghostnet>();
		
	private List<User> users = new ArrayList<>();

	public GhostNetManagement() {
		Ghostnet ghostNet1 = new Ghostnet(1, 53.387088, 4.693777, 100, "Gemeldet",
				(new GregorianCalendar(2022, 6, 23).getTime()), (new GregorianCalendar(2023, 1, 15).getTime()));
		ghostnets.add(ghostNet1);

		Ghostnet ghostNet2 = new Ghostnet(2, 69.075299, 12.621785, 50, "Bergung bevorstehend",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet2);
		
		Ghostnet ghostNet3 = new Ghostnet(3, 69.075299, 12.621785, 50, "Geborgen",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet3);
		
		Ghostnet ghostNet4 = new Ghostnet(4, 69.075299, 12.621785, 50, "Verschollen",
				(new GregorianCalendar(2012, 11, 23).getTime()), (new GregorianCalendar(2014, 5, 13).getTime()));
		ghostnets.add(ghostNet4);
	}

	public List<Ghostnet> getGhostnets() {
		return ghostnets;
	}
	
	public void addGhostnet(Ghostnet ghostnet) {
        ghostnets.add(ghostnet);
    }
	
	public int getcurrentSize() {
		return ghostnets.size();
	}
	
	public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
    
    public int getUserCount() {
        return users.size();
    }
	
    
}
