package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Core.Controler;
import beans.File;
import beans.SearchMode;

/**
 * Servlet implementation class SearchEngine
 */
public class SearchEngine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchEngine() {
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
		List<File> resultFiles = null;
		if (request.getParameter("searchMode") == SearchMode.ALL.toString()) {
			try {
				resultFiles = Controler.getUserFiles(new String(request
						.getParameter("userLogin")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("searchMode") == SearchMode.NAME.toString()) {
			try {
				resultFiles = Controler.getFilesByName(new String(request
						.getParameter("fileName")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("searchMode") == SearchMode.TAG.toString()) {
			try {
				resultFiles = Controler.getFilesByTag(new String(request
						.getParameter("fileTag")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("searchMode") == SearchMode.ID.toString()) {
			try {
				resultFiles = Controler.getFilesById(Integer.parseInt(request
						.getParameter("fileId")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("The request has failed");
		}
		request.setAttribute("resultFiles", resultFiles);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
