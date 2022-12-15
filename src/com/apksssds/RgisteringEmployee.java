package com.apksssds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RgisteringEmployee")
public class RgisteringEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RgisteringEmployee() {
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
	    String ename=request.getParameter("ename");
	    String eid=request.getParameter("eid");
	    int sal=Integer.parseInt(request.getParameter("sal"));
	    String design=request.getParameter("desig");
	    String sid=request.getParameter("sid");
	    String pass=request.getParameter("password");
	    int berth=Integer.parseInt(request.getParameter("berth"));
	    try
	    {
	    Statement stmt=conn.createStatement();
	    stmt.executeUpdate("insert into employee values('"+eid+"',"+sal+",'"+ename+"',current_timestamp,'"+design+"',"+berth+",'admin','"+pass+"')");
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    	
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
