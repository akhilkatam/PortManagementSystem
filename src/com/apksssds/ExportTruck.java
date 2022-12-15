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
 * Servlet implementation class ExportTruck
 */
@WebServlet("/ExportTruck")
public class ExportTruck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportTruck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	    String cid=request.getParameter("cid");
	    try
	    {
	    Statement stmt=conn.createStatement();
	    stmt.executeUpdate("update container set depository_date=current_timestamp where container_id="+cid+" and container_state='export' ");
	    ResultSet rs=stmt.executeQuery("select storage_id from container where container_id="+cid);
	    rs.next();
	    RequestDispatcher rd = request.getRequestDispatcher("/cardo/index"+rs.getInt("storage_id")+".html");
		rd.include(request, response);
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
