package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import Configuration.Configuration;

public class FileData {
	private Connection connection;
	private PreparedStatement addRecord, getUserFiles, getFilesByName,
			getFilesByNameAndUser, getFilesById, getFilesByTag;

	public FileData() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Configuration.HOST_DB,
					Configuration.USER_DB, Configuration.MDP_DB);

			addRecord = connection
					.prepareStatement("INSERT INTO files (name, name_space_parent, id_folder_parent) "
							+ "VALUES (?, ?, ?)");
			getUserFiles = connection
					.prepareStatement("SELECT FILES.id, FILES.name, FILES.id_folder_parent, FILES.name_space_parent FROM `FILES`, `SPACES`, `USERS` WHERE FILES.name_space_parent = SPACES.name and SPACES.owner = USERS.id and USERS.id = ? ");
			getFilesByName = connection
					.prepareStatement("SELECT * FROM  `files` WHERE  `name` LIKE ?");
			getFilesByNameAndUser = connection
					.prepareStatement("SELECT * FROM `files`, `users` WHERE FILES.name = ? AND USERS.login = ?");
			getFilesById = connection
					.prepareStatement("SELECT * FROM `files`, `users` WHERE FILES.id = ?");
			getFilesByTag = connection.prepareStatement("SELECT * FROM `file_tag` WHERE FILE_TAG.tagname LIKE ?");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<File> getFilesByTag(String tag) throws Exception {
		getFilesByTag.setString(0, tag);
		ResultSet result = getFilesByTag.executeQuery();
		List<File> resultFiles = new LinkedList<File>();
		while (result.next()) {
			File myFile = new File();
			myFile.setFileId(Integer.parseInt((result.getString(1))));
			myFile.setFileName(result.getString(2));
			myFile.setId_folder_parent(Integer.parseInt(result.getString(3)));
			myFile.setName_space_parent(result.getString(4));
			resultFiles.add(myFile);
		}
		return resultFiles;

	}

	public List<File> getFilesById(int id) throws Exception {
		getFilesById.setInt(0, id);
		ResultSet result = getFilesById.executeQuery();
		List<File> resultFiles = new LinkedList<File>();
		while (result.next()) {
			File myFile = new File();
			myFile.setFileId(Integer.parseInt((result.getString(1))));
			myFile.setFileName(result.getString(2));
			myFile.setId_folder_parent(Integer.parseInt(result.getString(3)));
			myFile.setName_space_parent(result.getString(4));
			resultFiles.add(myFile);
		}
		return resultFiles;

	}

	public List<File> getFilesByNameAndUser(String fileName, String login)
			throws Exception {
		getFilesByNameAndUser.setString(1, fileName);
		getFilesByNameAndUser.setString(2, login);
		ResultSet result = getFilesByNameAndUser.executeQuery();
		List<File> resultFiles = new LinkedList<File>();
		while (result.next()) {
			File myFile = new File();
			myFile.setFileId(Integer.parseInt((result.getString(1))));
			myFile.setFileName(result.getString(2));
			myFile.setId_folder_parent(Integer.parseInt(result.getString(3)));
			myFile.setName_space_parent(result.getString(4));
			resultFiles.add(myFile);
		}
		return resultFiles;

	}

	public List<File> getFilesByName(String name) throws SQLException {
		getFilesByName.setString(1, name);
		ResultSet result = getFilesByName.executeQuery();
		List<File> resultFiles = new LinkedList<File>();
		while (result.next()) {
			File myFile = new File();
			myFile.setFileId(Integer.parseInt((result.getString(1))));
			myFile.setFileName(result.getString(2));
			myFile.setId_folder_parent(Integer.parseInt(result.getString(3)));
			myFile.setName_space_parent(result.getString(4));
			resultFiles.add(myFile);
		}
		return resultFiles;

	}

	public List<File> getUserFile(String login) throws SQLException {
		getUserFiles.setString(1, login);
		ResultSet result = getUserFiles.executeQuery();
		List<File> resultFiles = new LinkedList<File>();
		while (result.next()) {
			File myFile = new File();
			myFile.setFileId(Integer.parseInt((result.getString(1))));
			myFile.setFileName(result.getString(2));
			myFile.setId_folder_parent(Integer.parseInt(result.getString(3)));
			myFile.setName_space_parent(result.getString(4));
			resultFiles.add(myFile);
		}
		return resultFiles;

	}

	public void addFile(File file) throws SQLException {

		addRecord.setString(1, file.getFileName());
		String nameSpace = file.getName_space_parent();
		Integer parentFolderId = file.getId_folder_parent();

		if (nameSpace == null) {
			addRecord.setNull(2, Types.NULL);
		} else {
			System.out.println(file.getName_space_parent());
			addRecord.setString(2, file.getName_space_parent());
		}

		if (parentFolderId == null) {
			addRecord.setNull(3, Types.NULL);
		} else {
			addRecord.setInt(3, file.getId_folder_parent());
		}

		addRecord.executeUpdate();

	}
}
