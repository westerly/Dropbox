package Core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Configuration.Configuration;
import beans.FileData;
import beans.Folder;
import beans.FolderData;
import beans.SpaceData;

public class Controler {

	public static List<beans.File> getFilesById(int id) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesById(id);
		return result;
	}

	public static List<beans.File> getFilesByTag(String tag) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByTag(tag);
		return result;
	}

	public static List<beans.File> getFilesByNameAndUser(String fileName,
			String login) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByNameAndUser(fileName, login);
		return result;
	}

	public static List<beans.File> getFilesByName(String name) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByName(name);
		return result;
	}

	public static List<beans.File> getUserFiles(String login) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getUserFile(login);
		return result;
	}

	static public ArrayList<beans.File> getDirectFilesOfParentNameSpace(String nameSpace)
			throws Exception {

		SpaceData spaceD = new SpaceData();
		ArrayList<beans.File> result = spaceD.getDirectParentsFiles(nameSpace);

		return result;
	}

	static public ArrayList<Folder> getDirectFoldersOfParentNameSpace(String nameSpace)
			throws Exception {

		SpaceData spaceD = new SpaceData();
		ArrayList<Folder> result = spaceD.getDirectParentsFolders(nameSpace);

		return result;
	}

	static public ArrayList<beans.File> getDirectFilesOfParentFolder(int idParent)
			throws Exception {

		SpaceData spaceD = new SpaceData();
		ArrayList<beans.File> result = spaceD.getDirectParentsFiles(idParent);

		return result;
	}

	static public ArrayList<Folder> getDirectFoldersOfParentFolder(int idParent)
			throws Exception {

		SpaceData spaceD = new SpaceData();
		ArrayList<Folder> result = spaceD.getDirectParentsFolders(idParent);

		return result;
	}

	static public int getParentFolderId(int idFolder) throws Exception {
		SpaceData spaceD = new SpaceData();
		int idParent = spaceD.getParentFolderId(idFolder);
		return idParent;
	}

	static public void deleteFolder(String folderName, String parent)
			throws Exception {

		String path;
		// The parent is a folder
		if (isNumeric(parent)) {
			SpaceData spaceD = new SpaceData();
			path = Configuration.DATA_FOLDER
					+ spaceD.getFullPathfromFolder(Integer.parseInt(parent));
		} else {
			// The parent is a nameSpace
			path = Configuration.DATA_FOLDER + parent + "\\";
		}

		delete(new File(path + folderName));

		FolderData folderD = new FolderData();
		Folder fold = folderD.getFolderFromName(folderName);
		folderD.deleteFolder(fold);

	}

	// Create folder in DB and on the hard drive, return the object Folder if it successes
	// and null otherwise
	static public Folder createFolder(String folderName, String parent)
			throws Exception {
		String path;
		// The parent is a folder
		if (isNumeric(parent)) {
			SpaceData spaceD = new SpaceData();
			path = Configuration.DATA_FOLDER
					+ spaceD.getFullPathfromFolder(Integer.parseInt(parent));
		} else {
			// The parent is a nameSpace
			path = Configuration.DATA_FOLDER + parent + "\\";
		}

		boolean success = (new File(path + folderName)).mkdirs();
		if (!success) {
			// Directory creation failed
			return null;
		} else {
			Folder fold = new Folder();
			fold.setFolderName(folderName);

			if (isNumeric(parent)) {
				fold.setId_folder_parent(Integer.parseInt(parent));
				fold.setName_space_parent(null);
			} else {
				fold.setId_folder_parent(null);
				fold.setName_space_parent(parent);
			}

			FolderData folderD = new FolderData();
			Integer folderId = folderD.addFolder(fold);
			if(folderId != -1){
				fold.setFolderId(folderId);
				return fold;
			}else{
				return null;
			}
			
		}
	}

	static public boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}

	public static void delete(File file) {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : "
						+ file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

	// Create file in the database
	public static void createFile(String fileName, String parent)
			throws Exception {
		FileData fileD = new FileData();
		beans.File file = new beans.File();

		file.setFileName(fileName);
		if (isNumeric(parent)) {
			file.setId_folder_parent(Integer.parseInt(parent));
			file.setName_space_parent(null);
		} else {
			file.setId_folder_parent(null);
			file.setName_space_parent(parent);
		}
		System.out.println("Controler" + file.getName_space_parent());
		fileD.addFile(file);
	}
	
	// Delete file on the hard drive and on the database
	static public beans.File deleteFile(String fileName, String parent)
			throws Exception {

		String path;
		// The parent is a folder
		if (isNumeric(parent)) {
			SpaceData spaceD = new SpaceData();
			path = Configuration.DATA_FOLDER
					+ spaceD.getFullPathfromFolder(Integer.parseInt(parent));
		} else {
			// The parent is a nameSpace
			path = Configuration.DATA_FOLDER + parent + "\\";
		}

		delete(new File(path + fileName));
		
		FileData fileD = new FileData();
		beans.File file = fileD.getFileFromParentAndName(fileName, parent);
		fileD.deleteFile(file);
		
		return file;
	}

}
