package file;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Serv
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
				PrintWriter pw=res.getWriter();
				  res.setContentType("text/html");        
				  String tb=req.getParameter("t");    
				  try
				  {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				    Connection con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","SYSTEM");
					//[1:to insert]   [2:to view] 
				    int sel=Integer.parseInt(req.getParameter("t1"));                  
					switch(sel)
					{ 
					case 1://Insert a row
						{int rno=Integer.parseInt(req.getParameter("t2"));
						String na=req.getParameter("t3");
						PreparedStatement pi= con.prepareStatement("insert into "+tb+" values(?,?)");  
						pi.setInt(1,rno);  
						pi.setString(2,na);    
						pi.executeUpdate();  
						pw.print("<h1>You are successfully Inserted a row...</h1>");  
						pw.print("<h1>Table data after insertion..</h1>");  
						pi=con.prepareStatement("select * from "+tb); 
						ResultSet rs=pi.executeQuery("Select * from "+tb);
				        pw.println("<table border=1>");
						pw.println("<tr><th>Sno</th><th>File Name</th></tr>");
				        while(rs.next())
				         {
				           pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+
													"</td></tr>");
				         }
				        pw.println("</table>");
					}
					break;
							
					case 2: //Select a row
					{ 
					  {
						  pw.print("<h1>Thank you for use our service...</h1>");
						  PreparedStatement pss= con.prepareStatement("select * from "+tb);
					    ResultSet rs=pss.executeQuery();
					    pw.println("<table border=1>");
					    pw.println("<tr><th>Sno</th><th>File Name</th></tr>");
						while(rs.next())
						{
							pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)
												+"</td></tr>");
				        }
						pw.println("</table>");
					  }
					
				  break;
							
				 
					}}}

				 catch (Exception e)
				   {  e.printStackTrace();    }

				 }
				
			


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
