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
 * Servlet implementation class EnterShipDetails
 */
@WebServlet("/EnterShipDetails")
public class EnterShipDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterShipDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("It wont came da");
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
	    String imo=request.getParameter("imo");
		String name=request.getParameter("name");
		String flag=request.getParameter("flag");
		String type=request.getParameter("type");
		String prev=request.getParameter("prev");
		String next=request.getParameter("next");
		String dead=request.getParameter("dead");
		String size=request.getParameter("size");
		try {
			int tug=Integer.parseInt(dead)/4000;
			if(tug<2)
			{
				tug=2;
			}
			Statement stmt  = conn.createStatement();
			System.out.println("insert into ship values("+dead+",'"+prev+"','"+next+"',null,null,'"+type+"','"+flag+"',"+imo+",'"+name+"','"+size+"',"+tug+",null)");
			stmt.executeUpdate("insert into ship values("+dead+",'"+prev+"','"+next+"',null,null,'"+type+"','"+flag+"',"+imo+",'"+name+"','"+size+"',"+tug+",null)");
		}
		catch(Exception w)
		{
			System.out.println(w);
		}
		RequestDispatcher rd = request.getRequestDispatcher("logintrans.html");
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
