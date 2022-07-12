package Code;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

public class Library {
	private static List<Book> books=new ArrayList<Book>();
	private static List<User>users=new ArrayList<User>();
	private static List<Admin>admins=new ArrayList<Admin>();
	private static LocalDate date= LocalDate.now(); 
	
	public static void syncData() {
	 PersonDAO_Imp personDAO=new PersonDAO_Imp();
	 BookDAO_Imp bookDAO=new BookDAO_Imp();
	 books=bookDAO.list();
	 users=personDAO.listUsers();
	 admins=personDAO.listAdmins();
	 
	}
	public static Book getBookByISBN(int ISBN) {
		for(Book bk : books) {
			if(bk.getISBN() == ISBN) {
				return bk;
			}
		}
		return null;
	}
	public static void checkFines(User user) {
		List<Date> deadlines = user.getDeadlines();
		for(int i=0; i<deadlines.size(); i++) {
			if(deadlines.get(i).compareTo(java.sql.Date.valueOf(date))<=0) {
				user.setHasFine(true);
			}
		}
	}
	
	public static List<Book> getBooks() {
		return books;
	}

	public static void setBooks(List<Book> bookList) {
		books = bookList;
	}
	

	public static LocalDate getDate() {
		return date;
	}

	public static void setDate(LocalDate date) {
		Library.date = date;
	}
	public static void nextDay() {
		date=date.plusDays(1);
		checkFines();
	}
	public static void checkFines() {
		for(User user: users) {
			List<Date> deadlines = user.getDeadlines();
			for(int i=0; i<deadlines.size(); i++) {
				if(deadlines.get(i).compareTo(java.sql.Date.valueOf(date))<=0) {
					user.setHasFine(true);
				}
			}
		}
	}
	public static User getUserByID(int ID) {
		for(User user: users) {
			if(user.getId() == ID) {
				return user;
			}
		}
		return null;
	}
	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		Library.users = users;
	}

	public static List<Admin> getAdmins() {
		return admins;
	}

	public static void setAdmins(List<Admin> admins) {
		Library.admins = admins;
	}

	
}
