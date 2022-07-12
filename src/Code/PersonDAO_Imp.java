package Code;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO_Imp implements PersonDAO {

	@Override
	public void add(Person person) { //d�zelt
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "INSERT INTO librarysql.person(id,name,surname,password,isAdmin,hasFine) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, person.getId());
			ps.setString(2, person.getName());
			ps.setString(3, person.getSurname());
			ps.setString(4, person.getPassword());
			if(person instanceof Admin) {
				ps.setInt(5, 1);
			}				
			else {
				ps.setInt(5, 0);
			}
			ps.setInt(6, 0);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Person person) { // To update an existing product
		try {
			Connection con = LibraryDB.getConnection();
			String sql = person instanceof Admin ? "UPDATE librarysql.person SET id=?,name=?,surname=?,password=?,hasFine=? WHERE id=?" : "UPDATE librarysql.person SET id=?,name=?,surname=?,password=?,hasFine=?,BookReceived=?, BookReadBefore=?, deadline=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, person.getId());
			ps.setString(2, person.getName());
			ps.setString(3, person.getSurname());
			ps.setString(4, person.getPassword());
			if(!(person instanceof Admin)) {
				String bookreceived_st = "";
				String bookreadbefore = "";
				String deadline = "";
				User user=new User();
				user=(User) person;
				for(Book bk: user.getBookReceived())
					bookreceived_st +=bk.getISBN()+",";
				for(Book bk: user.getBookReadBefore())
					bookreadbefore += bk.getISBN()+",";
				for(Date dt: user.getDeadlines())
					deadline += dt.toString()+",";
				ps.setInt(5, user.isHasFine()?1:0);
				ps.setString(6, bookreceived_st.replaceAll(",$", ""));
				ps.setString(7, bookreadbefore.replaceAll(",$", ""));
				ps.setString(8, deadline.replaceAll(",$", ""));
				ps.setInt(9, person.getId());
			}
			else {
				ps.setInt(5, 0);
				ps.setInt(6, person.getId());
			}
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void delete(int id) {
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "DELETE FROM librarysql.person WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Person get(int id) {
		Person person = null;
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.person WHERE id =?";
			PreparedStatement bs = con.prepareStatement(sql);
			bs.setInt(1, id);
			ResultSet rs = bs.executeQuery();
			if (rs.next()) {
				if (rs.getInt("isAdmin")==1) {
					person=new Admin();
				}
				else {
					person=new User();
					((User) person).setHasFine(rs.getBoolean("hasFine"));
					((User) person).syncData(id);

				}
				person.setId(id);
				person.setName(rs.getString("name"));
				person.setSurname(rs.getString("surname"));
				person.setPassword(rs.getString("password"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Admin> listAdmins() {
		List<Admin> list = new ArrayList<Admin>();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.person WHERE isAdmin=1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setSurname(rs.getString("surname"));
				admin.setPassword(rs.getString("password"));
				list.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<User> listUsers() {
		List<User> list = new ArrayList<User>();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT * FROM librarysql.person WHERE isAdmin=0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPassword(rs.getString("password"));
				user.setHasFine(rs.getBoolean("hasFine"));
				user.syncData(user.getId());
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Book> listUserBooks(int id, boolean getReceived) { //ID'YE GÖRE SEARCH FONSK EKLE,160TA İD'YE GÖRE ARAMADAN GELENİ EKLE
		List<Book> books=new ArrayList<Book>();
		
		try {
			Connection con = LibraryDB.getConnection();
			String sql = getReceived? "SELECT BookReceived FROM librarysql.person WHERE id =? AND BookReceived IS NOT NULL" : "SELECT BookReadBefore FROM librarysql.person WHERE id =? AND BookReadBefore IS NOT NULL";
			PreparedStatement bs = con.prepareStatement(sql);
			bs.setInt(1, id);
			ResultSet rs = bs.executeQuery();
			if (rs.next()) {
				String bookString = rs.getString(getReceived?"BookReceived":"BookReadBefore");
				String[] bookArray = bookString.split(",");
				for (String ISBN: bookArray) {
					if(!ISBN.equals(""))
					books.add(Library.getBookByISBN(Integer.parseInt(ISBN)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return person;
		return books;
	}

	@SuppressWarnings("exports")
	@Override
	public List<Date> getDeadlines(int id) {
		List<Date> deadlines=new ArrayList<Date>();
		try {
			Connection con = LibraryDB.getConnection();
			String sql = "SELECT deadline FROM librarysql.person WHERE id =? AND deadline IS NOT NULL";
			PreparedStatement bs = con.prepareStatement(sql);
			bs.setInt(1, id);
			ResultSet rs = bs.executeQuery();
			if (rs.next()) {
				String dateString = rs.getString("deadline");
				String[] dateArray = dateString.split(",");
				for (String str: dateArray) {
					deadlines.add(Date.valueOf(str));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deadlines;
	}

}
