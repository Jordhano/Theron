package Utiliteria;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.mysql.jdbc.Statement;

import PuntoVenta.frmUsuario;

public abstract class Edicion extends JDialog implements ActionListener{
	
	public static String sNombre=""; 
	 static  Connection conexion = ConexionBD.obtenerConexion();
	 static JPanel edicion;
	 static JPanel formularios;
	 static String tabla;
	 JButton btnNuevo;
	 JButton btnEditar;
	 JButton btnGuardar;
	 JButton btnBuscar;
	 JButton btnEliminar;
	 JButton btnCancelar;
	
	 
	public abstract void buscar();
	 public abstract void guardar();
	 public abstract void obtenerInformacion();
	
	public void  inicializarPaneles(JPanel edicion,JPanel formulario,String tabla) {
	
		this.edicion=edicion;
		this.formularios=formulario;
		this.tabla=tabla;
		inicializarBotones();
		opcionesBotones(true, false);
	}
	 
	 
public void opcionesBotones(boolean Estado1, boolean Estado2){
		btnNuevo.setEnabled(Estado1);
		btnEditar.setEnabled(Estado1);
		btnGuardar.setEnabled(Estado2);
		btnBuscar.setEnabled(Estado1);
		btnEliminar.setEnabled(Estado1);      
		btnCancelar.setEnabled(Estado2);
		
		cambiarEstadoObjetoInternos(Estado2);
	
	}
	
 public void cambiarEstadoObjetoInternos(boolean estado){
		JComponent a;
		for (int i=0; i < formularios.getComponentCount(); i ++){
			a = (JComponent) formularios.getComponent(i);
			a.setEnabled(estado);
		}
	}
	
 
	public  void cajasVacias(){
		JTextComponent a;
		JTable t;
		for (int i=0; i < formularios.getComponentCount(); i ++){
			try{
				a = (JTextComponent) formularios.getComponent(i);
				a.setText("");
			}catch(Exception e){
				try{
					t = (JTable) formularios.getComponent(i);
				}catch(Exception ex){
					
				}
			}
		}
			
	}
	
	private void inicializarBotones(){
		btnNuevo = (JButton) edicion.getComponent(0);
		btnEditar = (JButton) edicion.getComponent(1);
		btnGuardar = (JButton) edicion.getComponent(2);
		btnBuscar = (JButton) edicion.getComponent(3);
		btnEliminar = (JButton) edicion.getComponent(4);
		btnCancelar = (JButton) edicion.getComponent(5);
		
	}
	
	public void cambiarEstadoEscrituraObjetoInternos(boolean estado) {
		  JTextComponent a;
		  JTable b;
		  JComboBox c;
		  
		  for(int i=0;i<formularios.getComponentCount();i++)
		  {
			  try{
				  a=(JTextComponent) formularios.getComponent(i);
				  a.setEditable(estado);
			  }catch(Exception e){
				  try {
					  c=(JComboBox) formularios.getComponent(i);
					  c.setEditable(false);
				  }catch(Exception g)
				  {
					  try {
						  b=(JTable) formularios.getComponent(i);
					  
					  }catch(Exception t){}
				  }
			  }
			  
		  }
	  }
	public static boolean iscajasVacias(){
		boolean g=false;
		JTextField a;
	String date;
		JTable t;
		
		
		for (int i=0; i <=formularios.getComponentCount(); i ++){
			try{
				a = (JTextField) formularios.getComponent(i);
				if(a.getText().equals(""))	{
				g=true;	}
				else if(a.getText().indexOf("-")!=-1)
				{
					String telefonoYFecha=a.getText().trim();
					if((telefonoYFecha.length()<12) && (telefonoYFecha.length() !=10))
							{
								g=true;
							}	
				}
			}catch(Exception e){
				try{
					t = (JTable) formularios.getComponent(i);
				}catch(Exception ex){ }
			}
		
		}
		return g;	
	}
	
	
	
	
	public void nuevo() {
		cajasVacias();
		opcionesBotones(false, true);
		cambiarEstadoEscrituraObjetoInternos(true);
		sNombre="";
	}
	
	public void  editar() {
		 
		 if(sNombre.equals(""))
			 JOptionPane.showMessageDialog(this,"No existen Registros Seleccionados","Aviso",JOptionPane.ERROR_MESSAGE);
		 else {
		 cambiarEstadoEscrituraObjetoInternos(true);
		 opcionesBotones(false, true);
		 }
	 }
	 
	 
	public  void eliminar(String campo) {
		

		 cambiarEstadoObjetoInternos(false);
		 
		 if(sNombre.equals(""))
			 JOptionPane.showMessageDialog(this,"No existen Registros Seleccionados","Aviso",JOptionPane.ERROR_MESSAGE);
		 else {
		
			
			 if(comprobacionDeExistenciaEnBD(campo)) {
			 
		 int i=JOptionPane.showConfirmDialog(this," Confirma Eliminacion ?");
		
		 PreparedStatement state;
		try {
			state = conexion.prepareStatement("DELETE FROM "+tabla+" WHERE  "+campo +"= "+"'"+Edicion.sNombre+"'"+" ;");
		
		 if(i==0)
		 {
			 state.executeUpdate();
			 JOptionPane.showMessageDialog(this,"Registro Eliminado ");
			 this.sNombre = "";
			 cajasVacias();
		 }else
			 JOptionPane.showMessageDialog(this,"Eliminacion Cancelada");
		}catch(Exception e){e.printStackTrace();}
			 }
		 else
			 JOptionPane.showMessageDialog(this,"Registro No Existe ");
		 }
		 
	 }
	 
  public boolean comprobacionDeExistenciaEnBD(String campo)
	  {
		  boolean existenciaDeCampo=true;
		  
		  try{
		PreparedStatement  stBuscar= conexion.prepareStatement("SELECT * FROM "+this.tabla+" WHERE "+campo+" ="+"'"+Edicion.sNombre+"';");
		
		  ResultSet result=stBuscar.executeQuery();
		  
		if(!result.next()){
			existenciaDeCampo=false;
	  }
	  }catch(Exception e){}
	  return existenciaDeCampo;
	}
 
 
 public void cancelar()
 {
	 cajasVacias();
		opcionesBotones(true, false);
		sNombre="";
 }
	 
	 
}
