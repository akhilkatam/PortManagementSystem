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

/**
 * Servlet implementation class EnterContainerDetails
 */
@WebServlet("/EnterContainerDetails")
public class EnterContainerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterContainerDetails() {
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
	    int cid=Integer.parseInt(request.getParameter("cid"));
	    String cowner=request.getParameter("owner");
	    int weight=Integer.parseInt(request.getParameter("weight"));
	    int size=Integer.parseInt(request.getParameter("size"));
	    String type=request.getParameter("type");
	    String content=request.getParameter("content");
	    String state=request.getParameter("eiv");
	    int imo=Integer.parseInt(request.getParameter("imo"));
	    int typer;
	    switch(type)
	    {
	    case "Dry Container": typer=1;
	    break;
	    case "Flat Rack": typer=2;
	    break;
	    case "Ventilated": typer=3;
	    break;
	    case "Cryoscopic Container": typer=4;
	    break;
	    case "Tank Container": typer=5;
	    break;
	    case "Open Roof": typer=6;
	    break;
	    default:
	    	typer=0;
	    }
	    try
	    {
	    Statement stmt=conn.createStatement();
	    stmt.executeUpdate("insert into container values('"+cid+"','"+cowner+"','"+state+"',"+weight+","+imo+","+size+",'"+content+"','"+type+"',"+typer+",null,null )");
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    	try
	    	{
	    	Statement stmt=conn.createStatement();
		    stmt.executeUpdate("delete from container where cid="+cid);
		    stmt.executeUpdate("insert into container values('"+cid+"','"+cowner+"','"+state+"',"+weight+","+imo+","+size+",'"+content+"','"+type+"',"+typer+",null,null)");
	    	}
	    	catch(Exception w)
	    	{
	    		System.out.println(e);  
	    	}
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
