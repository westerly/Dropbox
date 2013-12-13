package servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.UserData;

/**
 * Servlet implementation class registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String errorMessage = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/userRegister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				String login = request.getParameter("login");
				String pwd = request.getParameter("pwd");
				if(login == null ||pwd == null){
					this.getServletContext().getRequestDispatcher("/userRegister.jsp").forward(request, response);
				}else{
					  String pattern = "^[a-z0-9_-]{3,16}$";

				      // Create a Pattern object
				      Pattern r = Pattern.compile(pattern);

				      // Now create matcher object.
				      Matcher m = r.matcher(login);
				      if (!m.find( )) {
				         this.errorMessage += "Login incorrect, enter a login with only characters, numbers and _ or - (Minimum 3 characters, maximum 16 characters)";
				      }
				      
				      pattern = "^[a-z0-9_-]{6,18}$";
				      
				      m = r.matcher(pwd);
				      if (!m.find( )) {
				         this.errorMessage += "<br/> Passsword incorrect, enter a login with only characters, numbers and _ or - (Minimum 6 characters, maximum 18 characters)";
				      }
				      
				      if(this.errorMessage.isEmpty()){
				    	 
				    	User myUser = new User();
				    	myUser.setLogin(login);
				    	
				    	// Encode password in md5
						try {
							MessageDigest md = MessageDigest.getInstance("MD5");
						    md.update(pwd.getBytes());

						    byte byteData[] = md.digest();

						    StringBuffer sb = new StringBuffer();
						    for (int i = 0; i < byteData.length; i++)
						        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
						    
					    	myUser.setPwd(sb.toString());
					    	UserData userD;
							try {
								userD = new UserData();
								try {
									userD.addUser(myUser);
									// Create a session for the new registered user
									HttpSession session = request.getSession();
									session.setAttribute("user", myUser);
									this.getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      }else{
				    	  request.setAttribute("errorMessage", this.errorMessage);
				    	  this.getServletContext().getRequestDispatcher("/userRegister.jsp").forward(request, response);
				      }
				}
	}

}
