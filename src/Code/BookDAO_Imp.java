package Code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO_Imp implements BookDAO { // Data Acces Object class for books
	@Override
	public void add(Book book) { // To add new product
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "INSERT INTO librarysql.book(name,author,pageNumber,ISBN,quantity) VALUES (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPageNumber());
			ps.setInt(4, book.getISBN());
			ps.setInt(5, book.getQuantity());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Book book) { // To update an existing product
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "UPDATE librarysql.book SET name=?,author=?,pageNumber=?,ISBN=?,quantity=? WHERE ISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setInt(3, book.getPageNumber());
			ps.setInt(4, book.getISBN());
			ps.setInt(5, book.getQuantity());
			ps.setInt(6, book.getISBN());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int ISBN) {
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "DELETE FROM librarysql.book WHERE ISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ISBN);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Book get(int ISBN) {
		Book bk = new Book();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.book WHERE ISBN =?";
			PreparedStatement bs = con.prepareStatement(sql);
			bs.setInt(1, ISBN);
			ResultSet rs = bs.executeQuery();
			if (rs.next()) {
				bk.setISBN(ISBN);
				bk.setName(rs.getString("name"));
				bk.setAuthor(rs.getString("author"));
				bk.setPageNumber(rs.getInt("pageNumber"));
				bk.setQuantity(rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bk;
	}
	public List<Book> Search(String string,boolean isAdmin) {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.book WHERE INSTR(name,?) > 0 OR INSTR(author,?) > 0";
			if (!isAdmin) {
				sql+="AND WHERE NOT quantity=0";
			}
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,string);
			ps.setString(2,string);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book bt = new Book();
				bt.setISBN(rs.getInt("ISBN"));
				bt.setName(rs.getString("name"));
				bt.setAuthor(rs.getString("author"));
				bt.setPageNumber(rs.getInt("pageNumber"));
				bt.setQuantity(rs.getInt("quantity"));
				list.add(bt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> list() {
		List<Book> list = new ArrayList<Book>();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.book";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Book bt = new Book();
				bt.setISBN(rs.getInt("ISBN"));
				bt.setName(rs.getString("name"));
				bt.setAuthor(rs.getString("author"));
				bt.setPageNumber(rs.getInt("pageNumber"));
				bt.setQuantity(rs.getInt("quantity"));
				list.add(bt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}