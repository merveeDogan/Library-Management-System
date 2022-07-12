package application;

import Code.LogInScreen;

public class RegisterLogin {
	public static boolean RegisterUser(int id, String password, String name, String surname) {
		if(LogInScreen.checkRegisterForUser(id, password)) {
			LogInScreen.addPerson(id, password, name, surname, false);
			return true;
		}
		return false;
	}
	public static boolean RegisterAdmin(int id, String password, String name, String surname, String token) {
		if(LogInScreen.checkRegisterForAdmin(id, password,token)) {
			LogInScreen.addPerson(id, password, name, surname, true);
			return true;
		}
		return false;
	}
	public static boolean LogInUser(int id, String password) {
		if(LogInScreen.checkLogin(id, password,false)) {
			return true;
		}
		return false;
	}
	public static boolean LogInAdmin(int id, String password) {
		if(LogInScreen.checkLogin(id, password,true)) {
			return true;
		}
		return false;
	}
}
