package Utiliteria;
import javax.sql.*;

import com.mysql.jdbc.Statement;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class ConexionBD {

	private static Connection conection ;
	private static PreparedStatement statement;
	private static ResultSet result;
	private static int numColumn;
	private static InputStream input;
	private static OutputStream out;
	private static String nombreImagen;
	private static BufferedOutputStream output;
	
	public static Connection obtenerConexion() 
	{
		try {
		Class.forName("com.mysql.jdbc.Driver");
		
			conection=DriverManager.getConnection("jdbc:mysql://localhost/bdtheron","root","102108426855");
			
		} catch (Exception e) {}
		
		return conection;
		
	}
	
	public  static void exportarImagenes(String productos,String combo)
	{
				obtenerConexion();
		File file = new File("imagenes/temp/prod");
		file.mkdirs();
			
		try{
		statement = conection.prepareStatement("SELECT  * FROM "+productos+";");
		
		result= statement.executeQuery();
		while(result.next())
		{
			nombreImagen=result.getString(1);
		
			Blob blob= result.getBlob(5);
			out= new FileOutputStream("imagenes/temp/prod/prod_"+nombreImagen+".png");
			output = new BufferedOutputStream(out);
			input = blob.getBinaryStream();
			byte [] byt=  new byte[10485760];
			int i=input.read(byt);
			while(i > 0)
			{
				output.write(byt,0,i);
				i=input.read(byt);
			}
			output.close();
			
			out.close();
			
			
		}
		file= new File("imagenes/temp/combo");
		file.mkdirs();
		
		
		statement = conection.prepareStatement("SELECT  * FROM "+combo+";");
		result = statement.executeQuery();
		
		while(result.next()){
			
			Blob blob= result.getBlob(4);
			input = blob.getBinaryStream();
			
			byte [] byt= new byte[10485760];
			int i=input.read(byt);
			out = new  FileOutputStream("imagenes/temp/combo/combo_"+result.getString(1)+".png");
		output =  new BufferedOutputStream(out);
			
			while(i>0){
				
				output.write(byt,0,i);
				i=input.read(byt);
			}
			output.close();
		out.close();	
		}
		
		
		
		
		
		}catch(Exception s){}	
	}
	
	
}