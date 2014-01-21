package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Core.Controler;

/**
 * Servlet implementation class Folder
 */
public class Folder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Folder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		 * String folderName = (String) request.getParameter("folderName");
		 * String parent = (String) request.getParameter("parent"); String
		 * action = (String) request.getParameter("action");
		 * 
		 * if(action != null && parent != null && folderName!=null){
		 * if(action.equals("create")){ try {
		 * if(!Controler.createFolder(folderName, parent)){
		 * request.setAttribute("errorMessage",
		 * "A problem occured during the folder creation, please be sure a folder with the same name doesn't exist and try again."
		 * ); } if(Controler.isNumeric(parent)){
		 * this.getServletContext().getRequestDispatcher
		 * ("/welcome.jsp?parentFolderId=" + parent ).forward(request,
		 * response); }else{
		 * this.getServletContext().getRequestDispatcher("/welcome.jsp"
		 * ).forward(request, response); } } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }else{
		 * if(action.equals("delete")){
		 * 
		 * try { Controler.deleteFolder(folderName, parent); } catch (Exception
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * if(Controler.isNumeric(parent)){
		 * this.getServletContext().getRequestDispatcher
		 * ("/welcome.jsp?parentFolderId=" + parent ).forward(request,
		 * response); }else{
		 * this.getServletContext().getRequestDispatcher("/welcome.jsp"
		 * ).forward(request, response); } } } }
		 */
	}

}
