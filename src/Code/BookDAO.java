package Code;

import java.util.List;
public interface BookDAO { //Data Acces Object interface for books
	 	public void add(Book book);
	    public void update(Book book);
	    public void delete(int ISBN);
	    public Book get(int ISBN);
	    public List<Book> list();
}