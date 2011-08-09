package Utiliteria;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import PuntoVenta.frmFacturacion;

public class Combo extends JButton implements ActionListener{
	
	private String CNombre;
	private	 double descuento=0;
	private double precio;
	private  String imagen;
	private PreparedStatement state;
	private PreparedStatement state2;
	private Connection conection=ConexionBD.obtenerConexion();
	private ResultSet result;
	private ResultSet result2;
	private Modelo model=null;
	
	public Combo()
	{
		
	}
	
public	Combo(String CNombre,double descuento,double precio,String imagen,Modelo model)
	{
	super(CNombre);
	
		this.CNombre= CNombre;
		this.descuento=descuento;
		this.precio= precio;
		this.imagen= imagen;
		this.model= model;
		
		colocarImagen(80,80,imagen);
		this.addActionListener(this);
		
	}

	public String getCNombre() {
		return CNombre;
	}

	public void setCNombre(String cNombre) {
		CNombre = cNombre;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	private void colocarImagen(int w, int h, String i){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(i);
		ImageIcon icon = new ImageIcon(i);
		if (w<=icon.getIconWidth() && h<=icon.getIconHeight()){
			Image scaledImage = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);    
			icon =new ImageIcon(scaledImage);
			this.setIcon(icon);
		}else{
			this.setIcon(icon);
		} 
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	try{	
		frmFacturacion.descuento +=  descuento;
		
		state= conection.prepareStatement("SELECT * FROM detalle_combo WHERE CNombre = ?;");
		state.setString(1,CNombre);
	result = state.executeQuery();
	while(result.next())
	{
		state2 = conection.prepareStatement("SELECT * FROM productos  WHERE PNombre = ?;");
		state2.setString(1,result.getString(2));
		result2=  state2.executeQuery();
		
		while(result2.next())
		{
			
		model.insertarDatos( new Producto(result2.getString(1),result2.getInt(2),result2.getDouble(3),CNombre));
		}
		
	}
		
		}catch(Exception p){p .printStackTrace();}
	}
	
	

}
