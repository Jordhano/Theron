package PuntoVenta;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import Utiliteria.Combo;
import Utiliteria.ConexionBD;
import Utiliteria.Modelo;
import Utiliteria.Producto;
import Utiliteria.RestriccionANumerico;





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
public class frmFacturacion extends JDialog implements ActionListener{

	{
		
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public Modelo modelo;
	private  JTable jTable;
	public  JPanel pnBotones;
	private JButton btnProductos;
	private JScrollPane jscTable;
	private JScrollPane jscBotones;
	private JButton btCancelar;
	private JButton btProcesar;
	private JButton btnEliminar;
	private JButton btnCombos;
	private LinkedList<Producto> productos;
	private LinkedList<Combo> combos;
	private PreparedStatement state;
	public PreparedStatement stateTemp;
	public ResultSet result;
	public ResultSet resultTemp;
	public java.sql.Connection conection = ConexionBD.obtenerConexion();
	public double monto;
	public static double descuento=0;
	private double desc1=0;
	
	
	
	public frmFacturacion() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Facturacion");
			this.setIconImage(new ImageIcon("Imagenes/frmPrincipal/btnModoFacturacion.png").getImage());
			pack();
			
		} catch (Exception e) {
		  
			e.printStackTrace();
		}
		
		{
			modelo =  new Modelo(new String []{"NOMBRE DE PRODUCTO","CANTIDAD","PRECIO","COMBO"});
		
			jTable = new JTable(modelo);
			jTable.setBounds(46, 252, 399, 123);
			
			}
		jscTable = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscTable.setBounds(142, 265, 583, 154);
		jscTable.getVerticalScrollBar().setEnabled(true);
		jscTable.setViewportView(jTable);
		getContentPane().add(jscTable);
		{
			pnBotones = new JPanel(new GridLayout(4,4,5,5));
			getContentPane().add(pnBotones);
			pnBotones.setBounds(150, 27, 498, 210);
			pnBotones.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}
		{
			jscBotones =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jscBotones.getVerticalScrollBar().setEnabled(true);
			getContentPane().add(jscBotones);
			jscBotones.setBounds(150, 27, 567, 207);
			jscBotones.setViewportView(pnBotones);
			pnBotones.setPreferredSize(new java.awt.Dimension(567, 207));
		}
		
		
		{
			ConexionBD.exportarImagenes("productos","combo");
			productos=insertarEnListProd();
			combos = insertarEnListCombo();
		}
		{
			btnProductos = new JButton();
			getContentPane().add(btnProductos);
			btnProductos.setBounds(12, 74, 105, 65);
			btnProductos.setText("Productos");
			btnProductos.addActionListener(this);
		}
		{
			btProcesar = new JButton();
			getContentPane().add(btProcesar);
			btProcesar.setText("Procesar");
			btProcesar.setBounds(386, 441, 91, 35);
			btProcesar.addActionListener(this);
		}
		{
			btCancelar = new JButton();
			getContentPane().add(btCancelar);
			btCancelar.setText("Cancelar");
			btCancelar.setBounds(507, 441, 91, 35);
			btCancelar.addActionListener(this);
		}
		{
			btnCombos= new JButton();
			getContentPane().add(btnCombos);
			btnCombos.setText("Combos");
			btnCombos.setBounds(12, 156, 105, 63);
			btnCombos.addActionListener(this);
		}
		{
			btnEliminar = new JButton();
			getContentPane().add(btnEliminar);
			btnEliminar.setText("Eliminar");
			btnEliminar.setBounds(265, 441, 91, 35);
			btnEliminar.addActionListener(this);
		}
		{
			pack();
			this.setSize(797, 547);
			this.setLocationRelativeTo(null);
			this.setModal(true);
			this.setVisible(true);
		}

		}
	
	public LinkedList<Producto> insertarEnListProd()
	{
		LinkedList<Producto> list = new LinkedList<Producto>();
		try{
			state= conection.prepareStatement("SELECT * FROM productos;");
		result =  state.executeQuery();
		String imagen="";
		
		while(result.next()) {
			
			imagen="imagenes/temp/prod/prod_"+result.getString(1)+".png";
			
			list.add(new  Producto(result.getString(1),result.getInt(2),result.getDouble(3),result.getInt(4),imagen,modelo,""));
		}
		}catch(Exception d){}
		
		return list;	
	}	
	
	public LinkedList<Combo> insertarEnListCombo()
	{
		LinkedList<Combo> lista = new LinkedList<Combo>();
		try{
			state= conection.prepareStatement("SELECT * FROM combo;");
		result =  state.executeQuery();
		String imagen="";
		
		while(result.next()) {
			
			imagen="imagenes/temp/combo/combo_"+result.getString(1)+".png";
			
			lista.add(new Combo(result.getString(1),result.getDouble(2),result.getDouble(3),imagen,modelo));
		}
		}catch(Exception d){}
		
		return lista;	
	}	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		monto = 0;
		
	if(arg0.getSource() == btProcesar)
	{
		if(!(jTable.getRowCount()==0)){
		for(int i=0;i<jTable.getRowCount();i++)
		{
			monto += Double.parseDouble((String) jTable.getValueAt(i, 2));
		}
		new cobrar2();
		}
		else {
			JOptionPane.showMessageDialog(this,"No Existen Registros Seleccionados");
		}
	}
		
	else if(arg0.getSource() == btCancelar)
	{
	for(int i=jTable.getRowCount(); i>0; i--)
	{
		modelo.eliminar(i-1);
	}
	}
	else if(arg0.getSource() == btnProductos)
	{
		pnBotones.setVisible(false);
		for(Producto g: productos)
		{
			pnBotones.add(g);
			
		}
		pnBotones.setVisible(true);
	}
	else if(arg0.getSource() == btnCombos)
	{
		pnBotones.setVisible(false);
		for(Combo h: combos)
		{
			pnBotones.add(h);
		}
		pnBotones.setVisible(true);
	}
	else if(arg0.getSource() == btnEliminar)
	{
		if (jTable.getRowCount()!=0){
			int j= jTable.getSelectedRow();
			if(jTable.getValueAt(j, 3).equals(""))
			{
				modelo.eliminar(j);
			}
			else{
				String CNombre=(String) jTable.getValueAt(j, 3);
			
			for(int i=jTable.getRowCount();i>0;i--)
			{
				if(String.valueOf(jTable.getValueAt(i-1, 3)).equals(CNombre)){
				modelo.eliminar(i-1);}
			}
		}
		}else{
			JOptionPane.showMessageDialog(null, "No Existen registro");
		}

	}
	}

	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
	 class cobrar2 extends javax.swing.JDialog  implements ActionListener{
		private JLabel lblMonto;
		private JTextField txtfEfectivo;
		private JLabel lblVuelto;
		private JButton btProcesarCobro;
		private JButton btCancelarCobro;
		private JTextField txtfMonto;
		private JTextField txtfVuelto;
		private JLabel lblEfectivo;
		private JLabel jlbDesc;
		private JLabel lblTotal;
		private JTextField txtfTotal;
		public JTextField txtfDesc;
		private String total;
		/**
		* Auto-generated main method to display this JDialog
		*/
		
		
		public cobrar2() {
			super();
					initGUI();
		}
		
		private void initGUI() {
			try {
				this.setTitle("Cobrar");
				this.setIconImage(new ImageIcon("Imagenes/frmCombos/frmCombos.png").getImage());
				{
					getContentPane().setLayout(null);
					
				}
				{
					lblMonto = new JLabel();
					getContentPane().add(lblMonto, "Center");
					lblMonto.setText("Monto");
					lblMonto.setBounds(18, 16, 49, 24);
				
				}
				{
					txtfMonto = new JTextField();
					getContentPane().add(txtfMonto);
					txtfMonto.setBounds(65, 15, 160, 27);
					txtfMonto.setEditable(false);
					
					                txtfMonto.setText(String.valueOf(monto));
					               
					              
				}
				{
					lblTotal = new JLabel();
					getContentPane().add(lblTotal, "Center");
					lblTotal.setText("Total");
					lblTotal.setBounds(18, 55, 40, 24);
				
				}
				{
					txtfTotal = new JTextField();
					getContentPane().add(txtfTotal);
					txtfTotal.setBounds(65,55, 160, 27);
					txtfTotal.setEditable(false);
					             
					              txtfTotal.setText(String.valueOf(monto-descuento));
				}
				
				{
					lblEfectivo = new JLabel();
					getContentPane().add(lblEfectivo, "Center");
					lblEfectivo.setText("Efectivo");
					lblEfectivo.setBounds(278,57, 67, 24);
				}
				{
					lblVuelto = new JLabel();
					getContentPane().add(lblVuelto, "Center");
					lblVuelto.setText("Vuelto");
					lblVuelto.setBounds(18, 90, 40, 24);
				}
				{
					txtfEfectivo = new JTextField();
					getContentPane().add(txtfEfectivo);
					txtfEfectivo.setBounds(350, 57, 99, 27);
					txtfEfectivo.setDocument(new RestriccionANumerico(txtfEfectivo,1000));
					txtfEfectivo.addKeyListener(new KeyListener() {
						double vuelto = 0;
						@Override
						public void keyReleased(KeyEvent arg0){
							String tempCaja = txtfEfectivo.getText();
							String tempKeyChar = String.valueOf(arg0.getKeyChar());
							String tempDos="";
							try{
								tempDos = tempCaja.substring(tempCaja.indexOf("."),tempCaja.length());
							}catch(Exception e){
								
							}
							if (tempKeyChar.equals(".") && tempDos.indexOf(".") != -1){
								txtfEfectivo.setText(tempCaja.substring(0, tempCaja.indexOf(".")+1));
							}
							if(!txtfEfectivo.getText().equals("")){
						vuelto = Double.parseDouble(txtfEfectivo.getText()) - monto;
							txtfVuelto.setText(String.valueOf(vuelto));
							
						}else 
							txtfVuelto.setText("0.0");
						}
						
					
						
						@Override
						public void keyPressed(KeyEvent e){}
						@Override
						public void keyTyped(KeyEvent e){}
					
					});
					
				}
				{
					txtfVuelto = new JTextField();
					getContentPane().add(txtfVuelto);
					txtfVuelto.setBounds(65, 89, 160, 27);
					txtfVuelto.setEditable(false);
					txtfVuelto.setText("0");
				}
				{
					jlbDesc =  new JLabel();
					getContentPane().add(jlbDesc,"Center");
					jlbDesc.setBounds(278, 16, 67, 24);
					jlbDesc.setText("Descuento");
					
					
							
				}
				{
					txtfDesc = new JTextField();
					getContentPane().add(txtfDesc);
					txtfDesc.setBounds(350, 15, 99, 27);
					txtfDesc.setEditable(false);
					txtfDesc.setText(String.valueOf(descuento));
				}
				{
					btProcesarCobro = new JButton();
					getContentPane().add(btProcesarCobro);
					btProcesarCobro.setBounds(107,130, 120, 34);
					btProcesarCobro.setText("Procesar");
					btProcesarCobro.addActionListener(this);
				}
				{
					btCancelarCobro =  new JButton();
					getContentPane().add(btCancelarCobro);
					btCancelarCobro.setBounds(259,130, 120, 34);
					btCancelarCobro.setText("Cancelar");
					btCancelarCobro.addActionListener(this);
				}
				
				{
					for(int i=0;i<jTable.getRowCount();i++)
					{
						if(jTable.getValueAt(i,3).equals(""))
						{
								desc1=0;
						}else{
							desc1=descuento;
						break;}
					}
				
				}
				descuento =  desc1;
			
			
				
				this.setSize(500, 210);
				this.setModal(true);
				this.setLocationRelativeTo(null);
				this.setVisible(true);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		public void procesarFactura(){
			
			
			actualizarBD();
			
			double valorTotal = Double.parseDouble(txtfVuelto.getText());
			
			
			try{
				if((!(txtfEfectivo.getText().equals("")) && (!(valorTotal<0) ))){
			state = conection.prepareStatement("INSERT INTO factura(Fecha,Total) VALUES('13-12-12' ,"+(double)monto+");");
			state.executeUpdate();
			
			stateTemp = conection.prepareStatement("SELECT * from factura;");
			result = stateTemp.executeQuery();
			
			result.last();
			
			int n= result.getInt(1);
			for(int i=0;i<jTable.getRowCount();i++){
				String producto =(String) jTable.getValueAt(i,0);
			stateTemp =  conection.prepareStatement("INSERT INTO detalle_factura VALUES("+n+",'"+producto+"');");
			stateTemp.executeUpdate();	
			}
			JOptionPane.showMessageDialog(this,"Proceso completo");
			for(int i=jTable.getRowCount(); i>0; i--)
			{
				modelo.eliminar(i-1);
			}
			imprimir("select factura_id from factura order by factura_id desc");
				}
			else {
				JOptionPane.showMessageDialog(this,"Total No Puede Ser Menor que Monto","Aviso",JOptionPane.ERROR_MESSAGE);
				monto=0;
			}
			}catch(Exception r){r.printStackTrace();}
			
		}	
		public void imprimir(String Sentencia){
			int i;
			Statement stEj;
			ResultSet rs = null;
			JasperReport jasperReport;
			try {
				stEj = ConexionBD.obtenerConexion().createStatement();
				rs =stEj.executeQuery(Sentencia);
				rs.first();
				i = rs.getInt(1);
				rs = stEj.executeQuery("select detalle_factura.pnombre, precio from factura join detalle_factura on factura.factura_id = detalle_factura.factura_id join productos on productos.pnombre = detalle_factura.pnombre where detalle_factura.factura_id = "+ i+" order by fecha desc");
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
		
		public void actualizarBD()
		{
			try{
			
				
			
		for(int f=0;f<jTable.getRowCount();f++)
		{	 
			
			String prod = (String ) jTable.getValueAt(f, 0);
			stateTemp = conection.prepareStatement("SELECT * FROM productos WHERE PNombre = ?;");
			stateTemp.setString(1,prod);
			
			resultTemp = stateTemp.executeQuery();
			resultTemp.next();
			
			int cant=resultTemp.getInt(2) -  Integer.parseInt(((String) jTable.getValueAt(f, 1)));
		
		
			state= conection.prepareStatement("UPDATE productos SET Cantidad = ? WHERE PNombre = ? ;");
			state.setInt(1,cant);
			state.setString(2,prod);
			
			state.executeUpdate();
			
		}
				
			}catch(Exception  e)
			{
				e.printStackTrace();
			}
		}
				
		
		
		

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource() == btProcesarCobro)
			{
				
				if(!(txtfMonto.equals(""))){
				
				procesarFactura();
		
				this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Aviso","No Es Posible Procesar",JOptionPane.ERROR_MESSAGE);
				}   
			}
			else if(arg0.getSource() == btCancelarCobro)
			{
				txtfEfectivo.setText("");
				txtfMonto.setText("");
				txtfVuelto.setText("");
				monto=0; 
				
				this.dispose();
			}
			
			
		}

		
	
	
	 }
	 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





}


















