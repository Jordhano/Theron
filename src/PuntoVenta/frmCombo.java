package PuntoVenta;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import Utiliteria.ConexionBD;
import Utiliteria.Edicion;
import Utiliteria.Modelo;
import Utiliteria.Producto;
import Utiliteria.RestriccionANumerico;
import Utiliteria.RestriccionDeJTextField;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class frmCombo extends Edicion implements KeyListener{

	private JPanel pnCampos;
	private JPanel pnBotones;
	private JTextField txtfPrecio;
	private JLabel lblImage;
	private JTextField txtfDescuento;
	private JTextField txtfNombre;
	private JButton btnBorrarProducto;
	private JButton btnEditar;
	private JButton btnCancelar;
	private JButton btnAgregarProducto;
	private JLabel lblPrecio;
	private JLabel lblDescuento;
	private JLabel lblNombre;
	private JTable tblCombos;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnGuardar;
	private JButton btnNuevo;
	Modelo tblCombosModelo;
	private static Connection conection =ConexionBD.obtenerConexion();
	private JScrollPane jsp;
	private PreparedStatement stGuardar;
	private File archivo = null;
	private FileInputStream entradaArchivo = null;
	private ResultSet recojedor;

	public frmCombo() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Combos");
			this.setIconImage(new ImageIcon("Imagenes/frmCombos/frmCombos.png").getImage());
			this.setResizable(false);
			{
				pnCampos = new JPanel();
				getContentPane().add(pnCampos);
				pnCampos.setBounds(46, 72, 496, 387);
				pnCampos.setBorder(BorderFactory.createTitledBorder(""));
				pnCampos.setLayout(null);
				{
					tblCombosModelo = new Modelo(new String[] {"Producto Nombre", "Cantidad", "Precio","Combo"});
					tblCombos = new JTable(tblCombosModelo);
					tblCombos.setBounds(46, 252, 399, 123);
				}
				{
					jsp = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					jsp.setBounds(46, 252, 399, 123);
					jsp.getVerticalScrollBar().setEnabled(true);
					jsp.setViewportView(tblCombos);
					pnCampos.add(jsp);
					jsp.setFocusTraversalKeysEnabled(false);
				}
				{
					lblNombre = new JLabel();
					pnCampos.add(lblNombre);
					lblNombre.setText("Nombre");
					lblNombre.setBounds(58, 44, 74, 14);
					lblNombre.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblDescuento = new JLabel();
					pnCampos.add(lblDescuento);
					lblDescuento.setText("Descuento");
					lblDescuento.setBounds(46, 100, 74, 14);
					lblDescuento.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblPrecio = new JLabel();
					pnCampos.add(lblPrecio);
					lblPrecio.setText("Precio");
					lblPrecio.setBounds(58, 155, 74, 14);
					lblPrecio.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					btnAgregarProducto = new JButton();
					pnCampos.add(btnAgregarProducto);
					btnAgregarProducto.setBounds(124, 197, 104, 44);
					btnAgregarProducto.setToolTipText("Agregar Producto");
					btnAgregarProducto.setIcon(new ImageIcon("Imagenes/frmCombos/Añadir.png"));
					btnAgregarProducto.setFocusable(false);
					btnAgregarProducto.addActionListener(this);
				}
				{	
					btnBorrarProducto = new JButton();
					pnCampos.add(btnBorrarProducto);
					btnBorrarProducto.setBounds(262, 197, 104, 44);
					btnBorrarProducto.setIcon(new ImageIcon("Imagenes/frmCombos/Borrar.png"));
					btnBorrarProducto.setFocusable(false);
					btnBorrarProducto.setToolTipText("Borrar Producto");
					btnBorrarProducto.addActionListener(this);
				}
				{
					txtfNombre = new JTextField();
					pnCampos.add(txtfNombre);
					txtfNombre.setBounds(124, 33, 199, 37);
					txtfNombre.setDocument(new RestriccionDeJTextField(txtfNombre,25));
					
				}
				{
					txtfDescuento = new JTextField();
					pnCampos.add(txtfDescuento);
					txtfDescuento.setBounds(124, 89, 124, 37);
					txtfDescuento.addKeyListener(this);
					txtfDescuento.addNotify();
					txtfDescuento.addKeyListener(new RestriccionANumerico(txtfDescuento,false));
				}
				{
					txtfPrecio = new JTextField();
					pnCampos.add(txtfPrecio);
					txtfPrecio.setBounds(124, 144, 124, 37);
					txtfPrecio.setFocusable(false);
					
				}
				{
					lblImage = new JLabel();
					pnCampos.add(lblImage);
					lblImage.setBounds(327, 20, 148, 136);
					lblImage.setIcon(new ImageIcon("Imagenes/frmCombos/frmCombos.png"));
				}
			}
			{
				pnBotones = new JPanel();
				FlowLayout pnBotonesLayout = new FlowLayout();
				pnBotonesLayout.setHgap(3);
				pnBotonesLayout.setVgap(3);
				pnBotones.setLayout(pnBotonesLayout);
				getContentPane().add(pnBotones);
				pnBotones.setBounds(56, 18, 474, 55);
				pnBotones.setBorder(BorderFactory.createTitledBorder(""));
				{
					btnNuevo = new JButton();
					pnBotones.add(btnNuevo);
					btnNuevo.setPreferredSize(new java.awt.Dimension(73, 40));
					btnNuevo.setToolTipText("Nuevo");
					btnNuevo.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/nuevo.png"));
					btnNuevo.setFocusable(false);
					btnNuevo.addActionListener(this);
				}
				{
					btnEditar = new JButton();
					pnBotones.add(btnEditar);
					btnEditar.setPreferredSize(new java.awt.Dimension(73, 40));
					btnEditar.setToolTipText("Editar");
					btnEditar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/editar.png"));
					btnEditar.setFocusable(false);
					btnEditar.addActionListener(this);
				}
				{
					btnGuardar = new JButton();
					pnBotones.add(btnGuardar);
					btnGuardar.setPreferredSize(new java.awt.Dimension(73, 40));
					btnGuardar.setToolTipText("Guardar");
					btnGuardar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/guardar.png"));
					btnGuardar.setFocusable(false);
					btnGuardar.addActionListener(this);
				}
				{
					btnBuscar = new JButton();
					pnBotones.add(btnBuscar);
					btnBuscar.setPreferredSize(new java.awt.Dimension(73, 40));
					btnBuscar.setToolTipText("Buscar");
					btnBuscar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/buscar.png"));
					btnBuscar.setFocusable(false);
					btnBuscar.addActionListener(this);
				}
				{
					btnEliminar = new JButton();
					pnBotones.add(btnEliminar);
					btnEliminar.setPreferredSize(new java.awt.Dimension(73, 40));
					btnEliminar.setToolTipText("Eliminar");
					btnEliminar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/eliminar.png"));
					btnEliminar.setFocusable(false);
					btnEliminar.addActionListener(this);
				}
				{
					btnCancelar = new JButton();
					pnBotones.add(btnCancelar);
					btnCancelar.setPreferredSize(new java.awt.Dimension(73, 40));
					btnCancelar.setToolTipText("Cancelar");
					btnCancelar.setIcon(new ImageIcon("Imagenes/frmEnMantenimiento/cancelar.png"));
					btnCancelar.setFocusable(false);
					btnCancelar.addActionListener(this);
				}
				{
					inicializarPaneles(pnBotones, pnCampos, "combo");
				}
			}		
			pack();
			this.setSize(608, 521);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buscar() {
		ResultSet temp2;
		
		String iNombre;
		iNombre = JOptionPane.showInputDialog(this, "Ingrese El Nombre");
		try {
			Statement ejecutador = conection.createStatement();
			Statement loqueria = conection.createStatement();
			recojedor = ejecutador.executeQuery("SELECT * FROM Combo WHERE CNombre = '" + iNombre +"';");
           if(recojedor.next() == false){
        	   JOptionPane.showMessageDialog(null, "Registro No Encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
           }else {
        	   recojedor.previous();
        	   while(recojedor.next()){
        		   txtfNombre.setText(recojedor.getString(1));
        		   txtfDescuento.setText(recojedor.getString(2));
        		   txtfPrecio.setText(recojedor.getString(3));
        	   }
        	   Edicion.sNombre = txtfNombre.getText();
   				for (int i = tblCombos.getRowCount();  i>0 ; i--){
   					tblCombosModelo.eliminar(i-1);
   				}
        	   recojedor = ejecutador.executeQuery("SELECT * FROM Detalle_Combo WHERE CNombre = '" + iNombre +"';");
        	   while(recojedor.next()){
        		   temp2 = loqueria.executeQuery("SELECT PNombre,Cantidad,Precio FROM Productos WHERE PNombre = '" + recojedor.getString(2) +"';");
        		   while(temp2.next()){
        			 tblCombosModelo.insertarDatos(new Producto(temp2.getString(1),temp2.getInt(2), temp2.getDouble(3)));
        		   }
        	   }
        	   
        	   
           }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void guardar() {
		boolean vacias = Edicion.iscajasVacias();
		
	
		if (vacias==false &&  tblCombos.getRowCount() > 0 ){
			try{
				GeneradorImagenes();
				conection.setAutoCommit(false);
				if (sNombre.equals((""))){
					stGuardar =conection.prepareStatement("INSERT Combo VALUES(?,?,?,?);");
					stGuardar.setString(1, txtfNombre.getText());
    				stGuardar.setDouble(2, Double.parseDouble(txtfDescuento.getText()));
    				stGuardar.setDouble(3, Double.parseDouble(txtfPrecio.getText()));
    				stGuardar.setBinaryStream(4, entradaArchivo, (int) archivo.length());
    				stGuardar.execute();
    				for (int i = 0; i <tblCombos.getRowCount(); i++){
    					stGuardar =conection.prepareStatement("INSERT Detalle_Combo VALUES(?,?);");
    					stGuardar.setString(1, txtfNombre.getText());
        				stGuardar.setString(2, (String) tblCombos.getValueAt(i,0));
        				stGuardar.execute();
    				}
				}else {
					stGuardar =conection.prepareStatement("UPDATE Combo SET CNombre = ?, Descuento= ?, Precio =?, Imagen =? WHERE CNombre = ? ;");
					stGuardar.setString(1, txtfNombre.getText());
					stGuardar.setDouble(2, Double.parseDouble(txtfDescuento.getText()));
    				stGuardar.setDouble(3, Double.parseDouble(txtfPrecio.getText()));
    				stGuardar.setBinaryStream(4, entradaArchivo, (int) archivo.length());
    				stGuardar.setString(5, sNombre);
    				stGuardar.executeUpdate();
    				
    				stGuardar =conection.prepareStatement("DELETE FROM Detalle_Combo WHERE CNombre = ? ;");
    				stGuardar.setString(1, sNombre);
    				stGuardar.executeUpdate();
    				for (int i = 0; i <tblCombos.getRowCount(); i++){
    					stGuardar =conection.prepareStatement("INSERT Detalle_Combo VALUES(?,?);");
    					stGuardar.setString(1, txtfNombre.getText());
        				stGuardar.setString(2, (String) tblCombos.getValueAt(i,0));
        				stGuardar.execute();
    				}
				}
				conection.commit();
				conection.setAutoCommit(true);
				sNombre =  txtfNombre.getText();
				JOptionPane.showMessageDialog(this,"Combo Grabado");
				cambiarEstadoObjetoInternos(false);
				opcionesBotones(true, false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(this,"Existe Un Combo Con el Mismo Nombre","Aviso",JOptionPane.ERROR_MESSAGE);
				
			}
		}else {
			JOptionPane.showMessageDialog(this,"Existen Campos Incompletos","Aviso",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void obtenerInformacion() {
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNuevo){
			nuevo();
			for (int i = tblCombos.getRowCount();  i>0 ; i--){
				tblCombosModelo.eliminar(i-1);
			}
		}else if (e.getSource() == btnEditar){
			editar();
		}else if(e.getSource() == btnGuardar ){
			guardar();
		}else if (e.getSource() == btnBuscar){
			buscar();
		}else if (e.getSource() == btnEliminar){
			if (!sNombre.equals("")){
				try {
					Statement a = conection.createStatement();
					a.execute("DELETE FROM Detalle_Combo WHERE CNombre = '"+sNombre +"';");
					eliminar("CNombre");
					for (int i = tblCombos.getRowCount();  i>0 ; i--){
						tblCombosModelo.eliminar(i-1);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}else{
				JOptionPane.showMessageDialog(this,"Debe Tener Un Registro Seleccionado","Aviso",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (e.getSource() == btnCancelar){
			cancelar();
			for (int i = tblCombos.getRowCount();  i>0 ; i--){
				tblCombosModelo.eliminar(i-1);
			}
		}else if (e.getSource() == btnAgregarProducto){
			AgregarProducto();
		}else if (e.getSource() == btnBorrarProducto){
			boolean elegido = false;
			for (int i = 0; i <= tblCombos.getRowCount() ; i++){
				if (tblCombos.isRowSelected(i)){
					tblCombosModelo.eliminar(tblCombos.getSelectedRow());
					elegido = true;
					break;
				}
			}
			if (elegido == false){
				JOptionPane.showMessageDialog(this, "No Hay Productos Selecionado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void AgregarProducto(){
		new frmListarProductos(null, tblCombosModelo);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String tempCaja = txtfDescuento.getText();
		String tempKeyChar = String.valueOf(arg0.getKeyChar());
		String tempDos="";
		try{
			tempDos = tempCaja.substring(tempCaja.indexOf("."),tempCaja.length());
		}catch(Exception e){
			
		}
		if (tempKeyChar.equals(".") && tempDos.indexOf(".") != -1){
			txtfDescuento.setText(tempCaja.substring(0, tempCaja.indexOf(".")+1));
		}
		double descuento = 0;
		double valor = 0;
		double total = 0;
		try{
			for (int i = 0; i < tblCombos.getRowCount() ; i++){
				valor = Double.parseDouble((String) tblCombos.getValueAt(i, 2));
				descuento = (valor * (Double.parseDouble(txtfDescuento.getText())/100));
				total += (valor - descuento);
			}
		}catch(Exception e){
			total = 0;
		}
		txtfPrecio.setText(String.valueOf(total));
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void GeneradorImagenes(){
		try {
			Statement traer = conection.createStatement();
			JFrame unaVentana = new JFrame();
			unaVentana.getContentPane().setLayout(new FlowLayout());
			for (int i = 0; i <tblCombos.getRowCount(); i++){
				recojedor = traer.executeQuery("SELECT * FROM productos WHERE PNombre = '" + tblCombos.getValueAt(i,0) +"'");
				while(recojedor.next()){
					FileOutputStream salidaArchivo =  new FileOutputStream("Imagenes/temp/copia_" + tblCombos.getValueAt(i,0)+ ".jpg");
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
	            	unaVentana.getContentPane().add(new JLabel(colocarImagen("Imagenes/temp/copia_" + tblCombos.getValueAt(i,0)+ ".jpg")));
				}
				
			}				            	
			unaVentana.pack();
			archivo = new File("Imagenes/temp/comboTemp.jpg");
			String formato = "jpg";
			
			BufferedImage imagen = new BufferedImage(unaVentana.getContentPane().getWidth(),unaVentana.getContentPane().getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = imagen.getGraphics();
			unaVentana.getContentPane().paint(g);
			
			try {
				ImageIO.write(imagen, formato, archivo);
				entradaArchivo = new FileInputStream(archivo);
			} catch (Exception e) {
				System.out.println("Error de escritura");
			}
		} catch (Exception e) {
			System.out.println("Nad, Error Intentando");
		}
		
	}
	
	private ImageIcon colocarImagen(String dirrecion){
		int w = 128;
		int h = 128;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(dirrecion);
		ImageIcon icon = new ImageIcon(image);
		if (w<=icon.getIconWidth() && h<=icon.getIconHeight()){
			
			Image scaledImage = image.getScaledInstance(w, h, Image.SCALE_DEFAULT);    
			icon =new ImageIcon(scaledImage);
			return icon;
		}else{
			return(icon);
		}
		
	}

}
