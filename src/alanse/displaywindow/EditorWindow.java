package alanse.displaywindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JEditorPane;

import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



/*
 * EditorWindow.java , orginally named Editor.java is a source file written by George-popoiu
 * and modified a little by keshav sharma, 
 * Link to Original : https://github.com/george-popoiu/Editor
 * 
 * 
 */
@SuppressWarnings("serial")
public class EditorWindow extends JPanel implements  KeyListener,DocumentListener/*,Runnable,ActionListener*/{
	public boolean changed = false;
	public JEditorPane textPane;
	
	private JPanel bottomPanel;
	private JLabel totalLines;
	private JLabel currentLine;
	private String fullText; //the entire text inside the Editor
	public String[] textList;//stores fullText such as each
	
	private GridBagConstraints gridManager;

	public EditorWindow() {
		this.setBorder(new LineBorder(Color.blue,5));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.yellow);
		textPane = new JEditorPane();
		textPane.setBackground(Color.GRAY);
		textPane.setForeground(Color.green);
		textPane.addKeyListener(this);
		totalLines=new JLabel("0");
		currentLine = new JLabel("None");
		fullText=new String(textPane.getText());
		textList=splitLines();
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
		String[] array=fullText.split(System.lineSeparator());
		for(int i=0;i<array.length;i++){
			array[i]=array[i].trim();//trim each line
			//System.out.println(array[i]+"<");
		}
		return array; //returns to textList
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
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
}