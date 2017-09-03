package alanse.datatable;

import java.awt.Color;
import java.util.ArrayList;


import javax.swing.JTable;

import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.MemoryCell;

public class MemoryCellTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MemoryCellTable()
	{
		this.setModel(new memTableModel());
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.white);
	}
}
class memTableModel extends AbstractTableModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MemoryCell> memoryCellList=null; //consider making it a hashmap <String,int> (string==key, int == value)
	public memTableModel()
	{
		memoryCellList=new ArrayList<MemoryCell>();
		for(char i='A';i<'G';i++)
		{
			memoryCellList.add(	new MemoryCell(	"LOC"+String.valueOf(i)	,0) );
		}
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
	
}