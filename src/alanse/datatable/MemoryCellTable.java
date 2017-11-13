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