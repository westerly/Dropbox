package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Configuration.Configuration;

public class SpaceData {
	private Connection connection;
	private PreparedStatement getRecordBySpaceName, addRecord, getParentsFiles, getParentsFolders;

	public SpaceData() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					Configuration.HOST_DB, Configuration.USER_DB,
					Configuration.MDP_DB);

			getRecordBySpaceName = connection.prepareStatement("SELECT * FROM spaces WHERE name = ?");
			

			addRecord = connection.prepareStatement("INSERT INTO spaces (name, owner) " + "VALUES (?, ?)");
			
			getParentsFiles = connection.prepareStatement("SELECT * FROM `files` WHERE name_space_parent = ? ");
			getParentsFolders = connection.prepareStatement("SELECT * FROM `folders` WHERE name_space_parent = ? ");
					


		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public Space getSpace(String nameSpace) throws SQLException{
		getRecordBySpaceName.setString(1, nameSpace);
		ResultSet result = getRecordBySpaceName.executeQuery();
		
		Space mySpace = new Space();
				
		if(result.next()){
			mySpace.setSpaceName(result.getString(1));
			mySpace.setOwnerId(Integer.parseInt(result.getString(2)));
		}
		
		return mySpace;
		
	}
	
	public void addSpace(Space mySpace) throws SQLException{
    	addRecord.setString(1, mySpace.getSpaceName());
  		addRecord.setInt(2, mySpace.getOwnerId());
  		addRecord.executeUpdate();
	}
	
	public ArrayList getDirectParentsFiles(String nameSpace) throws SQLException{
		getParentsFiles.setString(1, nameSpace);
		ResultSet result = getParentsFiles.executeQuery();
		
		ArrayList resList = new ArrayList(5);
		
		File file = new File();
		
		while(result.next()){
			file.setId(result.getInt(1));
			file.setFileName(result.getString(2));
			file.setId_folder_parent(result.getInt(3));
			file.setName_space_parent(result.getString(4));
			resList.add(file);
		}
		return resList;
	}
	
	public ArrayList getDirectParentsFolders(String nameSpace) throws SQLException{
		getParentsFolders.setString(1, nameSpace);
		ResultSet result = getParentsFolders.executeQuery();
		
		ArrayList resList = new ArrayList(5);
		
		Folder folder = new Folder();
		
		while(result.next()){
			folder.setId(result.getInt(1));
			folder.setFolderName(result.getString(2));
			folder.setId_folder_parent(result.getInt(3));
			folder.setName_space_parent(result.getString(4));
			resList.add(folder);
		}
		return resList;
	}
	
	
	
	
}
