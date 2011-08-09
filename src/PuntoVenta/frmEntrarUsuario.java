package PuntoVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import Utiliteria.ConexionBD;

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
public class frmEntrarUsuario extends JDialog implements ActionListener{
	private JLabel lblImagen;
	private JLabel lblUsuario;
	private JTextField txtfUsuario;
	private JPasswordField txtContrasena;
	private JButton btnSalir;
	private JButton btnEntrar;
	private JLabel lblContrasena;
	private PreparedStatement ejecutador;
	private ResultSet recojedor;
	private Connection conection =ConexionBD.obtenerConexion();
	private String tipoUsuario;
	private frmPrincipal ventanaPrincipal;
	private String usuario;
	
	public frmEntrarUsuario(){	
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		this.setTitle("Bienvenidos A Theron- [Formulario De Acceso]");
		this.setIconImage(new ImageIcon("Imagenes/frmUsuario/frmUsuarioIcono.png").getImage());
		this.setSize(551, 242);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
		{
			lblImagen = new JLabel();
			getContentPane().add(lblImagen);
			lblImagen.setBounds(9, 23, 240, 168);
			lblImagen.setIcon(new ImageIcon("Imagenes/frmPrincipal/Back.png"));
		}
		{
			lblUsuario = new JLabel();
			getContentPane().add(lblUsuario);
			lblUsuario.setText("Usuario");
			lblUsuario.setBounds(263, 62, 44, 14);
		}
		{
			txtfUsuario = new JTextField();
			getContentPane().add(txtfUsuario);
			txtfUsuario.setBounds(317, 48, 181, 33);
		}
		{
			lblContrasena = new JLabel();
			getContentPane().add(lblContrasena);
			lblContrasena.setText("Contraseña");
			lblContrasena.setBounds(243, 105, 67, 14);
		}
		{
			txtContrasena = new JPasswordField();
			getContentPane().add(txtContrasena);
			txtContrasena.setBounds(317, 92, 181, 33);
		}
		{
			btnEntrar = new JButton();
			getContentPane().add(btnEntrar);
			btnEntrar.setText("Entrar");
			btnEntrar.setIcon(new ImageIcon("Imagenes/frmUsuario/Lock2.png"));
			btnEntrar.setBounds(219, 147, 157, 62);
			btnEntrar.setFocusable(false);
			btnEntrar.addActionListener(this);
		}
		{
			btnSalir = new JButton();
			getContentPane().add(btnSalir);
			btnSalir.setText("Salir");
			btnSalir.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnSalir.png"));
			btnSalir.setBounds(376, 147, 157, 62);
			btnSalir.setFocusable(false);
			btnSalir.addActionListener(this);
		}
	}
	public static void main(String []args){
		frmEntrarUsuario a = new frmEntrarUsuario();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnEntrar){
			if (txtfUsuario.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Error, Campo Usuario", "Aviso", JOptionPane.ERROR_MESSAGE);
			}else if (txtContrasena.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Error, Campo Contraseña", "Aviso", JOptionPane.ERROR_MESSAGE);
			}else {
				Buscar();
			}
		}else if (arg0.getSource() == btnSalir){
			System.exit(0);
		}
	}
	
	public void Buscar(){
		try {
			txtfUsuario.getText();
			ejecutador = conection.prepareStatement("SELECT * FROM usuario WHERE (usuario =  ?)AND (constrasena = ?)  ");
			ejecutador.setString(1, txtfUsuario.getText());
			ejecutador.setString(2, String.valueOf(txtContrasena.getPassword()));
			recojedor = ejecutador.executeQuery();
			
			if (!recojedor.next()){
				JOptionPane.showMessageDialog(null, "Error, Usuario Invalido", "Aviso", JOptionPane.ERROR_MESSAGE);
				txtfUsuario.setText("");
				txtContrasena.setText("");
			}else{
				recojedor.previous();
				while(recojedor.next()){
					usuario = recojedor.getString(1);
					tipoUsuario = recojedor.getString(3);
				}
				tipoUsuario(tipoUsuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void tipoUsuario(String usuario){
		ventanaPrincipal = new frmPrincipal();
		ventanaPrincipal.setTitle("Theron™ - "+ this.usuario + " - Modo " + usuario);
		if (usuario.equals("Vendedor")){
			ventanaPrincipal.setVisible(true);
		}else if (usuario.equals("Cajero")){
			ventanaPrincipal.setBtnRegistrarUsuario(false);
			ventanaPrincipal.setBtnRegistrarCombo(false);
			ventanaPrincipal.setBtnRegistrarProducto(false);
			ventanaPrincipal.setMnuiCUsuario(false);
			ventanaPrincipal.setMnuiUsuario(false);
			ventanaPrincipal.setMnuiCombo(false);
			ventanaPrincipal.setMnuiProducto(false);
			ventanaPrincipal.setVisible(true);
		}
		this.dispose();
		
	}
}	

