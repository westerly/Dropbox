package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import Configuration.Configuration;

public class FileData {
	private Connection connection;
	private PreparedStatement addRecord;
	
	public FileData() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					Configuration.HOST_DB, Configuration.USER_DB,
					Configuration.MDP_DB);
			
			addRecord = connection.prepareStatement("INSERT INTO files (name, name_space_parent, id_folder_parent) " + "VALUES (?, ?, ?)");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	
	public void addFile(File file) throws SQLException{
		
		
    	addRecord.setString(1, file.getFileName());
    	String nameSpace = file.getName_space_parent();
    	Integer parentFolderId = file.getId_folder_parent();

    	if(nameSpace == null){
    		addRecord.setNull(2, Types.NULL);
    	}else{
    		System.out.println(file.getName_space_parent());
    		addRecord.setString(2, file.getName_space_parent());
    	}
  		
    	if(parentFolderId == null){
    		addRecord.setNull(3, Types.NULL);
    	}else{
    		addRecord.setInt(3, file.getId_folder_parent());
    	}
    	
  		addRecord.executeUpdate();
  		
	}
}
