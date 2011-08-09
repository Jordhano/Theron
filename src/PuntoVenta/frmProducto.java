package PuntoVenta;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import javax.swing.SwingUtilities;

import Utiliteria.ConexionBD;
import Utiliteria.Edicion;
import Utiliteria.RestriccionANumerico;
import Utiliteria.RestriccionDeJTextField;


public class frmProducto extends Edicion {
	private JPanel pnCampos;
	private JPanel pnBotones;
	private JButton btnExaminar;
	private JLabel lblNoOrden;
	private JTextField txtfNoOrden;
	private JTextField txtfPrecio;
	private JTextField txtfCantidad;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JTextField txtfNombre;
	private JLabel lblImagenProducto;
	private JLabel lblCantidad;
	private JLabel lblPrecio;
	private JLabel lblNombre;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private static Connection conection =ConexionBD.obtenerConexion();
	private JFileChooser chooser;
	private File archivo = null;
	private FileInputStream entradaArchivo = null;
	private PreparedStatement stGuardar;


	public frmProducto() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Productos");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG,PNG & GIF", "jpg", "gif","png");
			chooser = new JFileChooser();
			chooser.setDialogTitle("Abrir Imagen");
			chooser.setFileFilter(filter);
			archivo = new File("Imagenes/frmProducto/frmProductos.png");
			entradaArchivo = new FileInputStream(archivo);
			this.setIconImage(new ImageIcon("Imagenes/frmProducto/frmProductos.png").getImage());
			this.setResizable(false);
			{
				pnCampos = new JPanel();
				getContentPane().add(pnCampos);
				pnCampos.setBounds(46, 72, 496, 387);
				pnCampos.setBorder(BorderFactory.createTitledBorder(""));
				pnCampos.setLayout(null);
				{
					lblNombre = new JLabel();
					pnCampos.add(lblNombre);
					lblNombre.setText("Nombre");
					lblNombre.setBounds(148, 190, 147, 25);
					lblNombre.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblPrecio = new JLabel();
					pnCampos.add(lblPrecio);
					lblPrecio.setText("Precio");
					lblPrecio.setBounds(148, 286, 60, 14);
					lblPrecio.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblCantidad = new JLabel();
					pnCampos.add(lblCantidad);
					lblCantidad.setText("Cantidad");
					lblCantidad.setBounds(148, 236, 147, 25);
					lblCantidad.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblImagenProducto = new JLabel();
					pnCampos.add(lblImagenProducto);
					lblImagenProducto.setBounds(113, 42, 296, 137);
					lblImagenProducto.setIcon(new ImageIcon("Imagenes/frmProducto/lblImage.png"));
					lblImagenProducto.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				}
				{
					txtfNombre = new JTextField();
					pnCampos.add(txtfNombre);
					txtfNombre.setBounds(213, 187, 147, 25);
					txtfNombre.setSize(147, 28);
					txtfNombre.setDocument(new RestriccionDeJTextField(txtfNombre,30));
				}
				{
					lblNoOrden = new JLabel();
					pnCampos.add(lblNoOrden);
					lblNoOrden.setText("No. Orden");
					lblNoOrden.setBounds(148, 329, 60, 14);
					lblNoOrden.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					btnExaminar = new JButton();
					pnCampos.add(btnExaminar);
					btnExaminar.setBounds(413, 138, 73, 41);
					btnExaminar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/importar.png"));
					btnExaminar.setFocusable(false);
					btnExaminar.addActionListener(this);
					
				}
				{
					txtfCantidad = new JTextField();
					pnCampos.add(txtfCantidad);
					txtfCantidad.setBounds(213, 234, 147, 28);
					txtfCantidad.setDocument(new RestriccionANumerico(txtfCantidad,6));
					txtfCantidad.addKeyListener(new RestriccionANumerico(txtfCantidad,true));
				
				}
				{
					txtfPrecio = new JTextField();
					pnCampos.add(txtfPrecio);
					txtfPrecio.setBounds(213, 279, 147, 26);
					txtfPrecio.setDocument(new RestriccionANumerico(txtfPrecio, 6));
					txtfPrecio.addKeyListener(new RestriccionANumerico(txtfPrecio,false));
				}
				{
					txtfNoOrden = new JTextField();
					pnCampos.add(txtfNoOrden);
					txtfNoOrden.setBounds(213, 323, 147, 27);
					txtfNoOrden.setDocument(new RestriccionANumerico(txtfNoOrden, 6));
					txtfNoOrden.addKeyListener(new RestriccionANumerico(txtfNoOrden,true));
				}
			}
			{
				pnBotones = new JPanel();
				FlowLayout pnBotonesLayout = new FlowLayout();
				pnBotonesLayout.setHgap(3);
				pnBotonesLayout.setVgap(3);
				pnBotones.setLayout(pnBotonesLayout);
				getContentPane().add(pnBotones);
				pnBotones.setBounds(56, 15, 474, 58);
				pnBotones.setBorder(BorderFactory.createTitledBorder(""));
				{
					btnNuevo = new JButton();
					pnBotones.add(btnNuevo);
					btnNuevo.setPreferredSize(new java.awt.Dimension(73, 43));
					btnNuevo.setToolTipText("Nuevo");
					btnNuevo.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/nuevo.png"));
					btnNuevo.setFocusable(false);
					btnNuevo.addActionListener(this);
				}
				{
					btnEditar = new JButton();
					pnBotones.add(btnEditar);
					btnEditar.setPreferredSize(new java.awt.Dimension(73, 43));
					btnEditar.setToolTipText("Editar");
					btnEditar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/editar.png"));
					btnEditar.setFocusable(false);
					btnEditar.addActionListener(this);
				}
				{
					btnGuardar = new JButton();
					pnBotones.add(btnGuardar);
					btnGuardar.setPreferredSize(new java.awt.Dimension(73, 43));
					btnGuardar.setToolTipText("Guardar");
					btnGuardar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/guardar.png"));
					btnGuardar.setFocusable(false);
					btnGuardar.addActionListener(this);
				}
				{
					btnBuscar = new JButton();
					pnBotones.add(btnBuscar);
					btnBuscar.setPreferredSize(new java.awt.Dimension(73, 43));
					btnBuscar.setToolTipText("Buscar");
					btnBuscar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/buscar.png"));
					btnBuscar.setFocusable(false);
					btnBuscar.addActionListener(this);
				}
				{
					btnEliminar = new JButton();
					pnBotones.add(btnEliminar);
					btnEliminar.setPreferredSize(new java.awt.Dimension(73, 43));
					btnEliminar.setToolTipText("Eliminar");
					btnEliminar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/eliminar.png"));
					btnEliminar.setFocusable(false);
					btnEliminar.addActionListener(this);
				}
				{
					btnCancelar = new JButton();
					pnBotones.add(btnCancelar);
					btnCancelar.setPreferredSize(new java.awt.Dimension(73, 43));
					btnCancelar.setToolTipText("Cancelar");
					btnCancelar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/cancelar.png"));
					btnCancelar.setFocusable(false);
					btnCancelar.addActionListener(this);
				}
				{
					inicializarPaneles(pnBotones, pnCampos, "productos");
				}
				
				

			}
			pack();
			this.setSize(608, 521);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	public static void main(String []args){
		frmProducto a = new frmProducto();
	}
	@Override 
	public void guardar(){
		boolean vacias = Edicion.iscajasVacias();
        
        	if (vacias==false){
        		try {
        			conection.setAutoCommit(false);
        			if(sNombre.equals(""))
        			{
        				stGuardar =conection.prepareStatement("INSERT Productos VALUES(?,?,?,?,?);");
        				stGuardar.setString(1, txtfNombre.getText());
        				stGuardar.setInt(2, Integer.parseInt(txtfCantidad.getText()));
        				stGuardar.setDouble(3, Double.parseDouble(txtfPrecio.getText()));
        				stGuardar.setInt(4, Integer.parseInt(txtfNoOrden.getText()));
        				stGuardar.setBinaryStream(5, entradaArchivo, (int) archivo.length());
        				stGuardar.execute();
        		
        			}else{
        		
        				stGuardar = conection.prepareStatement(("UPDATE Productos SET PNombre = ?, Cantidad= ?, Precio =?, No_Orden =?, Imagen =? WHERE PNombre = ? ;"));
        				stGuardar.setString(1, txtfNombre.getText());
        				stGuardar.setInt(2, Integer.parseInt(txtfCantidad.getText()));
        				stGuardar.setDouble(3, Double.parseDouble(txtfPrecio.getText()));
        				stGuardar.setInt(4, Integer.parseInt(txtfNoOrden.getText()));
        				stGuardar.setBinaryStream(5, entradaArchivo, (int) archivo.length());
        				stGuardar.setString(6, sNombre);
        				stGuardar.executeUpdate();
        			
        			}
        			conection.commit();
        			conection.setAutoCommit(true);
        			sNombre =  txtfNombre.getText();
        			JOptionPane.showMessageDialog(this,"Producto Grabado");
		   			cambiarEstadoObjetoInternos(false);
		   			opcionesBotones(true, false);
        		}catch (Exception e) {
        			JOptionPane.showMessageDialog(this,"Existe Un Producto Con el Mismo Nombre","Aviso",JOptionPane.ERROR_MESSAGE);
        		}
        	}else{	
        	JOptionPane.showMessageDialog(this,"Existen Campos Incompletos","Aviso",JOptionPane.ERROR_MESSAGE);
        	}
        
        	       
	}

	@Override
	public void buscar() {
		String iNombre;
		ResultSet recojedor;
		FileOutputStream salidaArchivo;
		iNombre = JOptionPane.showInputDialog(this, "Ingrese El Nombre");
			try {
				Statement ejecutador = conection.createStatement();
				recojedor = ejecutador.executeQuery("SELECT * FROM productos WHERE PNombre = '" + iNombre +"'");
	           if(recojedor.next() == false){
	        	   JOptionPane.showMessageDialog(null, "Registro No Encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
	           }else {
	        	   
	           recojedor.previous();
				while(recojedor.next()){
	            	salidaArchivo =  new FileOutputStream("Imagenes/temp/copia_" + recojedor.getString(1)+ ".jpg");
	            	txtfNombre.setText(recojedor.getString(1));
	            	Edicion.sNombre = txtfNombre.getText();
	            	txtfCantidad.setText(recojedor.getString(2));
	            	txtfPrecio.setText(recojedor.getString(3));
	            	txtfNoOrden.setText(recojedor.getString(4));
	            	Blob bytesImagen = recojedor.getBlob(5); 		            	
	            	InputStream is = bytesImagen.getBinaryStream(); 
	            	byte bytes[] = new byte[1024];
	            	int leidos = is.read(bytes);
	            	while (leidos > 0) {
	            		salidaArchivo.write(bytes);
	            		leidos = is.read(bytes); 
	            	}
	            	salidaArchivo.close();
	            	is.close();
					archivo = new File("Imagenes/temp/copia_" + recojedor.getString(1)+ ".jpg");
					entradaArchivo = new FileInputStream(archivo);
	            	colocarImagen("Imagenes/temp/copia_" + recojedor.getString(1)+ ".jpg");
	            }
	           }
	           
			} catch (Exception e) {
				
			}
	}

	@Override
	public void obtenerInformacion() {
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo){
			try {
				nuevo();
				lblImagenProducto.setIcon(new ImageIcon("Imagenes/frmProducto/lblImage.png"));
				archivo = new File("Imagenes/frmProducto/frmProductos.png");
				entradaArchivo = new FileInputStream(archivo);
			} catch (FileNotFoundException e1) {
			}
		}else if (e.getSource() == btnEditar){
			editar();
		}else if(e.getSource() == btnGuardar ){
			guardar();
		}else if (e.getSource() == btnBuscar){
			buscar();
		}else if (e.getSource() == btnEliminar){
			try {
				eliminar("PNombre");
				if (Edicion.sNombre.equals("")){
					lblImagenProducto.setIcon(new ImageIcon("Imagenes/frmProducto/lblImage.png"));
					archivo = new File("Imagenes/frmProducto/frmProductos.png");
					entradaArchivo = new FileInputStream(archivo);	
				}
			} catch (FileNotFoundException e1) {
			}
		}else if (e.getSource() == btnCancelar){
			try {
				cancelar();
				lblImagenProducto.setIcon(new ImageIcon("Imagenes/frmProducto/lblImage.png"));
				archivo = new File("Imagenes/frmProducto/frmProductos.png");
				entradaArchivo = new FileInputStream(archivo);
			} catch (FileNotFoundException e1) {
			}
		}else if (e.getSource() == btnExaminar){
			abrirArchivo();
		}
		
	}	
	
	public void abrirArchivo(){
		chooser.setAcceptAllFileFilterUsed(false);
		int returnVal = chooser.showOpenDialog(this);
		try{
			if(returnVal == JFileChooser.APPROVE_OPTION){
				if (chooser.getSelectedFile().length() <10485760){
					archivo = chooser.getSelectedFile();
					entradaArchivo = new FileInputStream(archivo);
					colocarImagen(chooser.getSelectedFile().toString());
				}else{
					JOptionPane.showMessageDialog(null, "Intento de Insertar Archivo Mayor de 10 MB Capturado", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch(Exception e){
			   
		}
	}
	
	private void colocarImagen(String dirrecion){
		int w = lblImagenProducto.getSize().width;
		int h = lblImagenProducto.getSize().height;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(dirrecion);
		ImageIcon icon = new ImageIcon(image);
		if (w<=icon.getIconWidth() && h<=icon.getIconHeight()){
			Image scaledImage = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);    
			icon =new ImageIcon(scaledImage);
			lblImagenProducto.setIcon(icon);
		}else{
			lblImagenProducto.setIcon(icon);
		}
		
	}

}

