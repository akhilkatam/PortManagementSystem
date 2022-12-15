package com.apksssds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContainerOwnerRegister
 */
@WebServlet("/ContainerOwnerRegister")
public class ContainerOwnerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContainerOwnerRegister() {
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
	    String name=request.getParameter("name");
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		System.out.println(name+" "+user+" "+pass);
		try {

			Statement stmt  = conn.createStatement();
			System.out.println("insert into container_login values('"+user+"','"+pass+"','"+name+"')");
			stmt.executeUpdate("insert into container_login values('"+user+"','"+pass+"','"+name+"')");
		}
		catch(Exception w)
		{
			System.out.println(w);
		}
		RequestDispatcher rd = request.getRequestDispatcher("logincontainerowner.html");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
