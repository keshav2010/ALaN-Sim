package alanse.displaywindow;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import alanse.datatable.RegisterCellTable;
import alanse.instructiondata.RegisterCell;

//for displaying registers and related data
public class RegisterWindow extends JPanel{

	JScrollPane tableContainer;
	RegisterCellTable table;
	ArrayList<RegisterCell>registerList;
	public RegisterWindow()
	{
		table=new RegisterCellTable();
		tableContainer=new JScrollPane(table);
		this.setBackground(Color.black);
		this.setBorder(new TitledBorder("Register Address Table"));
		this.setLayout(new GridLayout());
		add(tableContainer);
		this.setBackground(Color.green);
		this.setToolTipText("This Window Displays Variables stored in Registers");
		
	}
	
}