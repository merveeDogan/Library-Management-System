package Code;

public class Book {
	private String name;
	private int ISBN;
	private String Author;
	private int pageNumber;
	private int quantity;

	public Book(String name, int iSBN, String author, int pageNumber) {
		this.name = name;
		ISBN = iSBN;
		Author = author;
		this.pageNumber = pageNumber;
		this.quantity = 1;
	}
	public Book() {
		
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
