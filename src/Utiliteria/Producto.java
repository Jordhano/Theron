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
import javax.swing.JOptionPane;

public class Producto extends JButton implements ActionListener{
	private PreparedStatement state;
	private Connection coneccion = ConexionBD.obtenerConexion();
	private ResultSet result;
	private String pNombre; 
	private int cantidad;
	private double precio;
	private int no_Orden;
	private String imagen;
	private Modelo tempLista;
	private String combo="";
	private int numCantidad=0;
	
	public Producto(String pNombre,int cantidad, double precio, String combo){
		this.pNombre = pNombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.combo =combo;
		
	}
	
	public Producto(String pNombre, int cantidad, double precio, int noOrden,String imagen, Modelo tempLista,String combo) {
		super(pNombre);
		this.pNombre = pNombre;
		this.cantidad = cantidad;
		this.precio = precio;
		no_Orden = noOrden;
		this.tempLista = tempLista;
		this.imagen = imagen;
		this.setFocusable(false);
		this.combo= combo;
		this.numCantidad = cantidad;
		
		colocarImagen(80,80, imagen);
		this.addActionListener(this);
	}
	
	
	
public Producto(String pNombre, int cantidad, double precio) {
		
		this.pNombre = pNombre;
		this.cantidad = cantidad;
		this.precio = precio;
		
	}

	public String obtenerpNombre() {
		return pNombre;
	}
	
	public int obtenerCantidad() {
		return cantidad;
	}
	public String obtenerCombo()
	{
	return combo;	
	}
	
	public double obtenerPrecio() {
		return precio;
	}

	public int obtenerNo_Orden() {
		return no_Orden;
	}

	public void obtenerNo_Orden(int noOrden) {
		no_Orden = noOrden;
	}

	public String obtenerImagen() {
		return imagen;
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
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			try{
				int cant;
			state = coneccion.prepareStatement("SELECT * FROM productos WHERE PNombre = ?;");
			state.setString(1,pNombre);
			result = state.executeQuery();
			result.next();
			cant = result.getInt(2);
			
			
			numCantidad -= 1;
			
			if((!(result.getInt(2)==0)))
			{
				if(!(numCantidad < 0))
				{
		tempLista.insertarDatos(new Producto(pNombre, cantidad, precio, no_Orden, imagen, tempLista, combo));
				}	
			else {
				int j=JOptionPane.showConfirmDialog(this,"No es posible anadir el elemento ,causa exitencia negativa,desea dar Entrada");
				if(j==0)
				{
					cant = cant + Integer.parseInt(JOptionPane.showInputDialog("Introduzca la Cantidad de Entrada"));
					state = coneccion.prepareStatement("UPDATE productos SET Cantidad = ? WHERE PNombre = ? ;");
					state.setInt(1,cant);
					state.setString(2,pNombre);
					state.executeUpdate();
					JOptionPane.showMessageDialog(this,"Entrada Exitosa");
					numCantidad=cantidad;
				}
			}
			}
			}catch(Exception r){
				r.printStackTrace();}
			}
		}
	

	
	
}
