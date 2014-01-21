package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

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
	@Path("getFileById/{fileId}")
	@Produces(MediaType.TEXT_XML)
	public static List<beans.File> getFilesById(@PathParam("fileId") int fileId)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getFilesById(fileId);
		return result;
	}

	@GET
	@Path("getFileByTag/{tag}")
	@Produces(MediaType.TEXT_XML)
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
	@Produces(MediaType.TEXT_XML)
	public static String getUserFiles(@PathParam("login") String login)
			throws Exception {
		FileData fileD = new FileData();
		List<beans.File> result = fileD.getUserFile(login);
		return result.toString();
	}

	@GET
	@Path("getParentFolderId/{idParent}")
	@Produces(MediaType.TEXT_XML)
	public static int getParentFolderId(@PathParam("idFolder") int idFolder)
			throws Exception {
		SpaceData spaceD = new SpaceData();
		int idParent = spaceD.getParentFolderId(idFolder);
		return idParent;
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

	@PUT
	@Path("createFolder/{folderName}/{parent: .*}")
	@Produces(MediaType.APPLICATION_JSON)
	// Create folder at the location indicated by the param parent. If parent is
	// equal to "" we create the folder in directly after the space name
	// of the user in the tree, if parent is an id we create the folder after
	// the folder with this id
	// If folder created successfully we return the Folder object otherwise we
	// return null
	public Folder createFolder(@Context HttpServletRequest req,
			@PathParam("folderName") String folderName,
			@PathParam("parent") String parent) throws Exception {
		User userCo = (User) req.getSession().getAttribute("user");
		UserData userD = new UserData();
		Folder fold = new Folder();
		if (userD.isValidUser(userCo)) {

			if (parent.equals("")) {
				parent = userCo.getNameSpace();
			} else {
				if (!isNumeric(parent)) {
					return null;
				}
			}
			fold = Controler.createFolder(folderName, parent);

		} else {
			return null;
		}

		return fold;
	}

	// @GET
	// @Path("getFile/{fileId}")
	// @Produces(MediaType.TEXT_HTML)
	// public String getFilesById(@PathParam("fileId") int fileId) throws
	// Exception {
	// FileData fileD = new FileData();
	// List<beans.File> result = fileD.getFilesById(fileId);
	// return "<b>" + result.get(1).getFileName() + "</b>";
	// }

	@GET
	@Path("getFoldersFromParentId/{parentId: .*}")
	@Produces(MediaType.TEXT_XML)
	// Return direct parent folders of parent folder Id if it is not equal to ""
	// and direct parent folders from name space parent
	// if parentId = ""
	// Return null if there is a problem
	public ArrayList<Folder> getFoldersFromParentId(
			@Context HttpServletRequest req,
			@PathParam("parentId") String parentId) throws Exception {
		User userCo = (User) req.getSession().getAttribute("user");
		UserData userD = new UserData();
		ArrayList<Folder> resultFolders;
		if (userD.isValidUser(userCo)) {
			if (!parentId.equals("")) {
				resultFolders = Controler
						.getDirectFoldersOfParentFolder(Integer
								.parseInt(parentId));
			} else {
				resultFolders = Controler
						.getDirectFoldersOfParentNameSpace(userCo
								.getNameSpace());
			}
			return resultFolders;
		} else {
			return null;
		}
	}

	@GET
	@Path("getFilesFromParentId/{parentId: .*}")
	@Produces(MediaType.TEXT_XML)
	// Return direct parent files of parent file Id if it is not equal to "" and
	// direct parent files from name space parent
	// if parentId = ""
	// Return null if there is a problem
	public ArrayList<beans.File> getFilesFromParentId(
			@Context HttpServletRequest req,
			@PathParam("parentId") String parentId) throws Exception {
		User userCo = (User) req.getSession().getAttribute("user");
		UserData userD = new UserData();
		ArrayList<beans.File> resultFiles;
		if (userD.isValidUser(userCo)) {
			if (!parentId.equals("")) {
				resultFiles = Controler.getDirectFilesOfParentFolder(Integer
						.parseInt(parentId));
			} else {
				resultFiles = Controler.getDirectFilesOfParentNameSpace(userCo
						.getNameSpace());
			}
			return resultFiles;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("deleteFolder/{folderName}/{parent}")
	@Produces(MediaType.APPLICATION_JSON)
	public JaxBool deleteFolder(@Context HttpServletRequest req,
			@PathParam("folderName") String folderName,
			@PathParam("parent") String parent) throws Exception {
		User userCo = (User) req.getSession().getAttribute("user");
		UserData userD = new UserData();
		JaxBool res = new JaxBool();
		if (userD.isValidUser(userCo)) {
			try {
				Controler.deleteFolder(folderName, parent);
				res.setRes(true);
			} catch (Exception e) {
				res.setRes(false);
				res.setError("Problem when we tried to delete the folder");
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		} else {
			res.setRes(false);
			res.setError("Problem when we tried to delete the folder");
		}

		return res;
	}

	// Delete a folder son of the parent parameter which can be a namespace or a
	// folder
	@DELETE
	@Path("deleteFile/{fileName}/{parent}")
	@Produces(MediaType.APPLICATION_JSON)
	public JaxBool deleteFile(@Context HttpServletRequest req,
			@PathParam("fileName") String fileName,
			@PathParam("parent") String parent) throws Exception {
		User userCo = (User) req.getSession().getAttribute("user");
		UserData userD = new UserData();
		JaxBool res = new JaxBool();
		if (userD.isValidUser(userCo)) {
			try {
				Controler.deleteFile(fileName, parent);
				res.setRes(true);
			} catch (Exception e) {
				res.setRes(false);
				res.setError("Problem when we tried to delete the file.");
			}
		} else {
			res.setRes(false);
			res.setError("Problem when we tried to delete the file.");
		}

		return res;
	}

	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/download/{folderName}/{parentId: .*}")
	public Response downloadFile(@Context HttpServletRequest request,
			@PathParam("folderName") String folderName,
			@PathParam("parentId") String parent) throws Exception {
		User userCo = (User) request.getSession().getAttribute("user");
		UserData userD = new UserData();

		if (!userD.isValidUser(userCo) || folderName.equals("")) {
			return Response.ok("Invalid user or folder name parameter missing")
					.build();
		}

		String filePath = "";
		// the parent of the file is the namespace of the user
		if (parent.equals("")) {
			filePath = Configuration.DATA_FOLDER + userCo.getNameSpace() + "\\"
					+ folderName;
		} else {
			SpaceData spaceD = new SpaceData();
			filePath = Configuration.DATA_FOLDER
					+ spaceD.getFullPathfromFolder(Integer.parseInt(parent))
					+ "\\" + folderName;
		}

		System.out.println(filePath);

		if (filePath != null && !"".equals(filePath)) {
			File file = new File(filePath);
			StreamingOutput stream = null;
			try {
				final InputStream in = new FileInputStream(file);
				stream = new StreamingOutput() {
					public void write(OutputStream out)
							throws WebApplicationException {
						try {
							int read = 0;
							byte[] bytes = new byte[1024];

							while ((read = in.read(bytes)) != -1) {
								out.write(bytes, 0, read);
							}
						} catch (Exception e) {
							throw new WebApplicationException(e);
						}
					}
				};
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			return Response
					.ok(stream)
					.header("content-disposition",
							"attachment; filename = " + file.getName()).build();
		}
		return Response.ok("file path null").build();
	}

}
