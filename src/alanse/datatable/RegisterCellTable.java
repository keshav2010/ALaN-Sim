package alanse.datatable;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.MemoryCell;
import alanse.instructiondata.RegisterCell;
public class RegisterCellTable extends JTable {

	public RegisterCellTable()
	{
		this.setModel(new regTableModel());
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.white);
	}

}
class regTableModel extends AbstractTableModel
{
	
	private ArrayList<RegisterCell> registerCellList=null;
	public regTableModel()
	{
		registerCellList=new ArrayList<RegisterCell>();
		for(int i=0;i<6;i++)
		{
			registerCellList.add(	new RegisterCell(	"R"+String.valueOf(i)	,0) );
		}
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