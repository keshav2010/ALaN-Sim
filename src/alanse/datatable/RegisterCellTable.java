package alanse.datatable;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.RegisterCell;
public class RegisterCellTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegisterCellTable()
	{
		this.setModel(new regTableModel());
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.white);
	}

}
