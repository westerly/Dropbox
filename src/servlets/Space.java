package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import Core.Controler;

/**
 * Servlet implementation class Space
 */
public class Space extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Space() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList resultFiles;
		ArrayList resultFolders;
		User userCo = (User)request.getSession().getAttribute("user");
		
		// Return list of direct parents (files and folders) of the space
		if(request.getParameter("parentFolderId") == null){
			try {
				resultFiles = Controler.getDirectFilesOfParentNameSpace(userCo.getNameSpace());
				resultFolders = Controler.getDirectFoldersOfParentNameSpace(userCo.getNameSpace());
				request.setAttribute("folders", resultFolders);
				request.setAttribute("files", resultFiles);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//resultFiles = Controler.getDirectParentsFoldersOfNameSpace(userCo.getNameSpace());
			
		}else{
			// Return list of direct parents of the folder with the id set in parentFolderId
			try {
				resultFiles = Controler.getDirectFilesOfParentFolder(Integer.parseInt(request.getParameter("parentFolderId")));
				resultFolders = Controler.getDirectFoldersOfParentFolder(Integer.parseInt(request.getParameter("parentFolderId")));
				request.setAttribute("folders", resultFolders);
				request.setAttribute("files", resultFiles);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList resultFiles;
		ArrayList resultFolders;
		User userCo = (User)request.getSession().getAttribute("user");
		
		// Return list of direct parents (files and folders) of the space
		if(request.getParameter("parentFolderId") == null){
			try {
				resultFiles = Controler.getDirectFilesOfParentNameSpace(userCo.getNameSpace());
				resultFolders = Controler.getDirectFoldersOfParentNameSpace(userCo.getNameSpace());
				request.setAttribute("folders", resultFolders);
				request.setAttribute("files", resultFiles);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//restultFiles = Controler.getDirectParentsFoldersOfNameSpace(userCo.getNameSpace());
			
		}else{
			// Return list of direct parents of the folder with the id set in parentFolderId
			try {
				resultFiles = Controler.getDirectFilesOfParentFolder(Integer.parseInt(request.getParameter("parentFolderId")));
				resultFolders = Controler.getDirectFoldersOfParentFolder(Integer.parseInt(request.getParameter("parentFolderId")));
				request.setAttribute("folders", resultFolders);
				request.setAttribute("files", resultFiles);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
