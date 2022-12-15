package com.apksssds;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InOrOut
 */
@WebServlet("/InOrOut")
public class InOrOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InOrOut() {
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
	    String imo=request.getParameter("imo");
	    String io=request.getParameter("io");
	    if("i".equals(io))
	    {
	    	try {

				Statement stmt  = conn.createStatement();
				String qqq="update ship set toa=current_timestamp where imo="+imo;
				System.out.println("error");
                String containupd="update container set depository_date=current_timestamp where ship_imo="+imo+" and CONTAINER_STATE='import'";
                System.out.println(imo+" error");
                stmt.executeUpdate(containupd);
                System.out.println("error");
                System.out.println(qqq);
				stmt.executeUpdate(qqq);
				stmt.executeUpdate("update ship set tod=null where imo="+imo);
				System.out.println("b1");
				ResultSet rs=stmt.executeQuery("select imo,berth_id from realberth");
				boolean flag=true;
				while(rs.next())
				{
					String temp=rs.getString("imo");
					System.out.println(temp+"null");
					if(temp==null)
					{
						System.out.println("af if");
						String qq="update ship set berthid='"+rs.getString("berth_id")+"' where imo="+imo;
						String q="update realberth set imo="+imo+" where berth_id='"+rs.getString("berth_id")+"'";
						System.out.println("be qq");
						stmt.executeUpdate(qq);
						System.out.println("be q");
						stmt.executeUpdate(q);
						System.out.println("af q");
						flag=false;
						break;
					}
				}
				if(flag)
				{
					System.out.println("fullllllllllllllllll");
					ResultSet rs1=stmt.executeQuery("select imo,priority from queue");
					int max=0;
					while(rs1.next())
					{
						int prio=rs1.getInt("priority");
						if(max<prio)
						{
							max=prio;
						}
					}
					stmt.executeUpdate("insert into queue values("+imo+","+(max+1)+")");
				}
			}
			catch(Exception w)
			{
				System.out.println(w);
			}
	    }
	    else
	    {
	    	try {
				Statement stmt  = conn.createStatement();
	    		ResultSet rs1=stmt.executeQuery("select imo,priority from queue");
				int min=1110;
				int yimo=0;
				while(rs1.next())
				{
					int prio=rs1.getInt("priority");
					if(min>prio)
					{
						if(prio!=0)
						{
							min=prio;
							yimo=rs1.getInt("imo");
						}
					}
				}
				if(min!=1110)
				{
					String containupd="update container set WITHDRAWAL_DATE=current_timestamp where ship_imo="+imo+" and CONTAINER_STATE='export'";
		               // String containupd="update container set depository_date=current_timestamp where ship_imo="+imo;
						System.out.println(containupd+" outgoing error");
						stmt.executeUpdate(containupd);
					System.out.println("outgoing error");
					String q="update realberth set imo="+yimo+" where imo="+imo;
					System.out.println(q);
					stmt.executeUpdate(q);
					System.out.println("outgoing error");
					System.out.println("a");
					ResultSet rs2=stmt.executeQuery("select berth_id from realberth where imo="+yimo);
					System.out.println("outgoing error");
					rs2.next();
					
					String qqq="update ship set tod=current_timestamp where imo="+imo;
					
					int xyz=rs2.getInt("berth_id");
					System.out.println("a");
					System.out.println(qqq);
					System.out.println("a");
					stmt.executeUpdate(qqq);
					System.out.println("outgoing error");
					System.out.println("a");
					stmt.executeUpdate("update ship set berthid=null where imo="+imo);
					System.out.println("a");
					stmt.executeUpdate("update ship set berthid="+xyz+" where imo="+yimo);
					System.out.println("a");
					stmt.executeUpdate("delete from queue where imo="+yimo);
				}
				else
				{
					String q="update realberth set imo=null where imo="+imo;
					System.out.println(q);
					stmt.executeUpdate(q);
					String qqq="update ship set tod=current_timestamp where imo="+imo;

					System.out.println(qqq);
					stmt.executeUpdate(qqq);
					stmt.executeUpdate("update ship set berthid=null where imo="+imo);
				}
				
				
				
				
				
				
			}
			catch(Exception w)
			{
				System.out.println(w);
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
