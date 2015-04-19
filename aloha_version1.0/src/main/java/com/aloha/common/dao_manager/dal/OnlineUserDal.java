/**
 * 
 */
package com.aloha.common.dao_manager.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aloha.common.dao_manager.DatabaseHandlerSingleton;
import com.aloha.common.entities.Post;
import com.aloha.common.entities.user.User;
import com.mysql.jdbc.Statement;

/**
 * @author Milind
 *
 */
public class OnlineUserDal {
	Connection con = null;
	// write the queries for Post table
	private String SELECT;
	private String INSERT;
	private String DELETE;
	private String SELECT_ONLINE_USERS;
	private String SELECT_ONLINE_FRIENDS;

	public OnlineUserDal() {
		SELECT = "SELECT online_users.user_id FROM online_users";
		INSERT = "INSERT INTO online_users (user_id) VALUES (?);";
		DELETE = "DELETE FROM online_users WHERE user_id = ?;";
		SELECT_ONLINE_USERS = "SELECT user.user_id, user.fname, user.lname, user.contact_number, user.email, user.password, user.bdate, user.isVerified, user.isLocked, user.lastactive FROM user WHERE user.user_id in (SELECT online_users.user_id FROM online_users);";
		SELECT_ONLINE_FRIENDS = "SELECT user.user_id, user.fname, user.lname, user.contact_number, user.email, user.password, user.bdate, user.isVerified, user.isLocked, user.lastactive from user where user_id in ("
				+ "SELECT user_id from online_users"
				+ "where user_id in (SELECT friendship.user_id1 FROM friendship where friendship.user_id2 = ?"
				+ "union"
				+ "SELECT friendship.user_id2 FROM friendship where friendship.user_id1 = ?));";
		con = DatabaseHandlerSingleton.getDBConnection();
	}

	public int isUserOnline(int id) throws SQLException {
		String isUserOnlineQuery = SELECT + " where online_users.user_id=?;";
		PreparedStatement ps = null;
		ResultSet rSet = null;
		int userId = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(isUserOnlineQuery);
			ps.setInt(1, id);
			rSet = ps.executeQuery();
			userId = rSet.getInt("user_id");
			return userId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rSet != null)
				rSet.close();
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int deletePost(int id) throws SQLException {
		String deleteUserQuery = DELETE;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(deleteUserQuery);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public int insertPost(int id) throws SQLException {
		String insertUserQuery = INSERT;
		PreparedStatement ps = null;
		int result = -1;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(insertUserQuery,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, id);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			result = rs.getInt("user_id");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public ArrayList<User> selectOnlineUsers() throws SQLException {
		String selectOnlineUsersQuery = SELECT;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(selectOnlineUsersQuery);
			rSet = ps.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			if (rSet != null) {
				while (rSet.next()) {
					User u = new User();
					u.setUserId(rSet.getInt("user_id"));
					u.setFirstName(rSet.getString("fname"));
					u.setLastName(rSet.getString("lname"));
					u.setContactNumber(rSet.getString("contact_number"));
					u.setEmail(rSet.getString("email"));
					u.setPassword(rSet.getString("password"));
					u.setDateOfBirth(rSet.getTimestamp("bdate"));
					u.setIsVerified(rSet.getInt("isVerified"));
					u.setIsLocked(rSet.getInt("isLocked"));
					u.setLastActive(rSet.getDate("lastActive"));
					users.add(u);
				}
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rSet != null)
				rSet.close();
			if (ps != null)
				ps.close();
			con.close();
		}
	}

	public ArrayList<User> selectOnlineFriends() throws SQLException {
		String selectOnlineFriendsQuery = SELECT;
		PreparedStatement ps = null;
		ResultSet rSet = null;
		try {
			con = DatabaseHandlerSingleton.getDBConnection();
			ps = con.prepareStatement(selectOnlineFriendsQuery);
			rSet = ps.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			if (rSet != null) {
				while (rSet.next()) {
					User u = new User();
					u.setUserId(rSet.getInt("user_id"));
					u.setFirstName(rSet.getString("fname"));
					u.setLastName(rSet.getString("lname"));
					u.setContactNumber(rSet.getString("contact_number"));
					u.setEmail(rSet.getString("email"));
					u.setPassword(rSet.getString("password"));
					u.setDateOfBirth(rSet.getTimestamp("bdate"));
					u.setIsVerified(rSet.getInt("isVerified"));
					u.setIsLocked(rSet.getInt("isLocked"));
					u.setLastActive(rSet.getDate("lastActive"));
					users.add(u);
				}
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rSet != null)
				rSet.close();
			if (ps != null)
				ps.close();
			con.close();
		}

	}

}
