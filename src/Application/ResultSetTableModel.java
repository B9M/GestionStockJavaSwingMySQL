package Application;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel{
	
	// Declaration
	private ResultSet rs;
	
	public ResultSetTableModel (ResultSet rs){
		this.rs= rs;
		fireTableDataChanged();
		
	}

	public int getColumnCount(){
		
		try{
			if (rs == null)
				return 0;
			else 
				return rs.getMetaData().getColumnCount();
			
		}
		catch(SQLException e ){
			System.out.println("getColoumnCount of resultser result ERRREURS___ While calculing Coulmn Count");
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int getRowCount(){
		
		try{
			if (rs == null)
				return 0;
			else 
				rs.last();
				return rs.getRow();
			
		}
		catch(SQLException e ){
			System.out.println("getRow of resultser result ERRREURS___ While calculing Coulmn Count");
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex){
		if (rowIndex < 0  || rowIndex > getRowCount() || columnIndex < 0 || columnIndex > getColumnCount() )
			return null;
		try{
			if (rs == null )
				return 0;
			else {
				rs.absolute(rowIndex + 1);
				return rs.getObject(columnIndex + 1);
				
			}
			
		}
		catch (SQLException e) {
			System.out.println("getValueAt resultset generate ERREURS while FETCHING ROWS ");
			System.out.println(e.getMessage());
			return null;
			
		}
	}
	
	@Override
	public String getColumnName (int columnIndex){
		try {
			return rs.getMetaData().getColumnName(columnIndex + 1);
		}
		catch (SQLException e ) {
			System.out.println("getColumnName resultset Generate ERREURS while fetching colum Name ");
			System.out.println(e.getMessage());
		}
		
		return super.getColumnName(columnIndex);
	}
}
