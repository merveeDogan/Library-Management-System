package Code;

import java.sql.Date;
import java.util.List;

public interface PersonDAO {
	public void add(Person person);

	public void update(Person person);

	public void delete(int id);

	public Person get(int id);

	public List<User> listUsers();
	
	public List<Admin> listAdmins();
	
	public List<Book> listUserBooks(int id, boolean getReceived);
	
	@SuppressWarnings("exports")
	public List<Date> getDeadlines(int id);
	
}
