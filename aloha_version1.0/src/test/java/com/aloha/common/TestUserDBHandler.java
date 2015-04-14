package com.aloha.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.dao_manager.dal.UserDal;
import com.aloha.common.entities.User;

public class TestUserDBHandler {
	static final Logger logger = Logger.getLogger(TestUserDBHandler.class
			.toString());
	UserDal ud;

	public TestUserDBHandler() {
		ud = new UserDal();
	}

	public static void main(String[] args) {
		Connection con = DatabaseHandlerSingleton.getDBConnection();

		try {
			TestUserDBHandler test = new TestUserDBHandler();
			 //test.insertUser();
			test.selectUser(6);
			// test.updateUser();
			// test.selectUser(3);
			// test.deleteUser(3);
			//test.selectAllUsers();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	private void selectAllUsers() throws SQLException {

		ArrayList<User> ulist = ud.selectUserAll();

		for (User user : ulist) {
			System.out.println(user);
		}

	}

	private void selectUser(int id) throws SQLException {
		User u = new User();
		u = ud.selectUserByPrimaryKey(id);
		System.out.println(u.toString());

	}

	private void insertUser() throws SQLException {
		int res;
		Date dob = Date.valueOf("1987-10-02");
		User u = new User();

		u.setFirstName("Milind");
		u.setLastName("Gokhale");
		u.setContactNumber("8123697654");
		u.setEmail("milindhg@gmail.com");
		u.setPassword("root");
		u.setDateOfBirth(dob);
		u.setIsVerified(0);
		u.setIsLocked(0);
		u.setLastActive(dob);
		res = ud.insertUser(u);
		if (res == 1)
			System.out.println("row inserted successfully");
	}

	private void updateUser(int id) throws SQLException {
		int res;
		Date dob = Date.valueOf("1999-12-31");
		User u = new User();

		u = ud.selectUserByPrimaryKey(id);
		u.setFirstName("Milind");
		u.setLastName("Gokhale");
		u.setContactNumber("8123697654");
		u.setEmail("mgokhale@indiana.edu");
		u.setPassword("root");
		u.setDateOfBirth(dob);
		u.setIsVerified(0);
		u.setIsLocked(0);
		u.setLastActive(dob);
		res = ud.updateUser(u);
		if (res == 1)
			System.out.println("row updated successfully");

	}

	private void deleteUser(int id) throws SQLException {
		int res = ud.deleteUser(id);
		if (res == 1)
			System.out.println("row deleted successfully");
	}

}