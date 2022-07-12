package Code;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Admin extends Person {//HAS FINELAR İLE KULLANCILILARIN CEZASINI GÖRÜNTÜLEMESİ GEREK
	Calendar cal = Calendar.getInstance();

	public Admin(int id, String password, String name, String surname) {
		super(id, password, name, surname);
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public void addBook(Book book) {
			BookDAO_Imp bookdao = new BookDAO_Imp();	
			Book bk =  Library.getBookByISBN(book.getISBN());	
			if(bk==null){				
			 book.setQuantity(1);
			 Library.getBooks().add(book);
			 bookdao.add(book);
			 }
			else{
			 bk.setQuantity(bk.getQuantity()+1);
			 bookdao.update(bk);
			 }			
	}
	
	public List<User> listFine() {
		List<User> users = Library.getUsers();
		List<User> usersFine = new ArrayList<User>();
		for(User user: users) {
			if(user.isHasFine()) {
				usersFine.add(user);
			}
		}
		return usersFine;
	}
	
	public boolean deleteBookByISBN(int ISBN) {
		BookDAO_Imp bookdao = new BookDAO_Imp();	
		Book bk =  Library.getBookByISBN(ISBN);	
		if(bk==null){				
		 return false;
		 }
		else{
			if(bk.getQuantity() == 1) {
				Library.getBooks().remove(bk);
				bookdao.delete(bk.getISBN());
			}
			else {
				bk.setQuantity(bk.getQuantity()-1);
				bookdao.update(bk);
			}
		 }
		return true;
	}

	public boolean giveBook(int ISBN, int ID) {
		BookDAO_Imp bookdao = new BookDAO_Imp();	
		Book book = Library.getBookByISBN(ISBN);
		Book bk =  Library.getBookByISBN(ISBN);	
		PersonDAO_Imp persondao = new PersonDAO_Imp();
		User user = Library.getUserByID(ID);
		try {
			if((user.getBookReceived().contains(book)||user.isHasFine()))
				return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
			if (book.getQuantity() == 0) {
				System.out.println("This book is not available");
				return false;
			}				
			else {
				if(book.getQuantity()==1) {
					book.setQuantity(0);
					bookdao.update(bk);
				}		
				else {
					deleteBookByISBN(ISBN);			
				}
				user.getBookReceived().add(book);
				user.getDeadlines().add(user.getBookReceived().indexOf(book),java.sql.Date.valueOf(Library.getDate().plusDays(15)));
				persondao.update(user);			
			}
			return true;
		
	}

	public boolean bookReturn(int ISBN, int ID) {
		
		try {
			BookDAO_Imp bookdao = new BookDAO_Imp();
			Book book = Library.getBookByISBN(ISBN);
			PersonDAO_Imp persondao = new PersonDAO_Imp();
			User user = Library.getUserByID(ID);
			book = user.getBookByISBN(ISBN);
			if(user.getBookReceived().contains(book)) {
				book.setQuantity(book.getQuantity() + 1);
				user.getBookReadBefore().add(book);
				user.getDeadlines().remove(user.getBookReceived().indexOf(book));
				user.getBookReceived().remove(book);
				user.setHasFine(false);
				Library.checkFines();
				bookdao.update(book);
				persondao.update(user);
				return true;
			}
			else return false;
		} catch (Exception e) {
			return false;
		}
	
			
	}

	public List<Book> searchBook(String string) {
		List<Book> searchingBooks = new ArrayList<Book>();
		for (int i = 0; i < Library.getBooks().size(); i++) {
			if (Library.getBooks().get(i).getAuthor().trim().contains(string)
					|| Library.getBooks().get(i).getName().trim().contains(string)) {
				searchingBooks.add(Library.getBooks().get(i));
			}
		}
		BookDAO_Imp book = new BookDAO_Imp();
		searchingBooks = book.Search(string, true);
		return searchingBooks;
	}

	public void listBook() {
		System.out.println(
				"Book name: " + "-" + "\t\t" + "Author: " + "-" + "\t\t" + "ISBN: " + "-" + "\t\t" + "Quantity: ");
		for (int i = 0; i < Library.getBooks().size(); i++) {
			System.out.println(Library.getBooks().get(i).getName() + "-" + "\t\t"
					+ Library.getBooks().get(i).getAuthor() + "-" + "\t\t" + Library.getBooks().get(i).getISBN() + "-"
					+ "\t\t" + Library.getBooks().get(i).getQuantity());
		}
	}
}
