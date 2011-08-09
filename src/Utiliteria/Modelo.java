package Utiliteria;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Modelo extends AbstractTableModel {
	
	ArrayList<Producto> ListadoProductos = null;
	String [] encabezados = null;
	int  longitudRecorrido;
	int rowCount;
	
	public Modelo(String [] encabezados){
		this.encabezados = encabezados;
		ListadoProductos = new ArrayList<Producto>();
	}
	@Override
	public int getColumnCount() {
		return encabezados.length;
	}

	@Override
	public int getRowCount() {
		 rowCount= ListadoProductos.size();
		
	      longitudRecorrido=rowCount;
          System.out.println(rowCount+" "+longitudRecorrido+" "+ListadoProductos.size());
	return rowCount;
		
	}

	@Override
	public String getColumnName(int x) {
		return encabezados[x];
	}
	
	public void insertarDatos(Producto temp){
		ListadoProductos.add(temp);
		fireTableDataChanged();
	}
	
	public void eliminar(int x){
		ListadoProductos.remove(x);
		fireTableRowsDeleted(x, x);
	}
	@Override
	public Object getValueAt(int x, int y) {
		String retorno = "";
		Producto producto = ListadoProductos.get(x);
		switch(y){
			case 0: 
				retorno = producto.obtenerpNombre();
				break;
			case 1: 
				retorno = "1";
				break;
			case 2: 
				retorno = String.valueOf(producto.obtenerPrecio());
				break;
			case 3: 
				retorno = producto.obtenerCombo();
				break;
		}	
		
		return retorno;
	}
	
	

}
