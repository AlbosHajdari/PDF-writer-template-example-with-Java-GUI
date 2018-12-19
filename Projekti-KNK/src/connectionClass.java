
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class connectionClass {
	
	Connection conn = null;
	
	public static Connection connectDb()
	{
		try 
		{
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbKNK?characterEncoding=UTF-8&useSSL=false","root","root");
			
			return conn;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Problemi: "+ex.getMessage());
			return null;
		}
	}
}
