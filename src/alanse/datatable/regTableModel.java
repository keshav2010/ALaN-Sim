package alanse.datatable;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.RegisterCell;

public class regTableModel extends AbstractTableModel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<RegisterCell> registerCellList=null; //TODO : replace with hashmap before final release, using List for testing
	public regTableModel()
	{
		registerCellList=new ArrayList<RegisterCell>();
		for(int i=0;i<6;i++)
		{
			registerCellList.add(	new RegisterCell(	"R"+String.valueOf(i)	,1) );
		}
	}
	public void setData(String valueSourceName, int operationResult) {
		if(registerCellList==null)
			return;
		for(int i=0;i<registerCellList.size();i++)
		{
			if(registerCellList.get(i).getName().equals(valueSourceName))
			{
				registerCellList.get(i).setValue(operationResult);
			}
		}
		
	}
	public int getData(String valueSourceName) {
		if(registerCellList==null)
			return 0;
		
		for(int i=0;i<registerCellList.size();i++)
		{
			if ( registerCellList.get(i).getName().equals(valueSourceName)) //will be replaced with hashmap
			{
				return registerCellList.get(i).getValue();
			}
		}
		
		return 0;
	}
	
	public void addRegisterCell(RegisterCell cell)
	{
		if(registerCellList==null)
			registerCellList=new ArrayList<RegisterCell>();
	
		registerCellList.add(cell);
	}
	private static String[] columnNames=
		{
				"Register Name",
				"Value"
		};
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		int rowCount;
		if(registerCellList==null)
			rowCount=0;
		else rowCount=registerCellList.size();
		return rowCount;
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		RegisterCell temp=registerCellList.get(row);
		if(col==0)
			return temp.getName();
		return temp.getValue();
	}
	
}