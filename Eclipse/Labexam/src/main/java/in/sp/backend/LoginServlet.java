package in.sp.backend;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {     // Set the HTTP status code
	        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.setContentType("text/html");
			String n = req.getParameter("email1");
			String p = req.getParameter("pass1");
			PrintWriter out = resp.getWriter();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/reg","root","595877");
			
			 PreparedStatement ps = con.prepareStatement("select * from register where email=? and password=? ");
				ps.setString(1, n);
				ps.setString(2, p);ResultSet rs =ps.executeQuery();
				if(rs.next()) {
					
					 out.println("Congratulations! Login Successfully");
				RequestDispatcher rd=req.getRequestDispatcher("profile.jsp");
			     rd.forward(req, resp);
			     System.out.println("Connected");
				}
				else {
					out.println("<font color=red size=18>Login Failed!!<br>");
					out.println("<a href=login.jsp>Try AGAIN!!</a>");
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
