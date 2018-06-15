package Com.Lavi;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out=res.getWriter();
		System.out.println("doGet method Starts:");
		String NAME=req.getParameter("NAME");
		String PASSWORD=req.getParameter("PASSWORD");
		try
		{
		   if(NAME!=null ) {
		
			String dburl="jdbc:oracle:thin:@//localhost:1521/ORACLE";
			String Username="system";
			String password="system";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection(dburl,Username,password);
		System.out.println("DB connection successful to: " + dburl);
		String sql="select * from user2 where Name= ? and PASSWORD=?";
		PreparedStatement psm=con.prepareStatement(sql);
		psm.setString(1, NAME);
		psm.setString(2, PASSWORD);
		ResultSet rs=psm.executeQuery(sql);
		if(rs.next()) {
			RequestDispatcher rd=req.getRequestDispatcher("user2Details.jsp");
			rd.forward(req, res);
			out.println("login is sucess");
		}else
		{
			out.println("usernot exists:");
			RequestDispatcher rd=req.getRequestDispatcher("register.html");
		
	    
		
		}
		   }
		}
		catch(Exception e)
		{
			out.println("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
