package com.apksssds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
    try
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "general", "general");
    }

    catch(Exception e)
    {
    	System.out.println("err");
    }
    finally
    {
    	System.out.println("Connected yayyyy");
    }
	String username=request.getParameter("user");
	String pass=request.getParameter("pass");
	System.out.println(username+" "+pass);
	try {

		Statement stmt  = conn.createStatement();
		ResultSet rs=stmt.executeQuery("select password from employee where emp_id='"+username+"'");
		rs.next();
		if(rs.getString("password").equals(pass))
		{
			RequestDispatcher rd = request.getRequestDispatcher("success.html");
			rd.include(request, response);
		}
		else
		{
			RequestDispatcher rd2 = request.getRequestDispatcher("incorr.html");
			rd2.include(request, response);
		}
		
	}
	catch(Exception w)
	{
		System.out.println(w);
		RequestDispatcher rd3 = request.getRequestDispatcher("incorr.html");
		rd3.include(request, response);
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
