package beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserData {
	private Connection connection;
	private PreparedStatement addRecord, getRecords, getUser;
	private String errorMessage = "";

	public UserData() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Dropbox", "root",
					"");

			getRecords = connection.prepareStatement("SELECT * FROM users");

			addRecord = connection.prepareStatement("INSERT INTO users (login, pwd) " + "VALUES (?, ?)");
			
			getUser = connection.prepareStatement("SELECT id, login, pwd from users WHERE login = ? ");

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
	
	public String getErrorMessage(){
		return this.errorMessage;
	}

}