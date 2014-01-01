package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import Configuration.Configuration;

public class FolderData {
	private Connection connection;
	private PreparedStatement addRecord, getFolderFromName, deleteRecord;
	
	public FolderData() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					Configuration.HOST_DB, Configuration.USER_DB,
					Configuration.MDP_DB);
			
			getFolderFromName = connection.prepareStatement("SELECT * FROM folders where name = ?");

			addRecord = connection.prepareStatement("INSERT INTO folders (name, name_space_parent, id_folder_parent) " + "VALUES (?, ?, ?)");
			
			deleteRecord = connection.prepareStatement("DELETE FROM folders WHERE id = ?");


		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void addFolder(Folder folder) throws SQLException{
	
    	addRecord.setString(1, folder.getFolderName());
    	String nameSpace = folder.getName_space_parent();
    	Integer parentFolderId = folder.getId_folder_parent();
    	if(nameSpace == null){
    		addRecord.setNull(2, Types.NULL);
    	}else{
    		addRecord.setString(2, folder.getName_space_parent());
    	}
  		
    	if(parentFolderId == null){
    		addRecord.setNull(3, Types.NULL);
    	}else{
    		addRecord.setInt(3, folder.getId_folder_parent());
    	}
  		
  		addRecord.executeUpdate();
	}
	
	public Folder getFolderFromName(String folderName) throws SQLException{
		Folder fold = new Folder();
		getFolderFromName.setString(1, folderName);
		ResultSet result = getFolderFromName.executeQuery();
		
		if(result.next()){
			fold.setId(result.getInt(1));
			fold.setFolderName(result.getString(2));
			fold.setName_space_parent(result.getString(3));
			fold.setId_folder_parent(result.getInt(4));
		}
		return fold;
	}
	
	// Delete the folder and all folders and files inside it from the DB (thanks to ON DELETE CASCADE OPTION)
	public void deleteFolder(Folder folder) throws SQLException{
			deleteRecord.setInt(1, folder.getId() );
			deleteRecord.executeUpdate();
	}

}
