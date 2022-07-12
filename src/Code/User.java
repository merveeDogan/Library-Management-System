package Code;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User extends Person { 
	private List<Book> BookReceived = new ArrayList<Book>();
	private List<Book> BookReadBefore = new ArrayList<Book>();
	private List<Date> deadlines = new ArrayList<Date>();
	private boolean hasFine; 
	private int id;
	private String name;

	
	
	public User(int id, String password, String name, String surname) {
		super(id, password, name, surname);
		this.id = id;//For table view
		this.name = name;
		hasFine = false;
		syncData(id);
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public Book getBookByISBN(int ISBN) {
		for(Book bk : BookReceived) {
			if(bk.getISBN() == ISBN) {
				return bk;
			}
		}
		return null;
	}
	public void syncData(int id) {
		PersonDAO_Imp personDAO=new PersonDAO_Imp();
		BookReceived = personDAO.listUserBooks(id, true);
		BookReadBefore = personDAO.listUserBooks(id, false);
		deadlines = personDAO.getDeadlines(id);
		Library.checkFines(this);
	}
	public List<Book> getFine() {
		List<Book> books=new ArrayList<Book>();
		for(Date date: deadlines) {
			if(date.compareTo(java.sql.Date.valueOf(Library.getDate()))<=0) {
				books.add(BookReceived.get(deadlines.indexOf(date)));
			}
		}
		return books;
	}

	public static List<Book> searchBook(String string) {
		List<Book> searchingBooks = new ArrayList<Book>();
		for (int i = 0; i < Library.getBooks().size(); i++) {
			if ((Library.getBooks().get(i).getAuthor().trim().contains(string)
					&& Library.getBooks().get(i).getQuantity() > 0)
					|| (Library.getBooks().get(i).getName().trim().contains(string)
							&& Library.getBooks().get(i).getQuantity() > 0)) {
				searchingBooks.add(Library.getBooks().get(i));
			}
		}
		return searchingBooks;
	}

	public void listBook() {
		System.out.println("Book name: " + "-" + "\t\t" + "Author: " + "-" + "\t\t" + "ISBN: ");
		for (int i = 0; i < Library.getBooks().size(); i++) {
			if (Library.getBooks().get(i).getQuantity() != 0) {
				System.out.println(Library.getBooks().get(i).getName() + "-" + "\t\t"
						+ Library.getBooks().get(i).getAuthor() + "-" + "\t\t" + Library.getBooks().get(i).getISBN());
			}
		}
	}

	public List<Book> getBookReceived() {
		return BookReceived;
	}

	public void setBookReceived(List<Book> bookReceived) {
		BookReceived = bookReceived;
	}

	public List<Book> getBookReadBefore() {
		return BookReadBefore;
	}

	public void setBookReadBefore(List<Book> bookReadBefore) {
		BookReadBefore = bookReadBefore;
	}

	public boolean isHasFine() {
		return hasFine;
	}

	public void setHasFine(boolean hasFine) {
		this.hasFine = hasFine;
	}

	@SuppressWarnings("exports")
	public List<Date> getDeadlines() {
		return deadlines;
	}

	public void setDeadlines(@SuppressWarnings("exports") List<Date> deadlines) {
		this.deadlines = deadlines;
	}
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


 
	
}
