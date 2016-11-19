package Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.Name;

import java.sql.*;


/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name;
	private String pass;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String printout = "";
		this.name = request.getParameter("username");
		this.pass = request.getParameter("password");
		
//		String clientCheckcode = request.getParameter("validateCode");
//		String serverCheckcode = (String)request.getSession().getAttribute("checkcode");
		
		if (name == "" || name == null || name.length() > 20) {
			try {
				printout = "２０字以内ユーザネームを再入力ください";
				request.setAttribute("message", printout);
				response.sendRedirect("index.jsp");
				return;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if (pass == "" || pass == null || pass.length() > 20) {
			try {
				printout = "２０字以内のパスワードを再入力ください";
				request.setAttribute("message", printout);
				response.sendRedirect("index.jsp");
				return;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//		if (!clientCheckcode.equals(serverCheckcode)) {
//			printout = "認証コードを再入力ください";
//			request.setAttribute("message", printout);
//			response.sendRedirect("index.jsp");
//			return;
//		}
//		
		// database driver 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connected to it!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Class not Found Exception");
		}
		
		// create url
		
		String url = "jdbc:mysql://localhost:3306/logindatabase";
	
		try {
			Connection conn = DriverManager.getConnection(url,"root","");
			Statement stmt = conn.createStatement();
			String sql = "select username,password from userinfo where username='"+name+"' and password= '"+pass+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				if (this.name.equals(rs.getString(1))||this.pass.equals(rs.getString(2))) {
					if (this.name != null && !this.name.equals("")) {
						
						Cookie cookie = new Cookie("username", this.name);
						cookie.setMaxAge(3600);
						cookie.setPath("/");
						response.addCookie(cookie);
						System.out.println(this.name + "'s cookie has been added.");
					}
				}
				response.sendRedirect("success.jsp");
			} else {
				response.sendRedirect("fail.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
