package PuntoVenta;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Utiliteria.ConexionBD;
import Utiliteria.Modelo;
import Utiliteria.Producto;

public class frmListarProductos extends javax.swing.JDialog implements ActionListener{
	private ResultSet recojedor = null;
	private JScrollPane jsp = null;
	private JPanel pnProductos = null;
	private JButton btnCerrar = null;
	private static Connection conection =ConexionBD.obtenerConexion();
	private Modelo ListarProductos;
	
	public frmListarProductos(JFrame frame, Modelo ListarProductos) {
		super(frame);
		this.ListarProductos = ListarProductos;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				btnCerrar = new JButton ("Cerrar",new ImageIcon("Imagenes/frmEnMantenimiento/cancelar.png"));
				btnCerrar.setFocusable(false);
				btnCerrar.addActionListener(this);
			}
			{
				pnProductos = new JPanel();
				pnProductos.setLayout(new GridLayout(0,2));
			}
			{
				Statement traer = conection.createStatement();
				recojedor = traer.executeQuery("Select * FROM productos");
				jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			}
			{
				while(recojedor.next()){
					FileOutputStream salidaArchivo =  new FileOutputStream("Imagenes/temp/copia_" + recojedor.getString(1)+ ".jpg");
	            	java.sql.Blob bytesImagen = recojedor.getBlob(5); 		            	
	            	java.io.InputStream is = bytesImagen.getBinaryStream(); 
	            	byte bytes[] = new byte[1024];
	            	int leidos = is.read(bytes);
	            	while (leidos > 0) {
	            		salidaArchivo.write(bytes);
	            		leidos = is.read(bytes); 
	            	}
	            	salidaArchivo.close();
	            	is.close();
	            	pnProductos.add(new Producto(recojedor.getString(1), recojedor.getInt(2), recojedor.getDouble(3), recojedor.getInt(4),"Imagenes/temp/copia_" + recojedor.getString(1)+ ".jpg", ListarProductos,"Combo"));	
				
				}
			}
			this.getContentPane().add(jsp, BorderLayout.CENTER);
			this.getContentPane().add(btnCerrar,BorderLayout.PAGE_END);
			jsp.setViewportView(pnProductos);
			this.setIconImage(new ImageIcon("Imagenes/frmProducto/frmProductos.png").getImage());
			this.setTitle("Agregar Producto");
			pack();
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setResizable(false);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == btnCerrar)
	      {
	    	this.dispose();
	      }

		
	}
	

}
