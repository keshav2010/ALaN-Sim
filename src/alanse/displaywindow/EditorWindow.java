package alanse.displaywindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import alanse.executor.Executor;
import alanse.executor.Tokenizer;



/*
 * EditorWindow.java , orginally named Editor.java is a source file written by George-popoiu
 * and modified a little by keshav sharma, 
 * Link to Original : https://github.com/george-popoiu/Editor
 * 
 * 
 */
@SuppressWarnings("serial")
public class EditorWindow extends JPanel implements  ActionListener,KeyListener,DocumentListener/*,Runnable,ActionListener*/{
	public boolean changed = false;
	public JEditorPane textPane;
	
	private JPanel bottomPanel;
	private JLabel totalLines;
	private JLabel currentLine;
	private String fullText; //the entire text inside the Editor
	public String[] textList;//stores fullText as each single line
	private JButton btnRun;
	private GridBagConstraints gridManager;

	public EditorWindow() {
		this.setBorder(new TitledBorder("Text Editor Window"));
		this.setLayout(new BorderLayout());
		textPane = new JEditorPane();
		textPane.setBackground(Color.lightGray);
		textPane.setForeground(Color.BLACK);
		textPane.addKeyListener(this);
		totalLines=new JLabel("0");
		currentLine = new JLabel("None");
		
		fullText=new String(textPane.getText()); //extract entire content written by user and store it as a single string
		textList=splitLines(); //finally the fullText string is parsed and an array of strings is created, as such each index of array
		//stores 1 complete instruction
		
		bottomPanel=new JPanel();		
		textPane.getDocument().addDocumentListener(this);
		buildBottomPanel();//for displaying lines information
		add(new JScrollPane(textPane), BorderLayout.CENTER);
		add(bottomPanel,BorderLayout.SOUTH);
		setSize(500, 500);
		setVisible(true);
	}
	private void updateBottomPanel() //TODO : update bottomPanel
	{
		textList=splitLines(); 
		totalLines.setText(Integer.toString(textList.length));
		currentLine.setText("None");
		bottomPanel.updateUI();		
	}
	private void buildBottomPanel()
	{	updateBottomPanel();
		bottomPanel.setLayout(new GridLayout());
		bottomPanel.add(new JLabel("Total Lines : "));
		bottomPanel.add(totalLines);
		bottomPanel.add(new JLabel("Current Line : "));
		bottomPanel.add(currentLine);
		btnRun = new JButton("Execute");
		
		btnRun.addActionListener(this);
		btnRun.setToolTipText("Execute the Instructions");
		btnRun.setBackground(Color.BLACK);
		btnRun.setForeground(Color.GREEN);
		bottomPanel.add(btnRun);
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changed = true;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		changed = true;
	}
	//TODO : splitLines Function
	public String[] splitLines() //operates on fullText, and textList[]
	{
		fullText=new String(textPane.getText().trim());
		fullText=fullText.toUpperCase();
		
		//Split the entire fullText string into sub-strings, 
		//this action converts a single big string into multiple lines just as how they are formatted in text-editor
		//this action however makes an assumption that user writes 1 single instruction on 1 line and should press ENTER 
		//to mark end of 1 instruction
		String[] array=fullText.split(System.lineSeparator());
		
		for(int i=0;i<array.length;i++){
			array[i]=array[i].trim();//trim each line
		
		}
		return array; //returns to textList
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		updateBottomPanel();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		updateBottomPanel();
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		updateBottomPanel();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnRun))
		{
			System.out.println("Pressed JButton");
			try {
				Tokenizer.feedAll(textList); //feed entire STRING-ARRAY to tokenizer
				Tokenizer.start();  //start the tokenizer action
			} catch (Exception e1) {
				// TODO instructionlist in Tokenizer not initalized error
				e1.printStackTrace();
			}
		}
		
	}
}