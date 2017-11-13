package alanse.displaywindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayoutInfo;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import alanse.instructiondata.MemoryCell;
import alanse.datatable.MemoryCellTable;
/*
 * for displaying memory address names (created by user)
 */
public class MemoryWindow extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//ArrayList<MemoryCell> memoryList;
	//JTable table;
	
	JScrollPane tableContainer=null; //Table must be enclosed in scrollPane to make sure Columnheader is visible
	MemoryCellTable table=null;
	public MemoryWindow()
	{
		this.setBorder(new TitledBorder("Memory Address Table"));
		this.setLayout(new GridLayout());
		table=new MemoryCellTable();
		tableContainer=new JScrollPane(table);
		add(tableContainer);
		this.setBackground(Color.green);
		this.setToolTipText("This Window Displays Variables stored in Memory");
		
	}
	
	public MemoryCellTable getTableObject() 
	{
	
		return table;
	}
}