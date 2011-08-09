package PuntoVenta;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Utiliteria.ConexionBD;
import Utiliteria.pnFondo;


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
public class frmPrincipal extends javax.swing.JFrame implements ActionListener{
	private JMenuBar mnubBarra;
	private JMenu mnuMantenimiento;
	private JButton btnSalir;
	private JButton btnRegistrarUsuario;
	private JButton btnModoFacturacion;
	private JButton btnListadoCombo;
	private JButton btnListadoProductos;
	private JButton btnRegistrarCombo;
	private JButton btnRegistrarProducto;
	private JPanel pnMenu;
	private JMenuItem mnuiAAyuda;
	private JMenu mnuAyuda;
	private JMenuItem mnuiFSalir;
	private JMenuItem mnuiFModoFacturacion;
	private JMenu mnuFacturacion;
	private JMenuItem mnuiCProductosVendidosPerso;
	private JMenuItem mnuiCProducto;
	private JMenuItem mnuiCUsuario;
	private JMenuItem mnuiCCombo;
	private JMenu mnuConsultas;
	private JMenuItem mnuiUsuario;
	private JMenuItem mnuiCombo;
	private JMenuItem mnuiProducto;
	private pnFondo fndPrincipal;
	private Statement stEj;
	private ResultSet rs;
	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public frmPrincipal() {
		super();
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			{
				pnMenu = new JPanel();
				FlowLayout pnMenuLayout = new FlowLayout();
				pnMenuLayout.setAlignOnBaseline(true);
				pnMenuLayout.setAlignment(FlowLayout.LEFT);
				pnMenu.setLayout(pnMenuLayout);
				getContentPane().add(pnMenu);
				pnMenu.setBounds(0, 0, 3000, 80);
				pnMenu.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
				this.setIconImage(new ImageIcon("Imagenes/frmIconos/IconoPrincipal.png").getImage());
				{
					btnRegistrarProducto = new JButton();
					pnMenu.add(btnRegistrarProducto);
					btnRegistrarProducto.setBounds(47, 190, 75, 23);
					btnRegistrarProducto.setPreferredSize(new java.awt.Dimension(89, 69));
					btnRegistrarProducto.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnRegistrarProducto.png"));
					btnRegistrarProducto.setToolTipText("Mantenimiento Productos");
					btnRegistrarProducto.setFocusable(false);
					btnRegistrarProducto.addActionListener(this);
				}
				{
					btnRegistrarCombo = new JButton();
					pnMenu.add(btnRegistrarCombo);
					btnRegistrarCombo.setPreferredSize(new java.awt.Dimension(89, 69));
					btnRegistrarCombo.setBounds(47, 190, 75, 23);
					btnRegistrarCombo.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnRegistrarCombo.png"));
					btnRegistrarCombo.setToolTipText("Mantenimiento Combos");
					btnRegistrarCombo.setFocusable(false);
					btnRegistrarCombo.addActionListener(this);
				}
				{
					btnListadoProductos = new JButton();
					pnMenu.add(btnListadoProductos);
					btnListadoProductos.setPreferredSize(new java.awt.Dimension(89, 69));
					btnListadoProductos.setBounds(47, 190, 75, 23);
					btnListadoProductos.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnListadoProductos.png"));
					btnListadoProductos.setToolTipText("Listado de Productos");
					btnListadoProductos.setFocusable(false);
					btnListadoProductos.addActionListener(this);
				}
				{
					btnListadoCombo = new JButton();
					pnMenu.add(btnListadoCombo);
					btnListadoCombo.setPreferredSize(new java.awt.Dimension(89, 69));
					btnListadoCombo.setBounds(47, 190, 75, 23);
					btnListadoCombo.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnListadoCombos.png"));
					btnListadoCombo.setToolTipText("Listado de Combos");
					btnListadoCombo.setFocusable(false);
					btnListadoCombo.addActionListener(this);
				}
				{
					btnModoFacturacion = new JButton();
					pnMenu.add(btnModoFacturacion);
					btnModoFacturacion.setPreferredSize(new java.awt.Dimension(89, 69));
					btnModoFacturacion.setBounds(47, 190, 75, 23);
					btnModoFacturacion.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnModoFacturacion.png"));
					btnModoFacturacion.setToolTipText("Modo Facturacion");
					btnModoFacturacion.setFocusable(false);
					btnModoFacturacion.addActionListener(this);
				}
				{
					btnRegistrarUsuario = new JButton();
					pnMenu.add(btnRegistrarUsuario);
					btnRegistrarUsuario.setPreferredSize(new java.awt.Dimension(89, 69));
					btnRegistrarUsuario.setBounds(47, 190, 75, 23);
					btnRegistrarUsuario.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnRegistroUsuario.png"));
					btnRegistrarUsuario.setToolTipText("Mantenimiento Usuario");
					btnRegistrarUsuario.setFocusable(false);
					btnRegistrarUsuario.addActionListener(this);
				}
				{
					btnSalir = new JButton();
					pnMenu.add(btnSalir);
					btnSalir.setPreferredSize(new java.awt.Dimension(89, 69));
					btnSalir.setBounds(47, 190, 75, 23);
					btnSalir.setIcon(new ImageIcon("Imagenes/frmPrincipal/btnSalir.png"));
					btnSalir.setToolTipText("Salir");
					btnSalir.setFocusable(false);
					btnSalir.addActionListener(this);
				}
			}
			{
				fndPrincipal = new pnFondo("Imagenes/frmPrincipal/Fondo - ");
				getContentPane().add(fndPrincipal);
				fndPrincipal.setBounds(0, 0, fndPrincipal.getAlto(), fndPrincipal.getAncho());
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setExtendedState(this.MAXIMIZED_BOTH);
			this.setSize(800, 600);
			this.setJMenuBar(mnubBarra);
			{
				mnubBarra = new JMenuBar();
				setJMenuBar(mnubBarra);
				{
					mnuMantenimiento = new JMenu();
					mnubBarra.add(mnuMantenimiento);
					mnuMantenimiento.setText("Mantenimiento");
					mnuMantenimiento.setMnemonic(java.awt.event.KeyEvent.VK_M);
					{
						mnuiProducto = new JMenuItem();
						mnuMantenimiento.add(mnuiProducto);
						mnuiProducto.setText("Producto");
						mnuiProducto.setMnemonic(java.awt.event.KeyEvent.VK_P);
						mnuiProducto.setIcon(new ImageIcon("Imagenes/frmIconos/IconoProducto.png"));
						mnuiProducto.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 2"));
						mnuiProducto.addActionListener(this);
					}
					{
						mnuiCombo = new JMenuItem();
						mnuMantenimiento.add(mnuiCombo);
						mnuiCombo.setText("Combo");
						mnuiCombo.setIcon(new ImageIcon("Imagenes/frmIconos/IconoCombo.png"));
						mnuiCombo.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 3"));
						mnuiCombo.setMnemonic(java.awt.event.KeyEvent.VK_C);
						mnuiCombo.addActionListener(this);
					}
					{
						mnuiUsuario = new JMenuItem();
						mnuMantenimiento.add(mnuiUsuario);
						mnuiUsuario.setText("Usuario");
						mnuiUsuario.setIcon(new ImageIcon("Imagenes/frmIconos/IconoUsuario.png"));
						mnuiUsuario.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 6"));
						mnuiUsuario.setMnemonic(java.awt.event.KeyEvent.VK_U);
						mnuiUsuario.addActionListener(this);
					}
				}
				{
					mnuConsultas = new JMenu();
					mnubBarra.add(mnuConsultas);
					mnuConsultas.setText("Consultas");
					mnuConsultas.setMnemonic(java.awt.event.KeyEvent.VK_C);
					mnuConsultas.addActionListener(this);
					{
						mnuiCProducto = new JMenuItem();
						mnuConsultas.add(mnuiCProducto);
						mnuiCProducto.setText("Listado Productos");
						mnuiCProducto.setMnemonic(java.awt.event.KeyEvent.VK_P);
						mnuiCProducto.setBorderPainted(false);
						mnuiCProducto.addActionListener(this);
					}
					{
						mnuiCCombo = new JMenuItem();
						mnuConsultas.add(mnuiCCombo);
						mnuiCCombo.setText("Listado Combos");
						mnuiCCombo.setMnemonic(java.awt.event.KeyEvent.VK_C);
						mnuiCCombo.addActionListener(this);
					}
					{
						mnuiCProductosVendidosPerso = new JMenuItem();
						mnuConsultas.add(mnuiCProductosVendidosPerso);
						mnuiCProductosVendidosPerso.setText("Listado Productos Vendidos Personalizados");
						mnuiCProductosVendidosPerso.setMnemonic(java.awt.event.KeyEvent.VK_R);
						mnuiCProductosVendidosPerso.addActionListener(this);
					}
					{
						mnuiCUsuario = new JMenuItem();
						mnuConsultas.add(mnuiCUsuario);
						mnuiCUsuario.setText("Listado Usuario");
						mnuiCUsuario.setMnemonic(java.awt.event.KeyEvent.VK_U);
						mnuiCUsuario.addActionListener(this);
					}
				}
				{
					mnuFacturacion = new JMenu();
					mnubBarra.add(mnuFacturacion);
					mnuFacturacion.setText("Facturacion");
					mnuFacturacion.setMnemonic(java.awt.event.KeyEvent.VK_F);
					{
						mnuiFModoFacturacion = new JMenuItem();
						mnuFacturacion.add(mnuiFModoFacturacion);
						mnuiFModoFacturacion.setText("Modo Facturacion");
						mnuiFModoFacturacion.setIcon(new ImageIcon("Imagenes/frmIconos/IconoModoFacturacion.png"));
						mnuiFModoFacturacion.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed 1"));
						mnuiFModoFacturacion.setMnemonic(java.awt.event.KeyEvent.VK_F);
						mnuiFModoFacturacion.addActionListener(this);
					}
					{
						mnuiFSalir = new JMenuItem();
						mnuFacturacion.add(mnuiFSalir);
						mnuiFSalir.setText("Salir");
						mnuiFSalir.setIcon(new ImageIcon("Imagenes/frmIconos/IconoSalir.png"));
						mnuiFSalir.setMnemonic(java.awt.event.KeyEvent.VK_F4);
						mnuiFSalir.addActionListener(this);
					}
				}
				{
					mnuAyuda = new JMenu();
					mnubBarra.add(mnuAyuda);
					mnuAyuda.setText("Ayuda");
					mnuAyuda.setMnemonic(java.awt.event.KeyEvent.VK_A);
					{
						mnuiAAyuda = new JMenuItem();
						mnuAyuda.add(mnuiAAyuda);
						mnuiAAyuda.setText("Acerca de ");
						mnuiAAyuda.setIcon(new ImageIcon("Imagenes/frmIconos/IconoInformaciones.png"));
						mnuiAAyuda.setMnemonic(java.awt.event.KeyEvent.VK_A);
						mnuiAAyuda.addActionListener(this);
					}
				}
			}
		} catch (Exception e) {}
	}

	public void setBtnRegistrarUsuario(boolean btnRegistrarUsuario) {
		this.btnRegistrarUsuario.setEnabled(btnRegistrarUsuario);
	}

	public void setBtnRegistrarCombo(boolean btnRegistrarCombo) {
		this.btnRegistrarCombo.setEnabled(btnRegistrarCombo);
	}

	public void setBtnRegistrarProducto(boolean btnRegistrarProducto) {
		this.btnRegistrarProducto.setEnabled(btnRegistrarProducto);
	}

	public void setMnuiCUsuario(boolean mnuiCUsuario) {
		this.mnuiCUsuario.setEnabled(mnuiCUsuario);
	}

	public void setMnuiUsuario(boolean mnuiUsuario) {
		this.mnuiUsuario.setEnabled(mnuiUsuario);
	}

	public void setMnuiCombo(boolean mnuiCombo) {
		this.mnuiCombo.setEnabled(mnuiCombo);
	}

	public void setMnuiProducto(boolean mnuiProducto) {
		this.mnuiProducto.setEnabled(mnuiProducto);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Object actualMenuItem =  arg0.getSource();
		
		if(actualMenuItem==btnRegistrarProducto)
		{
		
			arg0.setSource(mnuiProducto);
		}
		else if(actualMenuItem==btnRegistrarCombo)
		{
			arg0.setSource(mnuiCombo);
		}
		else if(actualMenuItem==btnListadoProductos)
		{
			imprimir("SELECT PNombre,Precio FROM Productos");
		}
		else if(actualMenuItem==btnListadoCombo)
		{
			imprimir("SELECT CNombre,Precio FROM Combo");
		}
		else if(actualMenuItem==btnModoFacturacion)
		{
			new frmFacturacion();
			
			
		}
		else if(actualMenuItem==btnRegistrarUsuario)
		{
			new frmUsuario();
		}
		else if(actualMenuItem==btnSalir)
		{
			vaciarDir();
			this.dispose();
		}
		
		
		 if(arg0.getSource()==mnuiProducto)
		{
			 new frmProducto();
		
		}
		 if(arg0.getSource()==mnuiCombo)
		{
			new frmCombo();
		}
		else if(actualMenuItem==mnuiUsuario) 
		{
			new frmUsuario();
		}
		else if(actualMenuItem==mnuiCProducto)
		{
			imprimir("SELECT PNombre,Precio FROM Productos");
		}
		else if(actualMenuItem==mnuiCCombo)
		{
			imprimir("SELECT CNombre,Precio FROM Combo");
		}
		else if(actualMenuItem==mnuiCProductosVendidosPerso)
		{
			String i = JOptionPane.showInputDialog(null, "Insertar Fecha Desde");
			String e = JOptionPane.showInputDialog(null, "Insertar Fecha Hasta");
			imprimir("SELECT detalle_factura.PNombre, COUNT(*) FROM factura JOIN detalle_factura ON factura.Factura_id =  detalle_factura.Factura_id WHERE (factura.Fecha >=" + i + ")AND (" + e+ "<= factura.Fecha) GROUP BY detalle_factura.PNombre");
		}
		else if(actualMenuItem==mnuiCUsuario)
		{
			imprimir("SELECT Usuario,Constrasena FROM Usuario");
		}
		else if(actualMenuItem==mnuiFModoFacturacion)
		{
			new frmFacturacion();
		}
		else if(actualMenuItem==mnuiFSalir)
		{
			
			//implementars
		}
		else if(actualMenuItem==mnuiAAyuda)
		{
			//Implementar
		}
	}
	
	public void imprimir(String Sentencia){
		JasperReport jasperReport;
		try {
			stEj = ConexionBD.obtenerConexion().createStatement();
			rs =stEj.executeQuery(Sentencia);
			if (!rs.next()){
				JOptionPane.showMessageDialog(this, "El Resultado de Datos Es Vacio, No Se Imprimira El Reporte");
				return ;
			}
			rs.previous();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		try {
			//UBICACION COMPLETA DEL ARCHIVO
                        jasperReport = JasperCompileManager.compileReport("JReports/rptUsuarios.jrxml");
			//RESULTSET QUE SE LE MANDARA AL JRXML//
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRResultSetDataSource(rs));
                        JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException ex) {
				ex.printStackTrace();

        }
	}
	
	public void vaciarDir()
	{
		File file1 = new File("imagenes/temp");
		File file2 = new File("imagenes/temp/combo");
		File file3 = new File("imagenes/temp/prod");
		File [] files;
		
			files = file1.listFiles();
			for(int i=0;i<files.length;i++)
			{
				file1 = files[i];
				file1.delete();
			}
		files = file2.listFiles();
		for(int i=0;i<files.length;i++)
		{
			file2 = files[i];
			file2.delete();
		}
		files = file3.listFiles();
		for(int i=0;i<files.length;i++)
		{
			file3 = files[i];
			file3.delete();
		}
		
		
		
	}
	
	public static void main(String...string){
		new frmPrincipal().setVisible(true);
	}
}


 