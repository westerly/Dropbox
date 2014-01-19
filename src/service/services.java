package service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import Configuration.Configuration;
import Core.Controler;
import beans.FileData;
import beans.Folder;
import beans.FolderData;
import beans.SpaceData;
import beans.User;
import beans.UserData;

import com.sun.jersey.spi.resource.Singleton;

@Path("service")
@Singleton
public class services {

	public services() {

	}

	@GET
	@Path("space/{nameSpace}")
	@Produces(MediaType.TEXT_HTML)
	public static String getUserSpaceHtml(
			@PathParam("nameSpace") String nameSpace) {
		return "<b>" + nameSpace + "</b>";
	}

	@GET
	@Path("test/")
	@Produces(MediaType.TEXT_HTML)
	public static String test() {
		return "<b> nameSpace </b>";
	}

	@GET
	@Path("getFileById/{fileId}")
	@Produces(MediaType.TEXT_PLAIN)
	public static List<beans.File> getFilesById(@PathParam("fileId") int fileId)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesById(fileId);
		return result;
	}

	@GET
	@Path("getFileByTag/{tag}")
	@Produces(MediaType.TEXT_PLAIN)
	public static List<beans.File> getFilesByTag(@PathParam("tag") String tag)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByTag(tag);
		return result;
	}

	@GET
	@Path("getFilesByNameAndUser/{filename}/{login}")
	@Produces(MediaType.TEXT_XML)
	public static List<beans.File> getFilesByNameAndUser(
			@PathParam("filename") String filename,
			@PathParam("login") String login) throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByNameAndUser(filename, login);
		return result;
	}

	@GET
	@Path("getFileByName/{name}")
	@Produces(MediaType.TEXT_XML)
	public static List<beans.File> getFilesByName(@PathParam("name") String name)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByName(name);
		return result;
	}

	@GET
	@Path("getUserFiles/{login}")
	@Produces(MediaType.TEXT_PLAIN)
	public static String getUserFiles(@PathParam("login") String login)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesByName(login);
		return result.toString();
	}

	@GET
	@Path("getDirectFilesOfParentNameSpace/{nameSpace}")
	@Produces(MediaType.TEXT_PLAIN)
	public static String getDirectFilesOfParentNameSpace(
			@PathParam("nameSpace") String nameSpace) throws Exception {
		SpaceData spaceD = new SpaceData();
		ArrayList result = spaceD.getDirectParentsFiles(nameSpace);
		System.out.println(result);
		return result.toString();
	}

	@GET
	@Path("getDirectFoldersOfParentNameSpace/{nameSpace}")
	@Produces(MediaType.TEXT_HTML)
	public static ArrayList<Folder> getDirectFoldersOfParentNameSpace(
			@PathParam("nameSpace") String nameSpace) throws Exception {
		SpaceData spaceD = new SpaceData();
		ArrayList<Folder> result = spaceD.getDirectParentsFolders(nameSpace);
		return result;
	}

	@GET
	@Path("getDirectFilesOfParentFolder/{idParent}")
	@Produces(MediaType.TEXT_HTML)
	public static ArrayList<File> getDirectFilesOfParentFolder(
			@PathParam("idParent") int idParent) throws Exception {
		SpaceData spaceD = new SpaceData();
		ArrayList<File> result = spaceD.getDirectParentsFiles(idParent);
		return result;
	}

	@GET
	@Path("getDirectFoldersOfParentFolder/{idParent}")
	@Produces(MediaType.TEXT_HTML)
	public static ArrayList<Folder> getDirectFoldersOfParentFolder(
			@PathParam("idParent") int idParent) throws Exception {
		SpaceData spaceD = new SpaceData();
		ArrayList<Folder> result = spaceD.getDirectParentsFolders(idParent);
		return result;
	}

	@GET
	@Path("getParentFolderId/{idParent}")
	@Produces(MediaType.TEXT_HTML)
	public static int getParentFolderId(@PathParam("idFolder") int idFolder)
			throws Exception {
		SpaceData spaceD = new SpaceData();
		int idParent = spaceD.getParentFolderId(idFolder);
		return idParent;
	}

	@DELETE
	@Path("deleteFolder/{folderName}/{parent}")
	public static void deleteFolder(
			@PathParam("folderName") String folderName,
			@PathParam("parent") String parent) throws Exception {

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

	@PUT
	@Path("createFolder/{folderName}/{parent}")
	public static boolean createFolder(
			@PathParam("folderName") String folderName,
			@PathParam("parent") String parent) throws Exception {
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
			return false;
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
			folderD.addFolder(fold);

		}

		return true;
	}

	private static boolean isNumeric(String str) {
		try {
			int d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}

	@DELETE
	@Path("deleteFile/{file}")
	public static void delete(@PathParam("file") File file) {

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

	@PUT
	@Path("createFile/{fileName}/{parent}")
	// Create file in the database
	public static void createFile(@PathParam("fileName") String fileName,
			@PathParam("parent") String parent) throws Exception {
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
//	@GET
//	@Path("getFile/{fileId}")
//	@Produces(MediaType.TEXT_HTML)
//	public String getFilesById(@PathParam("fileId") int fileId) throws Exception {
//		FileData fileD = new FileData();
//		List<beans.File> result = fileD.getFilesById(fileId);
//		return "<b>" + result.get(1).getFileName() + "</b>";
//	}
	
	@GET
	@Path("getFoldersFromParentId/{parentId: .*}")
	@Produces(MediaType.TEXT_XML)
	// Return direct parent folders of parent folder Id if it is not equal to "" and direct parent folders from name space parent
	// if  parentId = ""
	// Return null if there is a problem
	public ArrayList<Folder> getFoldersFromParentId(@Context HttpServletRequest req, @PathParam("parentId") String parentId) throws Exception {
		User userCo = (User)req.getSession().getAttribute("user");
		UserData userD = new UserData();
		ArrayList<Folder> resultFolders;
		if(userD.isValidUser(userCo)){
			if(!parentId.equals("")){
				resultFolders = Controler.getDirectFoldersOfParentFolder(Integer.parseInt(parentId));
			}else{
				resultFolders = Controler.getDirectFoldersOfParentNameSpace(userCo.getNameSpace());
			}
			return resultFolders; 
		}else{
			return null;
		}
	}
	
	@DELETE
	@Path("deleteFolder/{folderName: .*}/{parent: .*}")
	@Produces(MediaType.APPLICATION_JSON)
	public JaxBool deleteFolder(@Context HttpServletRequest req, @PathParam("folderName") String folderName, @PathParam("parent") String parent) throws Exception {
		User userCo = (User)req.getSession().getAttribute("user");
		UserData userD = new UserData();
		JaxBool res = new JaxBool();
		if(userD.isValidUser(userCo)){
			try {
				Controler.deleteFolder(folderName, parent);
				res.setRes(true);
			} catch (Exception e) {
				res.setRes(false);
				res.setError("Problem when we tried to delete the folder");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}else{
			res.setRes(false);
			res.setError("Problem when we tried to delete the folder");
		}
		
		return res;
	}

}
