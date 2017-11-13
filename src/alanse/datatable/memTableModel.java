package alanse.datatable;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.MemoryCell;

public class memTableModel extends AbstractTableModel
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MemoryCell> memoryCellList=null; //TODO : hashmap <String,int> (string==key, int == value) to be used in final release
	public memTableModel()
	{
		memoryCellList=new ArrayList<MemoryCell>();
		for(char i='A';i<'G';i++)
		{
			memoryCellList.add(	new MemoryCell(	"LOC"+String.valueOf(i)	,0) );
		}
	}
	
	public int getData(String Name,int Type)
	{
		if(memoryCellList==null)
		{
			return 0;
		}
		return Type;
	}
	public void addMemoryCell(MemoryCell cell)
	{
		if(memoryCellList==null)
			memoryCellList=new ArrayList<MemoryCell>();
		memoryCellList.add(cell);
	}
	private static final String[] columnNames =
	{
				"Memory Location",
				"Value"
	};

	@Override
	public String getColumnName(int col) {
	     
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
		if(memoryCellList==null)
			rowCount=0;
		else rowCount=memoryCellList.size();
		return rowCount;
	}

	@Override
	public Object getValueAt(int row, int col) {
	
		MemoryCell tempCell= memoryCellList.get(row);
		if(col==0)
			return tempCell.name;
		return tempCell.getValue();
	}
	public void setData(String valueSourceName, int operationResult) {
		if(memoryCellList==null)
			return;
		for(int i=0;i<memoryCellList.size();i++)
		{
			if(memoryCellList.get(i).name.equals(valueSourceName))
			{
				memoryCellList.get(i).setValue(operationResult);
			}
		}
		
	}
	public int getData(String valueSourceName) {
		if(memoryCellList==null)
			return 0;
		
		for(int i=0;i<memoryCellList.size();i++)
		{
			if ( memoryCellList.get(i).name.equals(valueSourceName))
			{
				return memoryCellList.get(i).getValue();
			}
		}
		
		return 0;
	}
	
}