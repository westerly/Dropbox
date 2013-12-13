package servlets;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import beans.UserData;

/**
 * Servlet implementation class login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String errorMessage = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/userLogin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		if(login == null || pwd == null){
			this.getServletContext().getRequestDispatcher("/userLogin.jsp").forward(request, response);
		}else{
			User myUser = new User();
			try {
				UserData userD = new UserData();
				myUser = userD.getUser(login);
				
				if(myUser != null && myUser.getLogin() != null){
					
					MessageDigest md = MessageDigest.getInstance("MD5");
				    md.update(pwd.getBytes());
				    byte byteData[] = md.digest();
				    StringBuffer sb = new StringBuffer();
				    for (int i = 0; i < byteData.length; i++)
				        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				    
					if(sb.toString().equals(myUser.getPwd())){
						HttpSession session = request.getSession();
						session.setAttribute("user", myUser);
						
						this.getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
					}else{
						this.errorMessage = "Login or password incorrect.";
					}
					
				}else{
					// User login didn't find in the db
					this.errorMessage = "Login or password incorrect.";
				}
				
				if(!this.errorMessage.isEmpty()){
					request.setAttribute("errorMessage", this.errorMessage);
					this.getServletContext().getRequestDispatcher("/userLogin.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
