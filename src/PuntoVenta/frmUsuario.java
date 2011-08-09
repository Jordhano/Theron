package PuntoVenta;
import java.awt.BorderLayout;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.management.timer.TimerMBean;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingUtilities;

import Utiliteria.ConexionBD;
import Utiliteria.Edicion;

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
public class frmUsuario extends Edicion {
	private JPanel pnCampos;
	private JPanel pnBotones;
	private JLabel lblImage;

	private static JTextField txtfNombre;
	private static JComboBox cboTipoUsuario;
	private static JPasswordField txtfContrasena;
	public static JTextField txtfUsuario;
	private JLabel lblFechaRegistro;
	private static JLabel lblTelefono;
	private JButton btnEditar;
	private JButton btnCancelar;
	
	private JLabel lblNombre;
	private JLabel lblTipoUsuario;
	private JLabel lblContrasena;
	private JLabel lblUsuario;
	private JButton btnEliminar;
	private JButton btnBuscar;
	public static JButton btnGuardar;
	private JButton btnNuevo;
	public static JFormattedTextField txtfFechaRegistro;
	public static JFormattedTextField txtfTelefono;
	
	private static String sentGuardarUsuario;
	private static String sentEditarUsuario;
	private static Connection conection =ConexionBD.obtenerConexion();
	
	public String fechaDeRegistro;
	
	public  frmUsuario() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Usuarios");
			this.setIconImage(new ImageIcon("Imagenes/frmUsuario/frmUsuarioIcono.png").getImage());
			this.setResizable(false);
			

			{
				pnCampos = new JPanel();
				getContentPane().add(pnCampos);
				pnCampos.setBounds(44, 73, 496, 387);
				pnCampos.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				pnCampos.setLayout(null);
				{
					lblUsuario = new JLabel();
					pnCampos.add(lblUsuario);
					lblUsuario.setText("Usuario");
					lblUsuario.setBounds(128, 111, 105, 15);
					lblUsuario.setFont(new java.awt.Font("Tahoma",1,11));
					lblUsuario.setSize(105, 28);
				
				}
				{
					lblContrasena = new JLabel();
					pnCampos.add(lblContrasena);
					lblContrasena.setBounds(128, 143, 105, 28);
					lblContrasena.setText("Contrase�a");
					lblContrasena.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblTipoUsuario = new JLabel();
					pnCampos.add(lblTipoUsuario);
					lblTipoUsuario.setText("Tipo de Usuario");
					lblTipoUsuario.setBounds(127, 172, 105, 28);
					lblTipoUsuario.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblNombre = new JLabel();
					pnCampos.add(lblNombre);
					lblNombre.setText("Nombre");
					lblNombre.setBounds(128, 218, 105, 15);
					lblNombre.setFont(new java.awt.Font("Tahoma",1,11));
				}
				
				{
					lblTelefono = new JLabel();
					pnCampos.add(lblTelefono);
					lblTelefono.setText("Telefono");
					lblTelefono.setBounds(128, 258, 105, 28);
					lblTelefono.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					lblFechaRegistro = new JLabel();
					pnCampos.add(lblFechaRegistro);
					lblFechaRegistro.setText("Fecha de Registro");
					lblFechaRegistro.setBounds(126, 298, 106, 28);
					lblFechaRegistro.setFont(new java.awt.Font("Tahoma",1,11));
				}
				{
					txtfUsuario = new JTextField();
					pnCampos.add(txtfUsuario);
					txtfUsuario.setBounds(232, 108, 105, 20);
					txtfUsuario.setSize(105, 28);
					txtfUsuario.setDocument(new RestriccionDeJTextField(txtfUsuario,10));
				}
				{
					txtfContrasena = new JPasswordField();
					pnCampos.add(txtfContrasena);
					txtfContrasena.setBounds(232, 143, 105, 28);
					txtfContrasena.setDocument(new RestriccionDeJTextField(txtfContrasena,10));
				}
				{
					ComboBoxModel cboTipoUsuarioModel = 
						new DefaultComboBoxModel(
								new String[] { "Vendedor", "Cajero" });
					cboTipoUsuario = new JComboBox();
					pnCampos.add(cboTipoUsuario);
					cboTipoUsuario.setModel(cboTipoUsuarioModel);
					cboTipoUsuario.setBounds(232, 177, 105, 28);
					
				}
				{
					txtfNombre = new JTextField();
					pnCampos.add(txtfNombre);
					txtfNombre.setBounds(232, 211, 105, 20);
					txtfNombre.setSize(105, 28);
					txtfNombre.setDocument(new RestriccionDeJTextField(txtfNombre,15));
					
				}
				{
					lblImage = new JLabel();
					pnCampos.add(lblImage);
					lblImage.setBounds(179, 3, 142, 99);
					lblImage.setIcon(new ImageIcon("Imagenes/frmUsuario/frmUsuario.png"));
				}
				{
					txtfTelefono = new JFormattedTextField(new MaskFormatter(" ###-###-####"));
					txtfTelefono.setValue(new String(" 222-222-2222"));
					txtfTelefono.setFocusLostBehavior(JFormattedTextField.COMMIT);
					pnCampos.add(txtfTelefono);
					txtfTelefono.setBounds(232, 258, 104, 31);
				}
				{
					txtfFechaRegistro = new JFormattedTextField(new MaskFormatter(" ####-##-##"));
					txtfFechaRegistro.setText(" 2010-12-12");
					txtfTelefono.setFocusLostBehavior(JFormattedTextField.COMMIT);
					pnCampos.add(txtfFechaRegistro);
					txtfFechaRegistro.setBounds(232, 301, 104, 33);
				}
			}
			{
				pnBotones = new JPanel();
				FlowLayout pnBotonesLayout = new FlowLayout();
				pnBotonesLayout.setHgap(3);
				pnBotonesLayout.setVgap(3);
				pnBotones.setLayout(pnBotonesLayout);
				getContentPane().add(pnBotones);
				pnBotones.setBounds(56, 23, 474, 50);
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
				inicializarPaneles(pnBotones, pnCampos, "usuario");
				}
			}
			
			
			pack();
			this.setSize(608, 521);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
			
		} catch (Exception e) {
		   
		}
	}
	
	@Override 
	public void obtenerInformacion()
	{
		String usuario=txtfUsuario.getText();
		String contrasena= String.valueOf(txtfContrasena.getPassword());
		String tipoDeUsuario=(String) cboTipoUsuario.getSelectedItem();
		String nombre=txtfNombre.getText();
		String telefono=txtfTelefono.getText();
		fechaDeRegistro=txtfFechaRegistro.getText();
	
			sentGuardarUsuario="INSERT INTO usuario VALUES("+"'"+usuario+"'"+","+"'"+contrasena+"'"+","+
													    "'"+tipoDeUsuario+"'"+","+"'"+nombre+"'"+","+
													    "'"+telefono+"'"+","+"'"+fechaDeRegistro+"'"+");";	
		sentEditarUsuario=	"UPDATE usuario SET Usuario= "+"'"+usuario+"'"+","+" Constrasena = "+"'"+contrasena+"'"+","+"Tipo_Usuario = "+"'"+tipoDeUsuario+"'"+","+"Nombre = "+"'"+nombre+"'"+","+
		"Telefono = "+"'"+telefono+"'"+","+" Fecha_Registro = "+"'"+fechaDeRegistro+"'"+" WHERE Usuario = " +"'"+Edicion.sNombre+"';";								
	
	}

	@Override
	public void buscar() {
		
		opcionesBotones(true, false);
	
		PreparedStatement stBuscar;

		String nombre=JOptionPane.showInputDialog(this,"Introduzca El Nombre De Usuario");
		
		try{
		  stBuscar= conection.prepareStatement("SELECT * FROM usuario WHERE usuario =  ?  ");
		  
		
		  stBuscar.setString(1,nombre);
		  
		  
		  ResultSet result=stBuscar.executeQuery();
		  
		if(!result.next()){
			if(!nombre.equals("")){
				JOptionPane.showMessageDialog(null, "Registro No Encontrado", "Aviso", JOptionPane.ERROR_MESSAGE);
			}
			
		}else{
			result.previous();
		
		 ColocarInformacionUsuario(result);
		 Edicion.sNombre=txtfUsuario.getText();
		 
		  cambiarEstadoObjetoInternos(true);
		 cambiarEstadoEscrituraObjetoInternos(false);
		}
		
	}catch(Exception t){}
	}

	@Override
	public void guardar() {
		
		boolean t=false;
		boolean validacionFecha;
		PreparedStatement stGuardar;
		t=Edicion.iscajasVacias();
		
		if(t==false) {
			validacionFecha=validarFecha(txtfFechaRegistro.getText().trim());

            if(validacionFecha==true){
            	
            	 try {
            		 if(!comprobacionDeExistenciaEnBD("Usuario")){
            	
		   				obtenerInformacion();
		   				stGuardar=conection.prepareStatement(sentGuardarUsuario);
		   				stGuardar.executeUpdate();
		   				
            		 } else{
            		obtenerInformacion();
            		stGuardar=conection.prepareStatement(sentEditarUsuario);
	   				stGuardar.executeUpdate();
            	 }
            		 
		   			JOptionPane.showMessageDialog(this,"Usuario Grabado");
		   			cambiarEstadoObjetoInternos(false);
		   			opcionesBotones(true, false);
	   			}catch (Exception e){
	   				JOptionPane.showMessageDialog(this,"Existe Un Usuario Con el Mismo Nombre","Aviso",JOptionPane.ERROR_MESSAGE);
	   				e.printStackTrace();
	   			}
		}else
			JOptionPane.showMessageDialog(this,"Error En Formato De Fecha","Aviso",JOptionPane.ERROR_MESSAGE);
}else
	JOptionPane.showMessageDialog(this,"Existen Campos Incompletos","Aviso",JOptionPane.ERROR_MESSAGE);
	}


	

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnNuevo) {
			nuevo();
		}else if(event.getSource() == btnEditar){
			editar();
		}else if(event.getSource() == btnGuardar){
			guardar();
		}else if(event.getSource() == btnBuscar){
			buscar();
		}else if(event.getSource() == btnEliminar){	
			eliminar("Usuario");
		}else if(event.getSource() == btnCancelar){
			cancelar();
		}
		
		
	}	
	
	  public boolean validarFecha(String fecha)
	  {
		  boolean state=false;
		 
		  int dia=Integer.parseInt(fecha.substring(8)); 
		  int mes=Integer.parseInt(fecha.substring(5,7));
		  int anio=Integer.parseInt(fecha.substring(0,4));
		
		  int [] meses={31,28,31,30,31,30,31,31,30,31,30,31};
		 
		if(anio>=1000){
			if(mes<=12 && mes>0)
			{
				if(dia<=meses[mes-1])
				{
				state=true;
				}
			}else
			state=false;
		
		 }
		
return state;
		  
	  }
		public static void ColocarInformacionUsuario(ResultSet result)
		{
			try{
				while(result.next())
				{
			txtfUsuario.setText(result.getString(1));
			txtfContrasena.setText(result.getString(2));
			cboTipoUsuario.setSelectedItem(result.getString(3));
			txtfNombre.setText(result.getString(4));
			txtfTelefono.setText(result.getString(5));
			txtfFechaRegistro.setText(String.valueOf(result.getDate(6)));
				}
			}catch(Exception e){
				
			}
		}
		}
	
	
	


