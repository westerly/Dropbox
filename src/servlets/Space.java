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
		if(request.getAttribute("parentFolderId") == null){
			try {
				resultFiles = Controler.getDirectParentsFilesOfNameSpace(userCo.getNameSpace());
				request.setAttribute("parentFiles", resultFiles);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//resultFiles = Controler.getDirectParentsFoldersOfNameSpace(userCo.getNameSpace());
			
		}else{
			// Return list of direct parents of the folder with the id set in parentFolderId
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
