package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import Configuration.Configuration;

public class SpaceData {
	private Connection connection;
	private PreparedStatement getRecordBySpaceName, addRecord,
			getParentsFilesFromNameSpace, getParentsFoldersFromNameSpace,
			getParentsFilesFromFolder, getParentFoldersFromFile, getFolder;

	public SpaceData() throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Configuration.HOST_DB,
					Configuration.USER_DB, Configuration.MDP_DB);

			getRecordBySpaceName = connection
					.prepareStatement("SELECT * FROM spaces WHERE name = ?");

			addRecord = connection
					.prepareStatement("INSERT INTO spaces (name, owner) "
							+ "VALUES (?, ?)");

			getParentsFilesFromNameSpace = connection
					.prepareStatement("SELECT * FROM `files` WHERE name_space_parent = ? ");
			getParentsFoldersFromNameSpace = connection
					.prepareStatement("SELECT * FROM `folders` WHERE name_space_parent = ? ");

			getParentsFilesFromFolder = connection
					.prepareStatement("SELECT * FROM `files` WHERE id_folder_parent = ? ");
			getParentFoldersFromFile = connection
					.prepareStatement("SELECT * FROM `folders` WHERE id_folder_parent = ? ");

			getFolder = connection
					.prepareStatement("SELECT * FROM `folders` WHERE id = ? ");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}


	public Space getSpace(String nameSpace) throws SQLException {
		getRecordBySpaceName.setString(1, nameSpace);
		ResultSet result = getRecordBySpaceName.executeQuery();

		Space mySpace = new Space();

		if (result.next()) {
			mySpace.setSpaceName(result.getString(1));
			mySpace.setOwnerId(Integer.parseInt(result.getString(2)));
		}

		return mySpace;

	}

	
	public void addSpace(Space mySpace) throws SQLException {
		addRecord.setString(1, mySpace.getSpaceName());
		addRecord.setInt(2, mySpace.getOwnerId());
		addRecord.executeUpdate();
	}

	public ArrayList<File> getDirectParentsFiles(String nameSpace)
			throws SQLException {
		getParentsFilesFromNameSpace.setString(1, nameSpace);
		ResultSet result = getParentsFilesFromNameSpace.executeQuery();

		ArrayList<File> resList = new ArrayList<File>(5);

		File file;

		while (result.next()) {
			file = new File();
			file.setId(result.getInt(1));
			file.setFileName(result.getString(2));
			file.setId_folder_parent(result.getInt(3));
			file.setName_space_parent(result.getString(4));
			resList.add(file);
		}
		return resList;
	}

	public ArrayList<Folder> getDirectParentsFolders(String nameSpace)
			throws SQLException {
		getParentsFoldersFromNameSpace.setString(1, nameSpace);
		ResultSet result = getParentsFoldersFromNameSpace.executeQuery();

		ArrayList<Folder> resList = new ArrayList<Folder>(5);

		Folder folder;
		while (result.next()) {
			folder = new Folder();
			folder.setId(result.getInt(1));
			folder.setFolderName(result.getString(2));
			folder.setName_space_parent(result.getString(3));
			folder.setId_folder_parent(result.getInt(4));
			resList.add(folder);
		}

		return resList;
	}

	public ArrayList<File> getDirectParentsFiles(int idParent) throws SQLException {
		getParentsFilesFromFolder.setInt(1, idParent);
		ResultSet result = getParentsFilesFromFolder.executeQuery();
		System.out.println(getParentsFilesFromFolder.toString());
		ArrayList<File> resList = new ArrayList<File>(5);

		File file;
		while (result.next()) {
			file = new File();
			file.setId(result.getInt(1));
			file.setFileName(result.getString(2));
			file.setId_folder_parent(result.getInt(3));
			file.setName_space_parent(result.getString(4));
			resList.add(file);
		}
		return resList;
	}

	public ArrayList<Folder> getDirectParentsFolders(int idParent) throws SQLException {
		getParentFoldersFromFile.setInt(1, idParent);
		ResultSet result = getParentFoldersFromFile.executeQuery();

		ArrayList<Folder> resList = new ArrayList<Folder>(5);

		Folder folder;
		while (result.next()) {
			folder = new Folder();
			folder.setId(result.getInt(1));
			folder.setFolderName(result.getString(2));
			folder.setName_space_parent(result.getString(3));
			folder.setId_folder_parent(result.getInt(4));
			resList.add(folder);
		}
		return resList;
	}

	// Return the id of the parent folder or nothing if the folder's parent is a
	// space
	public int getParentFolderId(int idFolder) throws SQLException {
		getFolder.setInt(1, idFolder);
		ResultSet result = getFolder.executeQuery();

		int idParent = 0;

		if (result.next()) {
			idParent = result.getInt(4);
		}

		return idParent;

	}

	public String getFullPathfromFolder(int idFolder) throws SQLException {
		String path = "";
		getFolder.setInt(1, idFolder);
		ResultSet result = getFolder.executeQuery();

		ArrayList<String> foldersPath = new ArrayList<String>(5);

		if (result.next()) {
			foldersPath.add(result.getString(2));
			String nameSpace = result.getString(3);
			int idFolderParent = result.getInt(4);
			while (nameSpace == null) {
				getFolder.setInt(1, idFolderParent);
				ResultSet result2 = getFolder.executeQuery();
				result2.next();
				foldersPath.add(result2.getString(2));
				nameSpace = result2.getString(3);
				idFolderParent = result2.getInt(4);
			}

			path = path + nameSpace + "\\";
			for (int i = foldersPath.size() - 1; i >= 0; i--) {
				path = path + foldersPath.get(i) + "\\";
			}
		}

		return path;

	}

}
