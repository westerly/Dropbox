package beans;


import java.sql.*;
import java.util.*;

import Configuration.Configuration;

public class UserData {
	private Connection connection;
	private PreparedStatement addRecord, getRecords, getUser, getUserByLoginAndPwd, getNameSpace;

	public UserData() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					Configuration.HOST_DB, Configuration.USER_DB,
					Configuration.MDP_DB);

			getRecords = connection.prepareStatement("SELECT * FROM users");

			addRecord = connection.prepareStatement("INSERT INTO users (login, pwd) " + "VALUES (?, ?)");
			
			getUser = connection.prepareStatement("SELECT id, login, pwd from users WHERE login = ? ");
			
			getNameSpace = connection.prepareStatement("SELECT name from spaces WHERE owner = ? ");
			
			getUserByLoginAndPwd = connection.prepareStatement("SELECT id, login, pwd from users WHERE login = ? AND pwd = ?");
			

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public List<User> getUsersList() throws SQLException {
		List<User> userList = new ArrayList<User>();

		// obtain list of titles
		ResultSet results = getRecords.executeQuery();

		// get row data
		while (results.next()) {
			User myUser = new User();
			
			myUser.setId(Integer.parseInt(results.getString(1)));
			myUser.setLogin(results.getString(2));
			myUser.setPwd(results.getString(3));
			
			userList.add(myUser);
		}

		return userList;
	}

	public void addUser(User myUser) throws SQLException{
	    	addRecord.setString(1, myUser.getLogin());
	  		addRecord.setString(2, myUser.getPwd());
	  		addRecord.executeUpdate();
	}
	
	public User getUser(String login) throws SQLException{
		getUser.setString(1, login);
		ResultSet result = getUser.executeQuery();
		
		User myUser = new User();
				
		if(result.next()){
			myUser.setId(Integer.parseInt(result.getString(1)));
			myUser.setLogin(result.getString(2));
			myUser.setPwd(result.getString(3));
		}
		
		getNameSpace.setInt(1, myUser.getId());
		result = getNameSpace.executeQuery();
		if(result.next()){
			myUser.setNameSpace(result.getString(1));
		}
		
		return myUser;
		
	}

	protected void finalize() {
		try {
			getRecords.close();
			addRecord.close();
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	public boolean isValidUser(User user) throws SQLException{
		getUserByLoginAndPwd.setString(1, user.getLogin());
		getUserByLoginAndPwd.setString(2, user.getPwd());
		ResultSet result = getUserByLoginAndPwd.executeQuery();
		if(result.next()){
			return true;
		}else{
			return false;
		}
	}
	

}